package me.diego.gymapi.controller;

import lombok.RequiredArgsConstructor;
import me.diego.gymapi.dto.UserAuthModel;
import me.diego.gymapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> signIn(@RequestBody UserAuthModel userAuthModel) {
        String jwt = authService.signInUser(userAuthModel);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody UserAuthModel userAuthModel) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.registerUser(userAuthModel));
    }
}
