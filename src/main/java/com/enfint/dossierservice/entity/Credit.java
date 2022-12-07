package com.enfint.dossierservice.entity;

import com.enfint.dossierservice.payload.PaymentScheduleElement;
import com.enfint.dossierservice.utils.CreditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Credit {
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal monthlyPayment;
    private BigDecimal rate;
    private BigDecimal psk;
    private List<PaymentScheduleElement> paymentSchedule;
    private CreditStatusEnum creditStatus;
}
