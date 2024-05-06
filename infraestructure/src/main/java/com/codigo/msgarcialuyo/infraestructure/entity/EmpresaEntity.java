package com.codigo.msgarcialuyo.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="empresa_info")
@Getter
@Setter
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEmpresa;

    @Column(name="razonsocial",nullable = false,length = 255)
    private String razonSocial;

    @Column(name="tipodocumento",nullable = false,length = 5)
    private String tipoDocumento;

    @Column(name="numerodocumento",nullable = false,length = 20)
    private String numeroDocumento;

    @Column(nullable = false,length = 1)
    private Integer estado;

    @Column(nullable = false,length = 50)
    private String condicion;

    private String direccion;

    @Column(nullable = true,length = 100)
    private String distrito;

    @Column(nullable = true,length = 100)
    private String provincia;

    @Column(nullable = true,length = 100)
    private String departamento;

    @Column(name="esagenteretencion",nullable = false)
    private boolean EsAgenteRetencion;

    @Column(name="usuacrea",nullable = false,length = 255)
    private String usuaCrea;

    @Column(name="datecreate",nullable = false)
    private Timestamp dateCreate;

    @Column(name="usuamodif",nullable = true,length = 255)
    private String usuaModif;

    @Column(name="datemodif",nullable = true)
    private Timestamp dateModif;

    @Column(name="usuadelet",nullable = true,length = 255)
    private String usuaDelet;

    @Column(name="datedelet",nullable = true)
    private Timestamp dateDelet;



}
