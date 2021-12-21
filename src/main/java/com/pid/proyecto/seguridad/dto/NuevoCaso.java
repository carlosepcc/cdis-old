package com.pid.proyecto.seguridad.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

public class NuevoCaso {

    @NotBlank
    private char estado;
    @NotBlank
    private Date fechaApertura;
    private Date fechaCierre;
    @NotBlank
    private int idDenuncia;
    @NotBlank
    private int idComision;

    public NuevoCaso(char estado, int idDenuncia, int idComision) {
        this.estado = estado;
        this.idDenuncia = idDenuncia;
        this.idComision = idComision;
    }

    public NuevoCaso() {
    }

    public NuevoCaso(Date fechaCierre) {
        this.fechaCierre = new Date();
    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

}
