package com.codigo.msgarcialuyo.infraestructure.mapper;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.infraestructure.entity.EmpresaEntity;

public class EmpresaMapper {

    public static EmpresaDto fromEntity(EmpresaEntity empresaEntity){

        EmpresaDto dto = new EmpresaDto();

        dto.setIdEmpresa(empresaEntity.getIdEmpresa());
        dto.setRazonSocial(empresaEntity.getRazonSocial());
        dto.setTipoDocu(empresaEntity.getTipoDocumento());
        dto.setNumDocu(empresaEntity.getNumeroDocumento());
        dto.setEstado(empresaEntity.getEstado());
        dto.setCondicion(empresaEntity.getCondicion());
        dto.setDireccion(empresaEntity.getDireccion());
        dto.setDistrito(empresaEntity.getDistrito());
        dto.setProvincia(empresaEntity.getProvincia());
        dto.setDepartamento(empresaEntity.getDepartamento());
        dto.setEsAgenteRetencion(empresaEntity.getEsAgenteRetencion());
        dto.setUsuaCreate(empresaEntity.getUsuaCrea());
        dto.setDateCreate(empresaEntity.getDateCreate());
        dto.setUsuaModif(empresaEntity.getUsuaModif());
        dto.setDateModif(empresaEntity.getDateModif());
        dto.setUsuaDelete(empresaEntity.getUsuaDelet());
        dto.setDateDelete(empresaEntity.getDateDelet());

        return dto;

    }

}
