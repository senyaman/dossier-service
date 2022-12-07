package com.enfint.dossierservice.service.impl;

import com.enfint.dossierservice.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public void sendDocument(String recipient, File file) {

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setFrom(new InternetAddress("za-bank@ymail.com", "za-bank@ymail.com"));

            mimeMessage.setSubject("send-documents");
            Multipart multipart = new MimeMultipart();
            MimeBodyPart fileBody = new MimeBodyPart();

            DataSource dataSource = new FileDataSource(file);
            fileBody.setDataHandler(new DataHandler(dataSource));
            fileBody.setFileName(file.getName() + ".txt");

            mimeMessage.setContent(multipart);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }

}
