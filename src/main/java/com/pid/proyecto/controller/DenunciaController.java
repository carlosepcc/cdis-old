package com.pid.proyecto.controller;

import com.pid.proyecto.entity.Denuncia;
import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.auxiliares.ConvertidorToListEntity;
import com.pid.proyecto.seguridad.auxiliares.Filtrador;
import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.auxiliares.SesionDetails;
import com.pid.proyecto.seguridad.dto.NuevaDenuncia;
import com.pid.proyecto.service.DenunciaService;
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
@RequestMapping("/Denuncia")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class DenunciaController {

    @Autowired
    DenunciaService denunciaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ConvertidorToListEntity convertidor;

    @Autowired
    SesionDetails sesionDetails;

    @Autowired
    Filtrador filtrador;

    @GetMapping("/listarDenuncia")
    public ResponseEntity<List<Denuncia>> list() {
        List<Denuncia> list = denunciaService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearDenuncia")
    @PreAuthorize("hasRole('ROLE_ADMIN')"
            + " or hasRole('ROLE_TRABAJADOR')"
            + " or hasRole('ROLE_DECANO')"
            + " or hasRole('ROLE_PROFESOR')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevaDenuncia nuevaDenuncia, BindingResult bindingResult) {

        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }
        
        // obtenemos del json el usuario del denunciante
        String denunciante = nuevaDenuncia.getDenunciante();
        // obtenemos el usuario que se encuentra ahora en sesion
        String usuario = sesionDetails.getUsuario();

        // verificamos que el valor introducido en el json para 
        // el denunciante exista en nuestra base de datos
        if (!usuarioService.existsByUsuario(denunciante)) {
            return new ResponseEntity<>(new Mensaje("ESE DENUNCIANTE NO EXISTE"), HttpStatus.BAD_REQUEST);
        }

        // verificamos que los valores introducidos en el json para 
        // los estudiantes existan en nuestra base de datos
        if (convertidor.convertirListaStringToListaUsuario(nuevaDenuncia.getEstudiantes()) == null) {
            return new ResponseEntity<>(new Mensaje("USUARIOS INCORRECTOS EN LOS ESTUDIANTES"), HttpStatus.BAD_REQUEST);
        }

        // verificamos que la lista sea solo de usuarios estudiantes
        if (!filtrador.soloEstudiantes(convertidor.convertirListaStringToListaUsuario(nuevaDenuncia.getEstudiantes()))) {
            return new ResponseEntity<>(new Mensaje("SOLO PUEDE DENUNCIAR A ESTUDIANTES"), HttpStatus.BAD_REQUEST);
        }

        // preparamos una lista de roles para verificar la autoridad en el sistema del denunciante
        List<RolSistema> rolDenunciante;
        rolDenunciante = new LinkedList<>();
        rolDenunciante.addAll(usuarioService.getByUsuario(denunciante).get().getRolsistemaList());

        // si el usuario introducido en el json no coincide con el que esta en sesion
        if (!denunciante.equals(usuario)) {
            boolean isAdmin = false;
            if (sesionDetails.getPrivilegios().contains("ROLE_ADMIN") || sesionDetails.getPrivilegios().contains("ROLE_DECANO")) {
                isAdmin = true;
            }
            // si no es decano o admin pues no podra hacer denuncias a nombre de nadie mas
            if (isAdmin == false) {
                return new ResponseEntity<>(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA REALIZAR UNA DENUNCIA CON UN USUARIO AJENO"), HttpStatus.BAD_REQUEST);
            }
        }
        // creamos la denuncia parcialmente
        Denuncia denuncia = new Denuncia(nuevaDenuncia.getDescripcion(), new Date());

        // preparamos la lista de relaciones de nuestra denuncia con los usuarios
        List<Usuario> u = new LinkedList<>();

        // añadimos el objeto usuario de nuestro denunciante a la relacion con esta denuncia 
        // pues ya sabemos que existe dicho usuario
        u.add(usuarioService.getByUsuario(denunciante).get());

        // añadimos la lista de estudiantes a nuestra variable que ya tiene el denunciante
        u.addAll(convertidor.convertirListaStringToListaUsuario(nuevaDenuncia.getEstudiantes()));

        // añadimos la lista con todas nuestras relaciones a la denuncia que se está creando
        denuncia.setUsuarioList(u);

        // salvamos la denuncia
        denunciaService.save(denuncia);
        return new ResponseEntity(new Mensaje("DENUNCIA GUARDADA"), HttpStatus.CREATED);
    }

}
