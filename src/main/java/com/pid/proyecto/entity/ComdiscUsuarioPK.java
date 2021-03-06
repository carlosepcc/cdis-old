package com.pid.proyecto.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ComdiscUsuarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcomision")
    private int idcomision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idusuario")
    private int idusuario;

    public ComdiscUsuarioPK() {
    }

    public ComdiscUsuarioPK(int idcomision, int idusuario) {
        this.idcomision = idcomision;
        this.idusuario = idusuario;
    }

    public int getIdcomision() {
        return idcomision;
    }

    public void setIdcomision(int idcomision) {
        this.idcomision = idcomision;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcomision;
        hash += (int) idusuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComdiscUsuarioPK)) {
            return false;
        }
        ComdiscUsuarioPK other = (ComdiscUsuarioPK) object;
        if (this.idcomision != other.idcomision) {
            return false;
        }
        if (this.idusuario != other.idusuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.ComdiscUsuarioPK[ idcomision=" + idcomision + ", idusuario=" + idusuario + " ]";
    }
    
}
