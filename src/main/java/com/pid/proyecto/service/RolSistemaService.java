package com.pid.proyecto.service;

import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.repository.RolSistemaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento de las consultas
public class RolSistemaService {

    // inyectamos
    @Autowired
    RolSistemaRepository rolSistemaRepository;

    public List<RolSistema> Listar() {
        
        return rolSistemaRepository.findAll();
    }
    
    // devolvemos el rol con nombre (rolNombre)
    public Optional<RolSistema> getByRol(RolNombre rolNombre) {
        return rolSistemaRepository.findByRol(rolNombre);
    }

    
    public void save(RolSistema rol) {
    
    rolSistemaRepository.save(rol);
        
    }
public boolean existsByRol(RolNombre rol) {

        return rolSistemaRepository.existsByRol(rol);

    }
}
