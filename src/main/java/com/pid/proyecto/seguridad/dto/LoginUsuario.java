package com.pid.proyecto.seguridad.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
    
    @NotBlank
    private String usuario;
    @NotBlank
    private String contrasena;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
}
