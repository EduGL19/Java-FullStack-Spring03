package com.codigo.msgarcialuyo.domain.aggregates.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonaDto {
    private Long idPersona;
    private String nombres;
    private String apellidos;
    private String tipoDocu;
    private String numDocu;
    private String email;
    private String telefono;
    private String direccion;
    private Integer estado;
    private String usuaCreate;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelete;
    private Timestamp dateDelete;
}
