package com.jinu.commerceuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.jinu.commerceuserservice", "com.jinu.commercecommon"})
@EnableJpaAuditing
public class CommerceUserService {
    public static void main(String[] args) {
        SpringApplication.run(CommerceUserService.class, args);
    }
}