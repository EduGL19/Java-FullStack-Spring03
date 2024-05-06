package com.codigo.msgarcialuyo.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaRequest {
    private Integer tipoDoc;
    private String numDoc;
    private String email;
    private String telefono;
    private String direccion;

}
