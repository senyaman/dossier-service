package com.enfint.dossierservice.service;

public interface MailService {
    void sendEmail(String to, String subject, String body);
    void sendSes(String recipient, String sesCode);
}
