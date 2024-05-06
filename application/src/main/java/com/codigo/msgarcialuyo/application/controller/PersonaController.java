package com.codigo.msgarcialuyo.application.controller;


import com.codigo.msgarcialuyo.domain.aggregates.dto.PersonaDto;
import com.codigo.msgarcialuyo.domain.aggregates.request.PersonaRequest;
import com.codigo.msgarcialuyo.domain.ports.in.PersonaServiceIn;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ms-garcia-luyo/v1/persona")
@AllArgsConstructor
public class PersonaController {

    private final PersonaServiceIn personaServiceIn;

    @PostMapping
    public ResponseEntity<PersonaDto> registrar(@RequestBody PersonaRequest personaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.crearIn(personaRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> obtenerPorId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.obtenerIn(id).get());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PersonaDto>> listar(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.listarIn());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> actualizar(@PathVariable Long id,@RequestBody PersonaRequest personaRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.actualizarIn(id,personaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonaDto> eliminar(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personaServiceIn.eliminarIn(id));
    }


}
