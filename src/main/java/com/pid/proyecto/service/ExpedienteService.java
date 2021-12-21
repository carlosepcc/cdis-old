package com.pid.proyecto.service;

import com.pid.proyecto.entity.Expediente;
import com.pid.proyecto.repository.ExpedienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento y la coherencia de las consultas
public class ExpedienteService {

    @Autowired
    ExpedienteRepository expedienteRepo;

    public List<Expediente> Listar() {

        return expedienteRepo.findAll();
    }

    public void save(Expediente expediente) {
        
        expedienteRepo.save(expediente);
    }
    
     public Optional<Expediente> getByIdexpediente(int id) {

        return expedienteRepo.findByIdexpediente(id);
    }
}
