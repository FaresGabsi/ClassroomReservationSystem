package com.example.identityservice.Service;

import com.example.identityservice.Entity.CustomUserDetails;
import com.example.identityservice.Entity.UserCredential;
import com.example.identityservice.Repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserCredentialRepository userCredentialRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> user= userCredentialRepository.findByname(username);
        return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found:"+username));
    }
}
