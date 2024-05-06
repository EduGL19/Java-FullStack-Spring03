package com.codigo.msgarcialuyo.application.controller;

import com.codigo.msgarcialuyo.domain.aggregates.dto.EmpresaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.EmpresaRequest;
import com.codigo.msgarcialuyo.domain.ports.in.EmpresaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-garcia-luyo/v1/empresa")
@AllArgsConstructor
public class EmpresaController {

    private final EmpresaServiceIn empresaServiceIn;

    @PostMapping
    public ResponseEntity<EmpresaDto> registrar(@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.crearIn(empresaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> obtenerPorId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.obtenerIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpresaDto>> listar(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.listarIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDto> actualizar(@PathVariable Long id,@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.actualizarIn(id,empresaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmpresaDto> eliminar(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaServiceIn.eliminarIn(id));
    }


}
