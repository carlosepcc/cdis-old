package com.pid.proyecto.seguridad.enums;

// tipos de roles que vamos a tener en el proyecto
public enum RolNombre {
    
    ROLE_ADMIN, // va a poder crear, modificar y eliminar
    ROLE_USER, // va a poder visualizar
    
    ROLE_INTEGRANTE, // rol en la comision
    ROLE_JEFE; // rol en la comision
}
