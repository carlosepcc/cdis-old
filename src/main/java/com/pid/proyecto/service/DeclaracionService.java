package com.pid.proyecto.service;

import com.pid.proyecto.entity.Declaracion;
import com.pid.proyecto.repository.DeclaracionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento y la coherencia de las consultas
public class DeclaracionService {
    
    @Autowired
    DeclaracionRepository declaracionRepo;
    
    public List<Declaracion> Listar() {
        
        return declaracionRepo.findAll();
    }
    
    public void save(Declaracion declaracion) {

        declaracionRepo.save(declaracion);
    }
}
