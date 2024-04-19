package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.domain.user.repository.UserRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ResponseBodyDto bodyDto;
    private final UserRepository repository;

    @Transactional
    public ResponseEntity<ResponseBodyDto> signUpUser(UserRequestDto requestDto) {
        if (repository.existsByEmail(requestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyDto.fail("이메일 중복"));
        }

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .mobile(requestDto.getMobile())
                .address(requestDto.getAddress())
                .build();

        repository.save(user);

        return ResponseEntity.ok(bodyDto.success("가입완료"));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> checkDuplicateByEmail(String email) {
        if (repository.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyDto.fail("이메일 중복"));
        }

        return ResponseEntity.ok(bodyDto.success("사용가능"));
    }
}