package com.enfint.dossierservice.entity;

import com.enfint.dossierservice.payload.ApplicationStatusHistoryDTO;
import com.enfint.dossierservice.payload.LoanOfferDTO;
import com.enfint.dossierservice.utils.ApplicationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    private Long id;
    private Client client;
    private Credit credit;
    private ApplicationStatusEnum status;
    private LocalDate creationDate;
    private LoanOfferDTO appliedOffer;
    private LocalDate signDate;
    private Integer sesCode;
    private List<ApplicationStatusHistoryDTO> statusHistory;
}
