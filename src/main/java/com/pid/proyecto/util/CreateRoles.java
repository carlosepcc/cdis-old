package com.pid.proyecto.util;

import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.service.RolSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//esta clase solo la usamos para crear los roles, luego hay que mantenerla comentada
@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolSistemaService rolSistemaService;

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
        RolSistema rolJefe = new RolSistema(RolNombre.ROLE_JEFE);

        
        

        if (!rolSistemaService.existsByRol(RolNombre.ROLE_ADMIN)) {
            rolSistemaService.save(rolAdmin);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_USER)) {
            rolSistemaService.save(rolUser);
        }

        if (!rolSistemaService.existsByRol(RolNombre.ROLE_INTEGRANTE)) {
            rolSistemaService.save(rolIntegrante);
        }
        if (!rolSistemaService.existsByRol(RolNombre.ROLE_JEFE)) {
            rolSistemaService.save(rolJefe);
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

    }

}
