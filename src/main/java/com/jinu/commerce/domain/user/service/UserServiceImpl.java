package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.email.service.EmailService;
import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.domain.user.repository.UserRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import com.jinu.commerce.global.redis.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Random;

@Service
@Slf4j(topic = "UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseBodyDto bodyDto;
    private final EmailService emailService;
    private final RedisService redisService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private Long authCodeExpirationMillis;

    @Override
    public ResponseEntity<ResponseBodyDto> sendVerifyEmailForJoin(String email) {
        log.info("이메일 검증");

        this.checkDuplicateByEmail(email);

        String title = "[e-commerce] 회원가입을 위한 이메일 인증";
        String code = this.createVerifyCode();
        String content = "인증번호 : " + code;

        emailService.sendEmail(email, title, content);

        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )
        redisService.setValues(email, code, Duration.ofMillis(this.authCodeExpirationMillis));

        return ResponseEntity.ok(bodyDto.success("발송완료"));
    }

    @Override
    public ResponseEntity<ResponseBodyDto> checkVerifyCodeForJoin(String email, String code) {
        log.info("인증코드 검증");

        this.checkDuplicateByEmail(email);

        String redisCode = redisService.getValues(email);

        if(!redisCode.equals(code)) throw new CustomException(ErrorCode.NOT_MATCHED_CODE);

        return ResponseEntity.ok(bodyDto.success("인증완료"));
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
    public String createVerifyCode() {
        Random random = new Random();

        int min = 1000;
        int max = 9999;

        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}