package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Comisiondisciplinaria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ComisionDisciplinariaRepository extends JpaRepository<Comisiondisciplinaria, Integer>{

    public void deleteByIdcomision(int id);

    public boolean existsByIdcomision(int id);

    public Optional<Comisiondisciplinaria> findByIdcomision(int id);
    
}
