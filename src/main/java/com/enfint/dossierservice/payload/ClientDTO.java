package com.enfint.dossierservice.payload;

import com.enfint.dossierservice.utils.GenderEnum;
import com.enfint.dossierservice.utils.MaritalStatusEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private GenderEnum gender;
    private LocalDate birthDate;
    private String passportSeries;
    private String passportNumber;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private MaritalStatusEnum maritalStatus;
    private Integer dependentAmount;
    private EmploymentDTO employment;
    private String account;
}
