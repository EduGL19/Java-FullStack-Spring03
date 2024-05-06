package com.codigo.msgarcialuyo.domain.ports.out;

import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceOut {

    PersonaDto crearOut(PersonaRequest personaRequest);

    Optional<PersonaDto> obtenerOut(Long id);

    List<PersonaDto> listarOut();

    PersonaDto actualizarOut(Long id,PersonaRequest personaRequest);

    PersonaDto eliminarOut(Long id);

}
