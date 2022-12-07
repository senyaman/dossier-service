package com.enfint.dossierservice.payload;

import com.enfint.dossierservice.utils.ApplicationStatusEnum;
import com.enfint.dossierservice.utils.ChangeTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationStatusHistoryDTO {

    private ApplicationStatusEnum applicationStatus;
    private LocalDateTime time;
    private ChangeTypeEnum changeType;
}
