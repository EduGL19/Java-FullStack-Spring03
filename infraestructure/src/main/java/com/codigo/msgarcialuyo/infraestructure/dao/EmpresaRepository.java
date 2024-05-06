package com.codigo.msgarcialuyo.infraestructure.dao;

import com.codigo.msgarcialuyo.infraestructure.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<EmpresaEntity,Long> {
}
