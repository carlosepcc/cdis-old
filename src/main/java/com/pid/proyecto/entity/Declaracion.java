package com.pid.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties({ "casoList","usuarioList" })
@Entity
@Table(name = "declaracion", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Declaracion.findAll", query = "SELECT d FROM Declaracion d")})
public class Declaracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddeclaracion")
    private Integer iddeclaracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripciondec")
    private String descripciondec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinTable(name = "usuario_declaracion", joinColumns = {
        @JoinColumn(name = "iddeclaracion", referencedColumnName = "iddeclaracion")}, inverseJoinColumns = {
        @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinTable(name = "caso_declaracion", joinColumns = {
        @JoinColumn(name = "iddeclaracion", referencedColumnName = "iddeclaracion")}, inverseJoinColumns = {
        @JoinColumn(name = "idcaso", referencedColumnName = "idcaso")})
    @ManyToMany
    private List<Caso> casoList;

    public Declaracion() {
    }

    public Declaracion(Integer iddeclaracion) {
        this.iddeclaracion = iddeclaracion;
    }

    public Declaracion(Integer iddeclaracion, String descripciondec, Date fecha) {
        this.iddeclaracion = iddeclaracion;
        this.descripciondec = descripciondec;
        this.fecha = fecha;
    }

    public Integer getIddeclaracion() {
        return iddeclaracion;
    }

    public void setIddeclaracion(Integer iddeclaracion) {
        this.iddeclaracion = iddeclaracion;
    }

    public String getDescripciondec() {
        return descripciondec;
    }

    public void setDescripciondec(String descripciondec) {
        this.descripciondec = descripciondec;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Caso> getCasoList() {
        return casoList;
    }

    public void setCasoList(List<Caso> casoList) {
        this.casoList = casoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeclaracion != null ? iddeclaracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Declaracion)) {
            return false;
        }
        Declaracion other = (Declaracion) object;
        if ((this.iddeclaracion == null && other.iddeclaracion != null) || (this.iddeclaracion != null && !this.iddeclaracion.equals(other.iddeclaracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Declaracion[ iddeclaracion=" + iddeclaracion + " ]";
    }
    
}
