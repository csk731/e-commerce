package dev.chaitanyaallu.userservice.controllers;

import dev.chaitanyaallu.userservice.dtos.*;
import dev.chaitanyaallu.userservice.models.User;
import dev.chaitanyaallu.userservice.repositories.UserRepository;
import dev.chaitanyaallu.userservice.services.AuthService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(AuthService authService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from AuthController";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        User user=new User();
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setEmail(requestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(requestDto.getPassword()));

        return new ResponseEntity<>(authService.signUp(user), HttpStatus.CREATED);

        //return ResponseEntity.status(200).body(User.toUserDto(savedUser));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody SignInRequestDto requestDto) throws Exception {
        return authService.login(requestDto.getEmail(), requestDto.getPassword());
    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto requestDto) {
        authService.logout(requestDto.getToken());
        return ResponseEntity.status(200).build();
    }
    public void validate(){

    }
}
