package com.codigo.msgarcialuyo.infraestructure.client;

import com.codigo.msgarcialuyo.domain.aggregates.dto.ReniecDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.SunatDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="client-reniec",url="https://api.apis.net.pe/v2/reniec/")
public interface ClientReniec {

    @GetMapping("/dni")
    ReniecDto obtenerInfoReniec(@RequestParam("numero") String numero,
                                @RequestHeader("Authorization") String authorization);

}
