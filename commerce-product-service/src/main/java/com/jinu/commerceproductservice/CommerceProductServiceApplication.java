package com.jinu.commerceproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.jinu.commerceproductservice", "com.jinu.commercecommon"})
@EnableJpaAuditing
public class CommerceProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceProductServiceApplication.class, args);
    }
}