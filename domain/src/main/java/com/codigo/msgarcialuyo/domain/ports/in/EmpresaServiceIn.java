package com.codigo.msgarcialuyo.domain.ports.in;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceIn {
    EmpresaDto crearIn(EmpresaRequest empresaRequest);

    Optional<EmpresaDto> obtenerIn(Long id);

    List<EmpresaDto> listarIn();

    EmpresaDto actualizarIn(Long id,EmpresaRequest empresaRequest);

    EmpresaDto eliminarIn(Long id);
}
