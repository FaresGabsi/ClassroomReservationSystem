package com.example.identityservice.Service;

import com.example.identityservice.Entity.UserCredential;
import com.example.identityservice.Repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "User saved successfully";
    }

    @Override
    public String generateToken(String userName) {
        return jwtService.generateToken(userName);
    }
    public void validateToken( String token) {
        jwtService.validateToken(token);
    }
}
