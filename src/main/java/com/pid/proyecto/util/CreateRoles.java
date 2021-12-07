/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pid.proyecto.util;

import com.pid.proyecto.entity.RolSistema;
import com.pid.proyecto.seguridad.enums.RolNombre;
import com.pid.proyecto.service.RolSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//esta clase solo la usamos para crear los roles, luego hay que mantenerla comentada

//@Component
//public class CreateRoles implements CommandLineRunner{
//
//    @Autowired
//    RolSistemaService rolSistemaService;
//    
//    @Override
//    public void run(String... args) throws Exception {
//        RolSistema rolAdmin = new RolSistema(RolNombre.ROLE_ADMIN);
//        RolSistema rolUser = new RolSistema(RolNombre.ROLE_USER);
//        rolSistemaService.save(rolUser);
//        rolSistemaService.save(rolAdmin);
//    }
    
//}