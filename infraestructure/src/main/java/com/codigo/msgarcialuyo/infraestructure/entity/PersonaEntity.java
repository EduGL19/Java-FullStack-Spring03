package com.codigo.msgarcialuyo.infraestructure.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="persona")
@Getter
@Setter
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPersona;

    @Column(nullable = false,length = 255)
    private String nombre;

    @Column(nullable = false,length = 255)
    private String apellido;

    @Column(name = "tipodocumento", nullable = false,length = 5)
    private String tipoDocumento;

    @Column(name = "numerodocumento", nullable = false,length = 20)
    private String numeroDocumento;

    @Column(nullable = false,length = 255)
    private String email;

    @Column(length = 15)
    private String telefono;

    private String direccion;

    @Column(nullable = false,length = 1)
    private Integer estado;

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

    @Column(name = "datedelet",nullable = true)
    private Timestamp dateDelet;

    //@Column(name = "empresa_id",nullable = true,length = 255)
    //private Long idEmpresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id",referencedColumnName="id",nullable = false,insertable = false)
    private EmpresaEntity empresa;

}
