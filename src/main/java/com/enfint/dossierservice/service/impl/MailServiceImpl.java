package com.enfint.dossierservice.service.impl;

import com.enfint.dossierservice.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("za-bank@ymail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @Override
    public void sendSes(String recipient, String sesCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("za-bank@ymail.com");
        message.setTo(recipient);
        message.setSubject("send-ses");
        message.setText(sesCode);
        javaMailSender.send(message);
    }

}
