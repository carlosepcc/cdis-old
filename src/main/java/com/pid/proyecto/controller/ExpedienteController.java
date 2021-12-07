package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Expediente;
import com.pid.proyecto.service.ExpedienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Expediente")
@CrossOrigin("*")
public class ExpedienteController {
    
    @Autowired
    ExpedienteService expedienteService;
    
    @GetMapping("/listarExpediente")
    public ResponseEntity<List<Expediente>> list() {
        List<Expediente> list = expedienteService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
}
