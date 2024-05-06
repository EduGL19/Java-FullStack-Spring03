package com.codigo.msgarcialuyo.domain.ports.out;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.EmpresaRequest;

import java.util.List;
import java.util.Optional;

public interface EmpresaServiceOut {
    EmpresaDto crearOut(EmpresaRequest empresaRequest);

    Optional<EmpresaDto> obtenerOut(Long id);

    List<EmpresaDto> listarOut();

    EmpresaDto actualizarOut(Long id,EmpresaRequest empresaRequest);

    EmpresaDto eliminarOut(Long id);
}
