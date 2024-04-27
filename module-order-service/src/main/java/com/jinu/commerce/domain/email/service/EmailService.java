package com.jinu.commerce.domain.email.service;

public interface EmailService {
    public void sendEmail(String email, String title, String content);
}
