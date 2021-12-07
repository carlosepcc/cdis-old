/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pid.proyecto.seguridad.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class NuevaComision {

    @NotBlank
    private String TipoComision;
    @NotBlank
    private Date fecha;
    @NotBlank
    private String usuario;
    @NotBlank
    private String rolComision;
    
    

    public NuevaComision(String TipoComision, Date fecha, String usuario, String rolComision) {
        this.TipoComision = TipoComision;
        this.fecha = fecha;
        this.usuario = usuario;
        this.rolComision = rolComision;
    }
    public NuevaComision() {
    }

    public String getRolComision() {
        return rolComision;
    }

    public void setRolComision(String rolComision) {
        this.rolComision = rolComision;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


    public String getTipoComision() {
        return TipoComision;
    }

    public void setTipoComision(String TipoComision) {
        this.TipoComision = TipoComision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
