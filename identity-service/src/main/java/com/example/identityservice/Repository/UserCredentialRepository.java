package com.example.identityservice.Repository;

import com.example.identityservice.Entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByname(String name);
}
