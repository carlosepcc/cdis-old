package com.pid.proyecto.entity;

import com.pid.proyecto.seguridad.enums.RolNombre;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "rolsistema", catalog = "PID", schema = "public")
public class RolSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrol")
    private Integer idrol;

    @Basic(optional = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Size(min = 1, max = 255)
    @Column(name = "rol")
    private RolNombre rol;

    public RolSistema() {
    }

    public RolSistema(Integer idrol) {
        this.idrol = idrol;
    }

    public RolSistema(Integer idrol, RolNombre rol) {
        this.idrol = idrol;
        this.rol = rol;
    }

    public RolSistema(RolNombre rolNombre) {
        this.rol=rolNombre;
    }

    public Integer getIdrol() {
        return idrol;
    }

    public void setIdrol(Integer idrol) {
        this.idrol = idrol;
    }

    public RolNombre getRol() {
        return rol;
    }

    public void setRol(RolNombre rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrol != null ? idrol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolSistema)) {
            return false;
        }
        RolSistema other = (RolSistema) object;
        if ((this.idrol == null && other.idrol != null) || (this.idrol != null && !this.idrol.equals(other.idrol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Rolsistema[ idrol=" + idrol + " ]";
    }

}
