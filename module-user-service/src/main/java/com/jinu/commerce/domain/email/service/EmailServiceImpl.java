package com.jinu.commerce.domain.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    @Override
    public void sendEmail(String email, String title, String content) {
        log.info("start sendEmail");

        SimpleMailMessage emailForm = createEmailForm(email, title, content);

        try {
            emailSender.send(emailForm);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }


    private SimpleMailMessage createEmailForm(String email, String title, String content) {
        log.info("start createEmailForm");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject(title);
        message.setText(content);

        return message;
    }
}