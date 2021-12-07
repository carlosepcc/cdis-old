package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Comisiondisciplinaria;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.dto.NuevaComision;
import com.pid.proyecto.service.ComisionDisciplinariaService;
import com.pid.proyecto.service.RolSistemaService;
import com.pid.proyecto.service.UsuarioService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Comision")
@CrossOrigin("*")
public class ComisionDisciplinariaController {

    @Autowired
    RolSistemaService rolSistemaService;
    
    @Autowired
    ComisionDisciplinariaService ComisionDisciplinariaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listarComision")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Comisiondisciplinaria>> list() {
        List<Comisiondisciplinaria> list = ComisionDisciplinariaService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearComision")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevaComision nuevaComision, BindingResult bindingResult) {

        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

// si todo esta bien creamos la comision
        Comisiondisciplinaria comisionDisciplinaria;
        comisionDisciplinaria = new Comisiondisciplinaria(nuevaComision.getTipoComision(), nuevaComision.getFecha());

        ComisionDisciplinariaService.save(comisionDisciplinaria);

        int idComision = comisionDisciplinaria.getIdcomision();
        int idUsuario;
        // si ya existe el usuario
        if (usuarioService.existsByUsuario(nuevaComision.getUsuario())) {
            idUsuario = usuarioService.getByUsuario(nuevaComision.getUsuario()).get().getIdusuario();
        } else {
//            ComisionDisciplinariaService.delete(comisionDisciplinaria.getIdcomision());
            return new ResponseEntity<>(new Mensaje("ESE USUARIO NO EXISTE INTÉNTELO DE NUEVO"), HttpStatus.BAD_REQUEST);
        }
        
        
        return new ResponseEntity(new Mensaje("COMISION CREADA"), HttpStatus.CREATED);
    }
}