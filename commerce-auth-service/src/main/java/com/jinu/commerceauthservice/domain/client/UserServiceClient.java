package com.jinu.commerceauthservice.domain.client;

import com.jinu.commerceauthservice.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commerce-user-service")
public interface UserServiceClient {

    @GetMapping("/api/user")
    UserDto getUser(@RequestParam(name = "email") String email);
}