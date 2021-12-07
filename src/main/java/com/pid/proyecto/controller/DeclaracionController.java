package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Declaracion;
import com.pid.proyecto.service.DeclaracionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Declaracion")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class DeclaracionController {
    
    @Autowired
    DeclaracionService declaracionService;
    
    @GetMapping("/listarDeclaracion")
    public ResponseEntity<List<Declaracion>> list() {
        List<Declaracion> list = declaracionService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
}
