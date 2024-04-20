package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.email.service.EmailService;
import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.domain.user.repository.UserRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseBodyDto bodyDto;
    private final UserRepository repository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(UserRequestDto requestDto) {
        log.info("start sendVerifyEmailForJoin");

        this.checkDuplicateByEmail(requestDto.getEmail());

        String title = "[e-commerce] 회원가입을 위한 이메일 인증";
        String content = "인증번호 : " + this.createVerifyCode();

        emailService.sendEmail(requestDto.getEmail(), title, content);

        return ResponseEntity.ok(bodyDto.success("mail 발송 완료"));
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseBodyDto> signUpUser(UserRequestDto requestDto) {
        log.info("start signUpUser");

        this.checkDuplicateByEmail(requestDto.getEmail());

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .mobile(requestDto.getMobile())
                .address(requestDto.getAddress())
                .build();

        repository.save(user);

        return ResponseEntity.ok(bodyDto.success("가입완료"));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkDuplicateByEmail(String mail) {
        if (repository.existsByEmail(mail)) {
            throw new CustomException(ErrorCode.DUPLICATE_MAIL);
        }
    }

    @Override
    public int createVerifyCode() {
        Random random = new Random();

        int min = 1000;
        int max = 9999;

        return random.nextInt(max - min + 1) + min;
    }
}