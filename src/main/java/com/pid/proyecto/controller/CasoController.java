package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.service.CasoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Caso")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class CasoController {
    
    @Autowired
    CasoService casoService;
    
    @GetMapping("/listarCaso")
    public ResponseEntity<List<Caso>> list() {
        List<Caso> list = casoService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
