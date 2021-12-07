package com.pid.proyecto.service;

import com.pid.proyecto.entity.Denuncia;
import com.pid.proyecto.repository.DenunciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //mejora el rendimiento y la coherencia de las consultas
public class DenunciaService {
    
    @Autowired
    DenunciaRepository denunciaRepo;
    
    public List<Denuncia> Listar() {
        
        return denunciaRepo.findAll();
    }
}
