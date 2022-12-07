package com.enfint.dossierservice.payload;

import com.enfint.dossierservice.utils.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessageDTO {
    private Long applicationID;
    private String address;
    private Theme theme;
}
