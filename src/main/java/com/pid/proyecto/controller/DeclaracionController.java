package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.entity.Declaracion;
import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.auxiliares.ConvertidorToListEntity;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.auxiliares.SesionDetails;
import com.pid.proyecto.seguridad.dto.NuevaDeclaracion;
import com.pid.proyecto.service.CasoService;
import com.pid.proyecto.service.DeclaracionService;
import com.pid.proyecto.service.UsuarioService;
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
@RequestMapping("/Declaracion")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class DeclaracionController {

    @Autowired
    DeclaracionService declaracionService;

    @Autowired
    SesionDetails sesionDetails;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ConvertidorToListEntity convertidor;

    @Autowired
    CasoService casoService;

    @GetMapping()
    public ResponseEntity<List<Declaracion>> list() {
        List<Declaracion> list = declaracionService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN') "
            + "or hasRole('ROLE_ESTUDIANTE')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevaDeclaracion nuevaDeclaracion, BindingResult bindingResult) {

        // obtenemos del json el usuario del estudiante
        String estudiante = nuevaDeclaracion.getEstudiante();
        // obtenemos el usuario que se encuentra ahora en sesion
        String usuario = sesionDetails.getUsuario();

        // verificamos que el valor introducido en el json para 
        // el estudiante exista en nuestra base de datos
        if (!usuarioService.existsByUsuario(estudiante)) {
            return new ResponseEntity<>(new Mensaje("ESE ESTUDIANTE NO EXISTE"), HttpStatus.BAD_REQUEST);
        }

        // preparamos una lista de roles para verificar la autoridad en el sistema del estudiante
        List<RolSistema> rolEstudiante;
        rolEstudiante = new LinkedList<>();
        rolEstudiante.addAll(usuarioService.getByUsuario(estudiante).get().getRolsistemaList());

        // si el usuario introducido en el json no coincide con el que esta en sesion
        if (!estudiante.equals(usuario)) {
            // si no es decano o admin pues no podra hacer denuncias a nombre de nadie mas
            return new ResponseEntity<>(new Mensaje("USTED NO PUEDE DECLARAR EN NOMBRE DE OTRO ESTUDIANTE"), HttpStatus.BAD_REQUEST);
        }
        // creamos la declaracion parcialmente
        Declaracion declaracion = new Declaracion(nuevaDeclaracion.getDescripcion(), new Date());

        // preparamos la lista de relaciones de nuestra declaracion con los usuarios
        List<Usuario> u = new LinkedList<>();

        // añadimos el objeto usuario de nuestro estudiante a la relacion con esta declaracion 
        // pues ya sabemos que existe dicho usuario
        u.add(usuarioService.getByUsuario(estudiante).get());

        // añadimos la lista con todas nuestras relaciones a la declaracion que se está creando
        declaracion.setUsuarioList(u);

        List<Caso> casoList = new LinkedList<>();
        casoList.add(casoService.getById(nuevaDeclaracion.getIdCaso()).get());

        declaracion.setCasoList(casoList);

        // salvamos la declaracion
        declaracionService.save(declaracion);
        return new ResponseEntity(new Mensaje("DECLARACION GUARDADA"), HttpStatus.CREATED);
    }
}
