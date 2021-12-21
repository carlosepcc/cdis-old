package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Expediente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpedienteRepository extends JpaRepository<Expediente, Integer>{

    public Optional<Expediente> findByIdexpediente(int id);
    
}
