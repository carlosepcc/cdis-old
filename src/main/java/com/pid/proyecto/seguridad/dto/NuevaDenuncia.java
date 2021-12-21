package com.pid.proyecto.seguridad.dto;

import com.pid.proyecto.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;

public class NuevaDenuncia {

    private String descripcion;
    @NotBlank
    private Date fecha;
    @NotBlank
    private String denunciante;
    @NotBlank
    private List<String> estudiantes;

    public NuevaDenuncia() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDenunciante() {
        return denunciante;
    }

    public void setDenunciante(String denunciante) {
        this.denunciante = denunciante;
    }

    public List<String> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<String> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
