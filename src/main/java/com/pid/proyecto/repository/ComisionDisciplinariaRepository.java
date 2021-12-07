package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Comisiondisciplinaria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComisionDisciplinariaRepository extends JpaRepository<Comisiondisciplinaria, Integer>{

    public void deleteByIdcomision(int id);
    
}
