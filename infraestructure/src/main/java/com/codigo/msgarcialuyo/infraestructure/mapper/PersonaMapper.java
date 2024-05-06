package com.codigo.msgarcialuyo.infraestructure.mapper;

import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.infraestructure.entity.PersonaEntity;

public class PersonaMapper {

    public static PersonaDto fromEntity(PersonaEntity personaEntity){

        PersonaDto dto = new PersonaDto();

        dto.setIdPersona(personaEntity.getIdPersona());
        dto.setNombres(personaEntity.getNombre());
        dto.setApellidos(personaEntity.getApellido());
        dto.setTipoDocu(personaEntity.getTipoDocumento());
        dto.setNumDocu(personaEntity.getNumeroDocumento());
        dto.setEmail(personaEntity.getEmail());
        dto.setTelefono(personaEntity.getTelefono());
        dto.setDireccion(personaEntity.getDireccion());
        dto.setEstado(personaEntity.getEstado());
        dto.setUsuaCreate(personaEntity.getUsuaCrea());
        dto.setDateCreate(personaEntity.getDateCreate());
        dto.setUsuaModif(personaEntity.getUsuaModif());
        dto.setDateModif(personaEntity.getDateModif());
        dto.setUsuaDelete(personaEntity.getUsuaDelet());
        dto.setDateDelete(personaEntity.getDateDelet());

        return dto;

    }

}
