package com.pid.proyecto.controller;

import com.pid.proyecto.entity.ComdiscUsuario;
import com.pid.proyecto.entity.ComdiscUsuarioPK;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.dto.NuevoComDiscUsuario;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.service.ComDiscUsuarioService;
import com.pid.proyecto.service.RolSistemaService;
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
@RequestMapping("/ComDiscUsuario")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class ComDiscUsuarioController {

    @Autowired
    RolSistemaService rolSistemaService;

    @Autowired
    ComDiscUsuarioService comDiscUsuarioService;

    @GetMapping("/listarComDiscUsuario")
    public ResponseEntity<List<ComdiscUsuario>> list() {
        List<ComdiscUsuario> list = comDiscUsuarioService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearComDisUsuario")
    @PreAuthorize("hasRole('ROLE_ADMIN') "
            + "or hasRole('ROLE_DECANO')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevoComDiscUsuario nuevoComDiscUsuario, BindingResult bindingResult) {

        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

// si todo esta bien creamos la comision
        ComdiscUsuario comDiscUsuario;

        ComdiscUsuarioPK comDiscUsuarioPK = new ComdiscUsuarioPK(nuevoComDiscUsuario.getIdComision(), nuevoComDiscUsuario.getIdComision());

        if (nuevoComDiscUsuario.getRol().contains("jefe")) {
            comDiscUsuario = new ComdiscUsuario(comDiscUsuarioPK, RolNombre.ROLE_JEFE.toString());
        } else {
            comDiscUsuario = new ComdiscUsuario(comDiscUsuarioPK, RolNombre.ROLE_INTEGRANTE.toString());
        }
        comDiscUsuarioService.save(comDiscUsuario);

        return new ResponseEntity(new Mensaje("INSTANCIA CREADA"), HttpStatus.CREATED);
    }

}
