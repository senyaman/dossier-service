package com.enfint.dossierservice.kafka;

import com.enfint.dossierservice.entity.Application;
import com.enfint.dossierservice.feignclient.DealClient;
import com.enfint.dossierservice.payload.EmailMessageDTO;
import com.enfint.dossierservice.service.DocumentService;
import com.enfint.dossierservice.service.MailService;
import com.enfint.dossierservice.utils.Theme;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final MailService mailService;
    private final ObjectMapper objectMapper;
    private final DealClient dealClient;
    private final DocumentService documentService;

    @KafkaListener(topics = "finish-registration", groupId = "messageGroup")
    public void consumeFinishRegistration(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        String emailBody = String.format(
                "%s, please finish up your registration.", application.getClient().getFirstName()
        );

        mailService.sendEmail(emailMessage.getAddress(), theme.toString(), emailBody);
    }

    @KafkaListener(topics = "create-documents", groupId = "messageGroup")
    public void consumeCreateDocuments(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        String emailBody = String.format(
                "%s, we are currently creating your loan documentation.", application.getClient().getFirstName()
        );

        mailService.sendEmail(emailMessage.getAddress(), theme.toString(), emailBody);
    }

    @KafkaListener(topics = "send-documents", groupId = "messageGroup")
    public void consumeSendDocuments(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        List<File> documents = documentService.createDocuments(application);

        documents.forEach(document -> mailService.sendDocument(emailMessage.getAddress(), document));
    }

    @KafkaListener(topics = "send-ses", groupId = "messageGroup")
    public void consumeSendSes(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        mailService.sendSes(emailMessage.getAddress(), application.getSesCode().toString());
    }

    @KafkaListener(topics = "credit-issued", groupId = "messageGroup")
    public void consumeCreditIssued(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        String emailBody = String.format(
                "%s, we are pleased to tell you, your loan is approved.", application.getClient().getFirstName()
        );

        mailService.sendEmail(emailMessage.getAddress(), theme.toString(), emailBody);
    }

    @KafkaListener(topics = "application-denied", groupId = "messageGroup")
    public void consumeApplicationDenied(String message) throws JsonProcessingException {
        EmailMessageDTO emailMessage = getEmailMessage(message);
        Theme theme = emailMessage.getTheme();
        log.info(String.format("Client received message with subject: %s", theme));

        Application application = dealClient.getApplicationById(emailMessage.getApplicationID());
        String emailBody = String.format(
                "%s, Sorry! your loan application was not successful.", application.getClient().getFirstName()
        );

        mailService.sendEmail(emailMessage.getAddress(), theme.toString(), emailBody);
    }

    private EmailMessageDTO getEmailMessage(String msg) {
        EmailMessageDTO emailMessageDTO = null;
        try {
            emailMessageDTO = objectMapper.readValue(msg, EmailMessageDTO.class);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return emailMessageDTO;
    }

}
