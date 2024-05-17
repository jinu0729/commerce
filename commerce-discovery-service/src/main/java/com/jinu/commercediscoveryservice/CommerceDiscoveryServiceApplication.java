package com.jinu.commercediscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CommerceDiscoveryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommerceDiscoveryServiceApplication.class, args);
    }
}