package com.enfint.dossierservice.entity;

import com.enfint.dossierservice.payload.EmploymentDTO;
import com.enfint.dossierservice.utils.GenderEnum;
import com.enfint.dossierservice.utils.MaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthDate;
    private String email;
    private GenderEnum gender;
    private MaritalStatusEnum maritalStatus;
    private Integer dependentNumber;
    private String passportSeries;
    private String passportNumber;
    private EmploymentDTO employment;
    private Application application;
    private String account;
}
