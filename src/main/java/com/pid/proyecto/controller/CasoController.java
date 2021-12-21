package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.entity.Comisiondisciplinaria;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.dto.NuevoCaso;
import com.pid.proyecto.service.CasoService;
import com.pid.proyecto.service.ComisionDisciplinariaService;
import com.pid.proyecto.service.DenunciaService;
import static java.util.Collections.list;
import java.util.Date;
import java.util.LinkedList;
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
@RequestMapping("/Caso")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class CasoController {

    @Autowired
    CasoService casoService;

    @Autowired
    ComisionDisciplinariaService comisionDisciplinariaService;

    @Autowired
    DenunciaService denunciaService;

    @GetMapping("/listarCaso")
    public ResponseEntity<List<Caso>> list() {
        List<Caso> list = casoService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearCaso")
    @PreAuthorize("hasRole('ROLE_ADMIN') "
            + "or hasRole('ROLE_DECANO')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevoCaso nuevoCaso, BindingResult bindingResult) {
        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

        // limita lo que el usuario escribe en el json para el estado a las letras a | c
        if (!((nuevoCaso.getEstado() == 'a') || (nuevoCaso.getEstado() == 'c'))) {
            return new ResponseEntity(new Mensaje("LOS ESTADOS ADMITIDOS SOLO DEBEN CONTENER UNA LETRA a | c"), HttpStatus.BAD_REQUEST);
        }
        // verificamos que exista la comision que intentamos asignar
        if (!comisionDisciplinariaService.existsById(nuevoCaso.getIdComision())) {
            return new ResponseEntity(new Mensaje("NO EXISTE LA COMISION"), HttpStatus.BAD_REQUEST);
        }
        // verificamos que exista la denuncia que intentamos asignar
        if (!denunciaService.existsById(nuevoCaso.getIdDenuncia())) {
            return new ResponseEntity(new Mensaje("NO EXISTE LA DENUNCIA"), HttpStatus.BAD_REQUEST);
        }
        // verificamos que esta denuncia no este ya siendo atendida 
        if (casoService.existsByIddenuncia(denunciaService.getByIddenuncia(nuevoCaso.getIdDenuncia()).get())) {
            return new ResponseEntity(new Mensaje("YA EXISTE UNA COMISION ASIGNADA PARA ESTA DENUNCIA"), HttpStatus.BAD_REQUEST);
        }

// si todo esta bien creamos el caso
        Caso caso;
        caso = new Caso(nuevoCaso.getEstado(), new Date());
        caso.setIddenuncia(denunciaService.getByIddenuncia(nuevoCaso.getIdDenuncia()).get());

        List<Comisiondisciplinaria> Lc = new LinkedList<>();
        Lc.add(comisionDisciplinariaService.getByIdcomision(nuevoCaso.getIdComision()).get());
        caso.setComisiondisciplinariaList(Lc);

        casoService.save(caso);
        return new ResponseEntity(new Mensaje("CASO GUARDADO"), HttpStatus.CREATED);
    }
}
