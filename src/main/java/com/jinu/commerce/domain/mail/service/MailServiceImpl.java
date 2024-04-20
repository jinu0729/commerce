package com.jinu.commerce.domain.mail.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String mail, String title, String content) {
        log.info("start sendMail");

        SimpleMailMessage emailForm = createMailForm(mail, title, content);

        try {
            mailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur mail: {}, title: {}, text: {}", mail, title, content);
        }
    }


    private SimpleMailMessage createMailForm(String mail, String title, String content) {
        log.info("start createMailForm");

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mail);
        message.setSubject(title);
        message.setText(content);

        return message;
    }
}