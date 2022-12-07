package com.enfint.dossierservice.service;

import java.io.File;

public interface MailService {
    void sendEmail(String to, String subject, String body);
    void sendSes(String recipient, String sesCode);
    void sendDocument(String recipient, File file);
}
