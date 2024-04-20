package com.jinu.commerce.domain.user.service;

import com.jinu.commerce.domain.mail.service.MailService;
import com.jinu.commerce.domain.user.dto.request.UserRequestDto;
import com.jinu.commerce.domain.user.entity.User;
import com.jinu.commerce.domain.user.repository.UserRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ResponseBodyDto bodyDto;
    private final UserRepository repository;
    private final MailService mailService;

    @Override
    public ResponseEntity<ResponseBodyDto> sendVerifyMailForJoin(UserRequestDto requestDto) {
        log.info("start sendVerifyMailForJoin");

        this.checkDuplicateByMail(requestDto.getMail());

        String title = "[e-commerce] 회원가입을 위한 이메일 인증";
        String content = "인증번호 : " + this.createVerifyCode();

        mailService.sendMail(requestDto.getMail(), title, content);

        return ResponseEntity.ok(bodyDto.success("mail 발송 완료"));
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseBodyDto> signUpUser(UserRequestDto requestDto) {
        log.info("start signUpUser");

        this.checkDuplicateByMail(requestDto.getMail());

        User user = User.builder()
                .mail(requestDto.getMail())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .mobile(requestDto.getMobile())
                .address(requestDto.getAddress())
                .build();

        repository.save(user);

        return ResponseEntity.ok(bodyDto.success("가입완료"));
    }

    @Override
    @Transactional(readOnly = true)
    public void checkDuplicateByMail(String mail) {
        if (repository.existsByMail(mail)) {
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