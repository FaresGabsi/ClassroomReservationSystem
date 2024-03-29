package com.example.identityservice.Controller;

import com.example.identityservice.Dto.AuthRequest;
import com.example.identityservice.Entity.UserCredential;
import com.example.identityservice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authManager;
    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody UserCredential user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.saveUser(user));
    }

    @GetMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest user) {
        Authentication auth=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if (auth.isAuthenticated())
            return ResponseEntity.ok(authService.generateToken(user.getUsername()));
        else{
            throw new RuntimeException("Invalid credentials");
            //return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return ResponseEntity.ok("Token is valid");
    }
}
