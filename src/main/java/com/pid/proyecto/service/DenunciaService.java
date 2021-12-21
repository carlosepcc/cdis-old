package com.pid.proyecto.service;

import com.pid.proyecto.entity.Denuncia;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.repository.DenunciaRepository;
import java.util.List;
import java.util.Optional;
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
    public void save(Denuncia denuncia) {

        denunciaRepo.save(denuncia);
    }
    
    public boolean existsById(int id) {
        return denunciaRepo.existsByIddenuncia(id);
    }
    
    public Optional<Denuncia> getByIddenuncia(int id) {

        return denunciaRepo.findByIddenuncia(id);
    }
}
