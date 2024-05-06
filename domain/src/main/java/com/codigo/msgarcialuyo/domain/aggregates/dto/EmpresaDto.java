package com.codigo.msgarcialuyo.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmpresaDto {
    private Long idEmpresa;
    private String razonSocial;
    private String tipoDocu;
    private String numDocu;
    private Integer estado;
    private String condicion;
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private boolean EsAgenteRetencion;
    private String usuaCreate;
    private Timestamp dateCreate;
    private String usuaModif;
    private Timestamp dateModif;
    private String usuaDelete;
    private Timestamp dateDelete;
}
