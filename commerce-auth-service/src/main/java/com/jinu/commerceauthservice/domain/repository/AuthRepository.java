package com.jinu.commerceauthservice.domain.repository;


import com.jinu.commerceauthservice.domain.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    void deleteByEmail(String email);
}
