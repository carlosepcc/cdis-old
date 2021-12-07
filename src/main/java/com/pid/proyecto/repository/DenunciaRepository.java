package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Integer>{
    
}
