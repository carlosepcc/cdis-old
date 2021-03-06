package com.pid.proyecto.util;

import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.service.RolSistemaService;
import com.pid.proyecto.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//esta clase solo la usamos para crear los roles, luego hay que mantenerla comentada
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolSistemaService rolSistemaService;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        // crear comision disciplinaria
        // crear denuncia
        // crear caso disciplinario
        // crear expediente
        // crear declaracion
        // crear usuario
        RolSistema rolAdmin = new RolSistema(RolNombre.ROLE_ADMIN);

        // crear comision disciplinaria
        // crear denuncia
        // crear caso disciplinario
        RolSistema rolDecano = new RolSistema(RolNombre.ROLE_DECANO);

        // crear denuncia
        RolSistema rolProfesor = new RolSistema(RolNombre.ROLE_PROFESOR);

        // crear denuncia
        RolSistema rolTrabajador = new RolSistema(RolNombre.ROLE_TRABAJADOR);

        // crear declaracion
        RolSistema rolEstudiante = new RolSistema(RolNombre.ROLE_ESTUDIANTE);

        RolSistema rolUser = new RolSistema(RolNombre.ROLE_USER);

        RolSistema rolIntegrante = new RolSistema(RolNombre.ROLE_INTEGRANTE);
        RolSistema rolPresidente = new RolSistema(RolNombre.ROLE_PRESIDENTE);
        RolSistema rolSecretario = new RolSistema(RolNombre.ROLE_SECRETARIO);

        

        if (!rolSistemaService.existsByRol(RolNombre.ROLE_ADMIN)) {
            rolSistemaService.save(rolAdmin);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_USER)) {
            rolSistemaService.save(rolUser);
        }

        if (!rolSistemaService.existsByRol(RolNombre.ROLE_INTEGRANTE)) {
            rolSistemaService.save(rolIntegrante);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_PRESIDENTE)) {
            rolSistemaService.save(rolPresidente);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_SECRETARIO)) {
            rolSistemaService.save(rolSecretario);
        }

        if (!rolSistemaService.existsByRol(RolNombre.ROLE_DECANO)) {
            rolSistemaService.save(rolDecano);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_PROFESOR)) {
            rolSistemaService.save(rolProfesor);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_TRABAJADOR)) {
            rolSistemaService.save(rolTrabajador);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_ESTUDIANTE)) {
            rolSistemaService.save(rolEstudiante);
        }

        Set<RolSistema> roles = new HashSet<>();

        Usuario usuario = new Usuario("nombreadmin", "apellidosadmin", "admin", passwordEncoder.encode("admin"), false);
        roles.add(rolSistemaService.getByRol(RolNombre.ROLE_ADMIN).get());
        usuario.setRolsistemaList(roles);
        // CREAMOS UN ADMINISTRADOR POR DEFECTO SI LA BASE DE DATOS ESTA VAC??A
        if (!usuarioService.existsById(1)) {
            usuarioService.save(usuario);
        }
    }

}
