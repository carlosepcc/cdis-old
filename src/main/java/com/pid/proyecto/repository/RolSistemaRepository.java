package com.pid.proyecto.repository;

import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.seguridad.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolSistemaRepository extends JpaRepository<RolSistema, Integer> {

    // buscamos el rol por el nombre del rol (rol)
    Optional<RolSistema> findByRol(RolNombre rol);

}
