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
    @Override
    public String saveUser(UserCredential userCredential) {
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "User saved successfully";
    }
}
