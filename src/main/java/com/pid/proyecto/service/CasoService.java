package com.pid.proyecto.service;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.repository.CasoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento y la coherencia de las consultas
public class CasoService {
    
    @Autowired
    CasoRepository casoRepo;
    
     public List<Caso> Listar() {
        
        return casoRepo.findAll();
    }
    
}
