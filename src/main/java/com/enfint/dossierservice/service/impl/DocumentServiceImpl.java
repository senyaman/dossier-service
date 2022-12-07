package com.enfint.dossierservice.service.impl;

import com.enfint.dossierservice.entity.Application;
import com.enfint.dossierservice.entity.Client;
import com.enfint.dossierservice.entity.Credit;
import com.enfint.dossierservice.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

    @Override
    public List<File> createDocuments(Application application) {
        File creditDocument = null;
        File applicationDocument = null;
        File clientDocument = null;

        Credit credit = application.getCredit();
        Client client = application.getClient();
        creditDocument = getCreditDocument(credit);
        applicationDocument = getApplicationDocument(application);
        clientDocument = getClientDocument(client);

        return List.of(creditDocument, applicationDocument, clientDocument);
    }

    private File getCreditDocument(Credit credit) {
        Map<String, String> info = new HashMap<>();
        info.put("id", credit.getId().toString());
        info.put("amount", credit.getAmount().toString());
        info.put("term", credit.getTerm().toString());
        info.put("monthlyPayment", credit.getMonthlyPayment().toString());
        info.put("rate", credit.getRate().toString());
        info.put("psk", credit.getPsk().toString());
        info.put("paymentSchedule", credit.getPaymentSchedule().toString());
        info.put("creditStatus", credit.getCreditStatus().toString());

        List<String> creditInfo = formatInfo(info);
        Path path = null;

        try {
            Path creditDocument = Files.createTempFile("CreditDoc", ".txt");
            path = Files.write(creditDocument, creditInfo);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        assert path != null;
        return path.toFile();
    }

    private File getApplicationDocument(Application application) {
        Map<String, String> info = new HashMap<>();
        info.put("id", application.getId().toString());
        info.put("client", application.getClient().toString());
        info.put("status", application.getStatus().toString());
        info.put("creationDate", application.getCreationDate().toString());
        info.put("appliedOffer", application.getAppliedOffer().toString());
        info.put("applicationHistory", application.getStatusHistory().toString());

        List<String> applicationInfo = formatInfo(info);
        Path path = null;

        try {
            Path applicationDocument = Files.createTempFile("ApplicationDoc", ".txt");
            path = Files.write(applicationDocument, applicationInfo);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        assert path != null;
        return path.toFile();
    }

    private List<String> formatInfo(Map<String, String> data) {
        return data.entrySet()
                .stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .collect(Collectors.toList());
    }

    private File getClientDocument(Client client) {
        Map<String, String> info = new HashMap<>();
        info.put("firstName", client.getFirstName());
        info.put("lastName", client.getLastName());
        info.put("middleName", client.getMiddleName());
        info.put("birthDate", client.getBirthDate().toString());
        info.put("email", client.getEmail());
        info.put("gender", client.getGender().toString());
        info.put("maritalStatus", client.getMaritalStatus().toString());
        info.put("dependentAmount", client.getDependentNumber().toString());
        info.put("passportNumber", client.getPassportNumber());
        info.put("passportSeries", client.getPassportSeries());
        info.put("employment", client.getEmployment().toString());
        info.put("account", client.getAccount());

        List<String> clientInfo = formatInfo(info);
        Path path = null;

        try {
            Path clientDocument = Files.createTempFile("ClientDoc", ".txt");
            path = Files.write(clientDocument, clientInfo);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        assert path != null;
        return path.toFile();
    }
}
