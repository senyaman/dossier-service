package com.enfint.dossierservice.feignclient;

import com.enfint.dossierservice.entity.Application;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "deal-service", url = "http://localhost:8081/deal")
public interface DealClient {

    @GetMapping("/admin/application/{applicationId}")
    Application getApplicationById (@PathVariable Long applicationId);
}
