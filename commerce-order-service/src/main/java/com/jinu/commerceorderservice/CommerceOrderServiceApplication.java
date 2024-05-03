package com.jinu.commerceorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.jinu.commerceorderservice", "com.jinu.commercecommon"})
@EntityScan(basePackages = {"com.jinu.commerceuserservice", "com.jinu.commerceorderservice", "com.jinu.commerceproductservice"})
@EnableJpaAuditing
@EnableScheduling
public class CommerceOrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceOrderServiceApplication.class, args);
    }
}