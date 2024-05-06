package com.codigo.msgarcialuyo.infraestructure.dao;

import com.codigo.msgarcialuyo.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {

}
