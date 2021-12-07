/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pid.proyecto.seguridad.dto;

import javax.validation.constraints.NotBlank;

public class NuevoComDiscUsuario {

    @NotBlank
    private int idComision;

    @NotBlank
    private int idUsuario;

    @NotBlank
    private String rol;

    public NuevoComDiscUsuario(int idComision, int idUsuario, String rol) {
        this.idComision = idComision;
        this.idUsuario = idUsuario;
        this.rol = rol;
    }

    public NuevoComDiscUsuario() {

    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
