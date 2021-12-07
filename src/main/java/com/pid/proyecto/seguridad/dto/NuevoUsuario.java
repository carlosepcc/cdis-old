package com.pid.proyecto.seguridad.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

public class NuevoUsuario {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellidos;
    @NotBlank
    private String usuario;
    @NotBlank
    private String contrasena;
    @NotBlank
    private boolean profesor;

    private Set<String> roles = new HashSet<>();

    public NuevoUsuario() {
    }

    public NuevoUsuario(String nombre, String apellidos, String usuario, String contrasena, boolean profesor, Set<String> roles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.profesor = profesor;
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

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

    public boolean isProfesor() {
        return profesor;
    }

    public void setProfesor(boolean profesor) {
        this.profesor = profesor;
    }

}
