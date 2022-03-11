package com.pid.proyecto.seguridad.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;

public class NuevoDelete {
    
    @NotBlank
    private List<Integer> listaId;

    public NuevoDelete(List<Integer> listaId) {
        this.listaId = listaId;
    }
    public NuevoDelete() {
    }

    public List<Integer> getListaId() {
        return listaId;
    }

    public void setListaId(List<Integer> listaId) {
        this.listaId = listaId;
    }
    
    
    
}
