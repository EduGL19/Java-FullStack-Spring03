package com.codigo.msgarcialuyo.domain.impl;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.EmpresaRequest;
import com.codigo.msgarcialuyo.domain.ports.in.EmpresaServiceIn;
import com.codigo.msgarcialuyo.domain.ports.out.EmpresaServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaServiceIn {

    private final EmpresaServiceOut empresaServiceOut;

    @Override
    public EmpresaDto crearIn(EmpresaRequest empresaRequest) {
        return empresaServiceOut.crearOut(empresaRequest);
    }

    @Override
    public Optional<EmpresaDto> obtenerIn(Long id) {
        return empresaServiceOut.obtenerOut(id);
    }

    @Override
    public List<EmpresaDto> listarIn() {
        return empresaServiceOut.listarOut();
    }

    @Override
    public EmpresaDto actualizarIn(Long id, EmpresaRequest empresaRequest) {
        return empresaServiceOut.actualizarOut(id,empresaRequest);
    }

    @Override
    public EmpresaDto eliminarIn(Long id) {
        return empresaServiceOut.eliminarOut(id);
    }
}
