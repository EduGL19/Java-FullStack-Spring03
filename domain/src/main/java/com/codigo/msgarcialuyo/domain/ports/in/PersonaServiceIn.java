package com.codigo.msgarcialuyo.domain.ports.in;

import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.PersonaRequest;

import java.util.List;
import java.util.Optional;

public interface PersonaServiceIn {

    PersonaDto crearIn(PersonaRequest personaRequest);

    Optional<PersonaDto> obtenerIn(Long id);

    List<PersonaDto> listarIn();

    PersonaDto actualizarIn(Long id,PersonaRequest personaRequest);

    PersonaDto eliminarIn(Long id);

}
