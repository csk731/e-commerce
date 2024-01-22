package dev.chaitanyaallu.userservice.services;

import dev.chaitanyaallu.userservice.dtos.UserDto;
import dev.chaitanyaallu.userservice.models.Session;
import dev.chaitanyaallu.userservice.models.SessionStatus;
import dev.chaitanyaallu.userservice.models.User;
import dev.chaitanyaallu.userservice.repositories.SessionRepository;
import dev.chaitanyaallu.userservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
@Primary
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto signUp(User user) {

        Optional<User> optionalUser=userRepository.findByEmail(user.getEmail());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User savedUser = userRepository.save(user);

        return User.toUserDto(savedUser);
    }
    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if(!bCryptPasswordEncoder.matches(password,optionalUser.get().getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        User user = optionalUser.get();

        //String token= RandomStringUtils.randomAlphanumeric(20);

        // Create a test key suitable for the desired HMAC-SHA algorithm:
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS512
        SecretKey key = alg.key().build();

        // create json with user_id, email_id, roles
//        String message = "{\n" +
//                "  \"email_id\": \"chay@gmail.com\",\n" +
//                "  \"name\": \"Chaitanya Allu\",\n" +
//                "  \"user_id\":\"12345\",\n" +
//                "  \"roles\": [\"mentor\",\"ta\"],\n" +
//                "  \"expires_on\":\"01January2024\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String, Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email_id", user.getEmail());
        jsonForJwt.put("name", user.getFirstName()+ " " + user.getLastName());
        jsonForJwt.put("user_id", user.getId());
        jsonForJwt.put("roles", "X");
        //curr date + 10 days
        jsonForJwt.put("expires_on", new Date(LocalDate.now().plusDays(10).toEpochDay()));
        // curr date
        jsonForJwt.put("created_on", new Date(LocalDate.now().toEpochDay()));

        // Create the compact JWS:
        String jws = Jwts.builder().claims(jsonForJwt).signWith(key, alg).compact();

        // Parse the compact JWS:
//        content = Jwts.parser().verifyWith(key).build().parseSignedContent(jws).getPayload();

//        assert message.equals(new String(content, StandardCharsets.UTF_8));

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setUser(optionalUser.get());
        session.setToken(jws);
        session.setCreatedAt(new Date());

        sessionRepository.save(session);

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "token=" + jws);

        return new ResponseEntity<UserDto>(User.toUserDto(user), headers, HttpStatus.OK);

    }
    @Override
    public void logout(String token) {
        Optional<Session> optionalSession=sessionRepository.findByTokenEquals(token);
        if(optionalSession.isEmpty()){
            throw new RuntimeException("Invalid token");
        }
        else if(optionalSession.get().getSessionStatus()==SessionStatus.ENDED){
            throw new RuntimeException("Session already ended");
        }
        else{
            Session session=optionalSession.get();
            session.setSessionStatus(SessionStatus.ENDED);
            sessionRepository.save(session);
        }
    }

    @Override
    public SessionStatus validate(String token, String userId) {
        Optional<Session> optionalSession=sessionRepository.findByTokenAndUser_Id(token, UUID.fromString(userId));
        if(optionalSession.isEmpty()){
            return SessionStatus.ENDED;
        }
        else if(optionalSession.get().getSessionStatus().equals(SessionStatus.ENDED)){
            return SessionStatus.ENDED;
        }
        Jws<Claims> claims=Jwts.parser()
                .build()
                .parseSignedClaims(token);

        String email_id=(String) claims.getPayload().get("email_id");
        String name=(String) claims.getPayload().get("name");
        String user_id=(String) claims.getPayload().get("user_id");
        List<String> roles=(List<String>) claims.getPayload().get("roles");
        Date expires_on=(Date) claims.getPayload().get("expires_on");
        Date created_on=(Date) claims.getPayload().get("created_on");

//        if(created_on.before(new Date().))
        return SessionStatus.ACTIVE;
    }
}


//eyJjdHkiOiJ0ZXh0L3BsYWluIiwiYWxnIjoiSFMyNTYifQ.ewogICJlbWFpbF9pZCI6ICJjaGF5QGdtYWlsLmNvbSIsCiAgIm5hbWUiOiAiQ2hhaXRhbnlhIEFsbHUiLAogICJ1c2VyX2lkIjoiMTIzNDUiLAogICJyb2xlcyI6IFsibWVudG9yIiwidGEiXSwKICAiZXhwaXJlc19vbiI6IjAxSmFudWFyeTIwMjQiCn0.g2fo-w8zn0AoBEtpU9tlpFidtivGbfYrHTFK7hGPZjk