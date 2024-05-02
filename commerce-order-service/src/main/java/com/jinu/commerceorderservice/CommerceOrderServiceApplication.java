package com.jinu.commerceorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class CommerceOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceOrderServiceApplication.class, args);
    }
}