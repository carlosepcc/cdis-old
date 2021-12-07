package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Caso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Integer>{
    
}
