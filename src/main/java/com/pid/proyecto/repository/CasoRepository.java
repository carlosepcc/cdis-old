package com.pid.proyecto.repository;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.entity.Denuncia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasoRepository extends JpaRepository<Caso, Integer>{

    public boolean existsByIdcaso(int id);

    public Optional<Caso> findByIdcaso(int id);

    public boolean existsByIddenuncia(Denuncia iddenuncia);
    
}
