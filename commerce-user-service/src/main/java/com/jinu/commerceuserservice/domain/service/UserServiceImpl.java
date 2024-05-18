package com.jinu.commerceuserservice.domain.service;

import com.jinu.commercecommon.exception.CustomException;
import com.jinu.commercecommon.exception.ErrorCode;
import com.jinu.commercecommon.redis.RedisService;
import com.jinu.commerceuserservice.domain.dto.SignUpRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdateInfoRequestDto;
import com.jinu.commerceuserservice.domain.dto.UpdatePasswordRequestDto;
import com.jinu.commerceuserservice.domain.entity.User;
import com.jinu.commerceuserservice.domain.repository.UserRepository;
import com.jinu.commerceuserservice.domain.util.UserUtil;
import com.jinu.commerceuserservice.global.email.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Random;

@Service
@Slf4j(topic = "UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final RedisService redisService;
    private final UserUtil userUtil;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private Long authCodeExpirationMillis;

    @Override
    @Transactional
    public void signUpUser(SignUpRequestDto requestDto) {
        log.info("start signUpUser");

        this.checkDuplicateByEmail(requestDto.getEmail());

        User user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .name(requestDto.getName())
                .mobile(requestDto.getMobile())
                .address(requestDto.getAddress())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public void sendVerifyEmailForJoin(String email) {
        log.info("이메일 검증");

        this.checkDuplicateByEmail(email);

        String title = "[e-commerce] 회원가입을 위한 이메일 인증";
        String code = this.createVerifyCode();
        String content = "인증번호 : " + code;

        emailService.sendEmail(email, title, content);

        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = email / value = code )
        redisService.setValues(email, code, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkVerifyCodeForJoin(String email, String code) {
        log.info("인증코드 검증");

        this.checkDuplicateByEmail(email);

        String redisCode = redisService.getValues(email);

        if (!redisCode.equals(code)) throw new CustomException(ErrorCode.NOT_MATCHED_CODE);
    }

    @Override
    @Transactional
    public void updateByInfo(HttpServletRequest req, UpdateInfoRequestDto requestDto) {
        log.info("개인정보 업데이트");

        Long userId = this.userUtil.getUserIdFromToken(req);
        User user = this.getUserByUserId(userId);

        user.updateByInfo(requestDto);
    }

    @Override
    @Transactional
    public void updateByPassword(HttpServletRequest req, UpdatePasswordRequestDto requestDto) {
        log.info("패스워드 업데이트");

        Long userId = this.userUtil.getUserIdFromToken(req);
        User user = this.getUserByUserId(userId);

        this.validatePassword(requestDto.getOgPassword(), user.getPassword());

        user.updateByPassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
    }

    @Transactional(readOnly = true)
    public void checkDuplicateByEmail(String mail) {
        this.userRepository.findByEmail(mail).ifPresent(
                user -> {
                    throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
                });
    }

    @Transactional(readOnly = true)
    public User getUserByUserId(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    public String createVerifyCode() {
        Random random = new Random();

        int min = 1000;
        int max = 9999;

        return String.valueOf(random.nextInt(max - min + 1) + min);
    }

    public void validatePassword(String inputPassword, String setPassword) {
        if (!passwordEncoder.matches(inputPassword, setPassword)) {
            throw new CustomException(ErrorCode.NOT_MATCHED_PASSWORD);
        }
    }
}