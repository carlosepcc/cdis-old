package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Denuncia;
import com.pid.proyecto.service.DenunciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Denuncia")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class DenunciaController {
    
    @Autowired
    DenunciaService denunciaService;
    
    @GetMapping("/listarDenuncia")
    public ResponseEntity<List<Denuncia>> list() {
        List<Denuncia> list = denunciaService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
