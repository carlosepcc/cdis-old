package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Caso;
import com.pid.proyecto.entity.Expediente;
import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.auxiliares.ConvertidorToListEntity;
import com.pid.proyecto.seguridad.auxiliares.Filtrador;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.dto.NuevoExpediente;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.service.CasoService;
import com.pid.proyecto.service.DenunciaService;
import com.pid.proyecto.service.ExpedienteService;
import com.pid.proyecto.service.RolSistemaService;
import com.pid.proyecto.service.UsuarioService;
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
@RequestMapping("/Expediente")
@CrossOrigin("*")
public class ExpedienteController {

    @Autowired
    ExpedienteService expedienteService;

    @Autowired
    CasoService casoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    DenunciaService denunciaService;

    @Autowired
    RolSistemaService rolSistemaService;

    @Autowired
    ConvertidorToListEntity convertidorToListEntity;

    @Autowired
    Filtrador filtrador;

    @GetMapping("/listarExpediente")
    public ResponseEntity<List<Expediente>> list() {
        List<Expediente> list = expedienteService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearExpediente")
    @PreAuthorize("hasRole('ROLE_ADMIN') "
            + "or hasRole('ROLE_DECANO')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevoExpediente nuevoExpediente, BindingResult bindingResult) {

        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

        // verificamos que exista el caso
        if (!casoService.existByIdcaso(nuevoExpediente.getIdCaso())) {
            return new ResponseEntity<>(new Mensaje("NO EXISTE EL CASO ENUNCIADO " + nuevoExpediente.getIdCaso()), HttpStatus.BAD_REQUEST);
        }

        // guardamos el caso
        Caso caso = casoService.getById(nuevoExpediente.getIdCaso()).get();

        // verificamos que el usuario pasado en el json exista realmente
        if (!usuarioService.existsByUsuario(nuevoExpediente.getUsuario())) {
            return new ResponseEntity<>(new Mensaje("NO EXISTE EL ESTUDIANTE ESPECIFICADO"), HttpStatus.BAD_REQUEST);
        }

        List<Usuario> listaUsuarios = filtrador.listaEstudiantes(casoService.getById(caso.getIdcaso()).get()
                .getIddenuncia().getUsuarioList());

        // comprobamos que todos los usuarios pertenezcan al caso en cuestion
        boolean existeEnCaso = false;
        for (Usuario u : listaUsuarios) {

            if (u.getUsuario().equals(nuevoExpediente.getUsuario())) {
                existeEnCaso = true;
            }
        }
        
        // si no se encuentra el usuario dentro del caso analizado entonces es un error 
        if (!existeEnCaso) {
            return new ResponseEntity<>(new Mensaje("ESTE USUARIO NO PERTENECE AL CASO ANALIZADO"), HttpStatus.BAD_REQUEST);
        }

        // añadimos el usuario del que vamos a crear el expediente
        Usuario usuario = usuarioService.getByUsuario(nuevoExpediente.getUsuario()).get();

        // sacamos la anterior lista de expedientes del caso en cuestion
        List<Expediente> Le = caso.getExpedienteList();

        // si todo esta bien creamos el expediente
        Expediente expediente;
        expediente = new Expediente(nuevoExpediente.getDescripcion(), nuevoExpediente.getFecha());
        expediente.setUsuario(usuario);

        // guardamos el expediente
        expedienteService.save(expediente);

        // creamos la lista de expedientes
        Le.add(expedienteService.getByIdexpediente(expediente.getIdexpediente()).get());
        // agregamos al caso del que hablamos el expediente nuevo
        caso.setExpedienteList(Le);

        // actualizamos el caso
        casoService.save(caso);
        
        return new ResponseEntity(new Mensaje("EXPEDIENTE CREADO"), HttpStatus.CREATED);
    }

}
