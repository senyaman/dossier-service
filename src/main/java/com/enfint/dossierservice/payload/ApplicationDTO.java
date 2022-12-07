package com.enfint.dossierservice.payload;

import com.enfint.dossierservice.utils.ApplicationStatusEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ApplicationDTO {
    private Long id;
    private ClientDTO client;
    private CreditDTO credit;
    private ApplicationStatusEnum applicationStatus;
    private LocalDate creatingDate;
    private LocalDate signDate;
    private String sesCode;
    private List<ApplicationStatusHistoryDTO> applicationStatusHistory;
}
