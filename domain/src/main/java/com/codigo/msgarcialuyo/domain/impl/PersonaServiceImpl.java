package com.codigo.msgarcialuyo.domain.impl;

import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.PersonaRequest;
import com.codigo.msgarcialuyo.domain.ports.in.PersonaServiceIn;
import com.codigo.msgarcialuyo.domain.ports.out.PersonaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonaServiceImpl implements PersonaServiceIn {

    private  final PersonaServiceOut personaServiceOut;

    @Override
    public PersonaDto crearIn(PersonaRequest personaRequest) {
        return personaServiceOut.crearOut(personaRequest);
    }

    @Override
    public Optional<PersonaDto> obtenerIn(Long id) {
        return personaServiceOut.obtenerOut(id);
    }

    @Override
    public List<PersonaDto> listarIn() {
        return personaServiceOut.listarOut();
    }

    @Override
    public PersonaDto actualizarIn(Long id, PersonaRequest personaRequest) {
        return personaServiceOut.actualizarOut(id,personaRequest);
    }

    @Override
    public PersonaDto eliminarIn(Long id) {
        return personaServiceOut.eliminarOut(id);
    }
}
