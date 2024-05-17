package com.jinu.commerceauthservice.global.security;

import com.jinu.commerceauthservice.domain.client.UserServiceClient;
import com.jinu.commerceauthservice.domain.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserServiceClient userServiceClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = userServiceClient.getUser(email);

        return new UserDetailsImpl(userDto);
    }
}