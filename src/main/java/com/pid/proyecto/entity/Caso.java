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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "caso", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c")})
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcaso")
    private Integer idcaso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaapertura")
    @Temporal(TemporalType.DATE)
    private Date fechaapertura;
    @Column(name = "fechacierre")
    @Temporal(TemporalType.DATE)
    private Date fechacierre;
    @JoinTable(name = "caso_comdisc", joinColumns = {
        @JoinColumn(name = "idcaso", referencedColumnName = "idcaso")}, inverseJoinColumns = {
        @JoinColumn(name = "idcomision", referencedColumnName = "idcomision")})
    @ManyToMany
    @JsonIgnoreProperties({ "casoList","comdiscUsuarioList" })
    private List<Comisiondisciplinaria> comisiondisciplinariaList;
    @JoinTable(name = "caso_expediente", joinColumns = {
        @JoinColumn(name = "idcaso", referencedColumnName = "idcaso")}, inverseJoinColumns = {
        @JoinColumn(name = "idexpediente", referencedColumnName = "idexpediente")})
    @ManyToMany
    @JsonIgnoreProperties({"casoList","idusuario"})
    private List<Expediente> expedienteList;
    @ManyToMany(mappedBy = "casoList")
    @JsonIgnoreProperties({"usuarioList", "casoList"})
    private List<Declaracion> declaracionList;
    @JoinColumn(name = "iddenuncia", referencedColumnName = "iddenuncia")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({"usuarioList","casoList"})
    private Denuncia iddenuncia;

    public Caso() {
    }

    public Caso(Integer idcaso) {
        this.idcaso = idcaso;
    }

    public Caso(Integer idcaso, Character estado, Date fechaapertura) {
        this.idcaso = idcaso;
        this.estado = estado;
        this.fechaapertura = fechaapertura;
    }
    public Caso(Character estado, Date fechaapertura) {
        this.estado = estado;
        this.fechaapertura = fechaapertura;
    }

    public Integer getIdcaso() {
        return idcaso;
    }

    public void setIdcaso(Integer idcaso) {
        this.idcaso = idcaso;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFechaapertura() {
        return fechaapertura;
    }

    public void setFechaapertura(Date fechaapertura) {
        this.fechaapertura = fechaapertura;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public List<Comisiondisciplinaria> getComisiondisciplinariaList() {
        return comisiondisciplinariaList;
    }

    public void setComisiondisciplinariaList(List<Comisiondisciplinaria> comisiondisciplinariaList) {
        this.comisiondisciplinariaList = comisiondisciplinariaList;
    }

    public List<Expediente> getExpedienteList() {
        return expedienteList;
    }

    public void setExpedienteList(List<Expediente> expedienteList) {
        this.expedienteList = expedienteList;
    }

    public List<Declaracion> getDeclaracionList() {
        return declaracionList;
    }

    public void setDeclaracionList(List<Declaracion> declaracionList) {
        this.declaracionList = declaracionList;
    }

    public Denuncia getIddenuncia() {
        return iddenuncia;
    }

    public void setIddenuncia(Denuncia iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcaso != null ? idcaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.idcaso == null && other.idcaso != null) || (this.idcaso != null && !this.idcaso.equals(other.idcaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Caso[ idcaso=" + idcaso + " ]";
    }
    
}
