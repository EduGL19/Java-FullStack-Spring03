package com.codigo.msgarcialuyo.infraestructure.util;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

    public  static  String convertirAString(PersonaDto personaDto){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(personaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public  static  String convertirAStringEmpresa(EmpresaDto empresaDto){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(empresaDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public  static  <T> T convertirDesdeString(String json,Class<T> tipoClase){

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json,tipoClase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
