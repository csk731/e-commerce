package dev.chaitanyaallu.userservice.services;

import dev.chaitanyaallu.userservice.dtos.UserDto;
import dev.chaitanyaallu.userservice.models.SessionStatus;
import dev.chaitanyaallu.userservice.models.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    UserDto signUp(User user);
    ResponseEntity<UserDto> login(String email, String password) throws Exception;
    void logout(String token);
    SessionStatus validate(String token, String userId);
}
