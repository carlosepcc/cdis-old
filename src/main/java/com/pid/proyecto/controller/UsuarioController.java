package com.pid.proyecto.controller;

import com.pid.proyecto.seguridad.auxiliares.Mensaje;
import com.pid.proyecto.seguridad.dto.JwtDto;
import com.pid.proyecto.seguridad.dto.LoginUsuario;
import com.pid.proyecto.seguridad.dto.NuevoUsuario;
import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.auxiliares.SesionDetails;
import com.pid.proyecto.seguridad.dto.NuevoDelete;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.seguridad.jwt.JwtProvider;
import com.pid.proyecto.service.RolSistemaService;
import com.pid.proyecto.service.UsuarioService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//revisamos MainSecurity.class en el configure vemos la direccion para crear nuevo usuario
@RequestMapping("/Usuario")
//podemos acceder desde cualquier url
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolSistemaService rolSistemaService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    SesionDetails sesionDetails;

    @GetMapping()
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> list = usuarioService.Listar();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PutMapping("/crearUsuario")
    @PreAuthorize("hasRole('ROLE_ADMIN') "
            + "or hasRole('ROLE_DECANO')"
            + "or hasRole('ROLE_TRABAJADOR')"
            + "or hasRole('ROLE_PROFESOR')")
    public ResponseEntity<?> crear(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {

        // si tiene errores en el binding result
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

        // si ya existe el usuario
        if (usuarioService.existsByUsuario(nuevoUsuario.getUsuario())) {
            return new ResponseEntity<>(new Mensaje("ESE USUARIO YA EXISTE"), HttpStatus.BAD_REQUEST);
        }

        Set<RolSistema> roles = new HashSet<>();
        boolean estudiante = false;

        roles.add(rolSistemaService.getByRol(RolNombre.ROLE_USER).get());

        // VERIFICAMOS QUE SOLO SE PUEDAN CREAR ESTUDIANTES LIBREMENTE Y LOS ADMINISTRADORES SON LOS QUE CREAN LOS DEMAS USUARIOS
        if (nuevoUsuario.getRoles().contains("admin") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_ADMIN).get());
        }
        if (nuevoUsuario.getRoles().contains("admin") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO ADMINISTRADOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("decano") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_DECANO).get());
        }
        if (nuevoUsuario.getRoles().contains("decano") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO DECANO"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("profesor") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_PROFESOR).get());
        }
        if (nuevoUsuario.getRoles().contains("profesor") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO PROFESOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("trabajador") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_TRABAJADOR).get());
        }
        if (nuevoUsuario.getRoles().contains("trabajador") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO TRABAJADOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.isEstudiante() || nuevoUsuario.getRoles().contains("estudiante")) {
            estudiante = true;
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_ESTUDIANTE).get());

        }

// si todo esta bien creamos el usuario
        Usuario usuario;
        usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellidos(),
                nuevoUsuario.getUsuario(), passwordEncoder.encode(nuevoUsuario.getContrasena()), estudiante);

        usuario.setRolsistemaList(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("USUARIO GUARDADO"), HttpStatus.CREATED);
    }

    @PutMapping("/actualizarUsuario/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody NuevoUsuario nuevoUsuario) {

        // si todo esta bien creamos el usuario
        Usuario usuario = usuarioService.getByIdusuario(id).get();
        usuario.setNombre(nuevoUsuario.getNombre());
        usuario.setApellidos(nuevoUsuario.getApellidos());
        usuario.setUsuario(nuevoUsuario.getUsuario());
        usuario.setContrasena(passwordEncoder.encode(nuevoUsuario.getContrasena()));
        usuario.setEstudiante(nuevoUsuario.isEstudiante());

        Set<RolSistema> roles = new HashSet<>();

        roles.add(rolSistemaService.getByRol(RolNombre.ROLE_USER).get());
        boolean estudiante = false;
        // VERIFICAMOS QUE SOLO SE PUEDAN CREAR ESTUDIANTES LIBREMENTE Y LOS ADMINISTRADORES SON LOS QUE CREAN LOS DEMAS USUARIOS
        if (nuevoUsuario.getRoles().contains("admin") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_ADMIN).get());
        }
        if (nuevoUsuario.getRoles().contains("admin") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO ADMINISTRADOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("decano") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_DECANO).get());
        }
        if (nuevoUsuario.getRoles().contains("decano") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO DECANO"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("profesor") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_PROFESOR).get());
        }
        if (nuevoUsuario.getRoles().contains("profesor") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO PROFESOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.getRoles().contains("trabajador") && sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_TRABAJADOR).get());
        }
        if (nuevoUsuario.getRoles().contains("trabajador") && !sesionDetails.getPrivilegios().contains("ROLE_ADMIN")) {
            return new ResponseEntity(new Mensaje("USTED NO POSEE PRIVILEGIOS PARA CREAR UN USUARIO TRABAJADOR"), HttpStatus.BAD_REQUEST);
        }
        if (nuevoUsuario.isEstudiante() || nuevoUsuario.getRoles().contains("estudiante")) {
            estudiante = true;
            roles.add(rolSistemaService.getByRol(RolNombre.ROLE_ESTUDIANTE).get());

        }
        usuario.setRolsistemaList(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("USUARIO ACTUALIZADO"), HttpStatus.CREATED);
    }

    @DeleteMapping("/Delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> borrar(@Valid @RequestBody NuevoDelete nuevoDelete) {

        for (int i: nuevoDelete.getListaId()) {
            if (!usuarioService.existsById(i)) {
                return new ResponseEntity(new Mensaje("NO EXISTE ALGUNO DE LOS ID ESPECIFICADOS"), HttpStatus.NOT_FOUND);
            }
        }
        for (int i: nuevoDelete.getListaId()) {
            usuarioService.delete(i);
        }

        return new ResponseEntity(new Mensaje("USUARIOS BORRADOS"), HttpStatus.OK);
    }

    @PostMapping("/loginUsuario")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("CAMPOS MAL PUESTOS"), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsuario(), loginUsuario.getContrasena()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        JwtDto jwtDto = new JwtDto(jwt); //CONSTRUIMOS EL TOKEN
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
