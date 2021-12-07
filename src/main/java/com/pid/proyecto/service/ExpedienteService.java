package com.pid.proyecto.service;

import com.pid.proyecto.entity.Expediente;
import com.pid.proyecto.repository.ExpedienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento y la coherencia de las consultas
public class ExpedienteService {
    
    @Autowired
    ExpedienteRepository expedienteRepository;
    
    public List<Expediente> Listar() {
        
        return expedienteRepository.findAll();
    }
}
