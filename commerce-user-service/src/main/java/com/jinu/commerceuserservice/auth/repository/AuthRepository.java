package com.jinu.commerceuserservice.auth.repository;

import com.jinu.commerceuserservice.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    void deleteByEmail(String email);
}
