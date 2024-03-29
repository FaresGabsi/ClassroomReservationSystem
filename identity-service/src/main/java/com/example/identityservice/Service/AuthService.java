package com.example.identityservice.Service;

import com.example.identityservice.Entity.UserCredential;

public interface AuthService {
    public String saveUser(UserCredential userCredential);
    public String generateToken(String userName);
    public void validateToken( String token);

}
