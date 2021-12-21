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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "expediente", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Expediente.findAll", query = "SELECT e FROM Expediente e")})
public class Expediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idexpediente")
    private Integer idexpediente;
    @Size(max = 255)
    @Column(name = "descripcione")
    private String descripcione;
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @ManyToMany(mappedBy = "expedienteList")
    @JsonIgnoreProperties({"comisiondisciplinariaList","expedienteList","declaracionList","iddenuncia"})
    private List<Caso> casoList;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "denunciaList","rolsistemaList","declaracionList","expedienteList","comdiscUsuarioList"})
    private Usuario usuario;

    public Expediente() {
    }

    public Expediente(Integer idexpediente) {
        this.idexpediente = idexpediente;
    }

    public Expediente(String descripcion, Date fecha) {
        this.descripcione = descripcion;
        this.fechacreacion = fecha;
    }

    public Integer getIdexpediente() {
        return idexpediente;
    }

    public void setIdexpediente(Integer idexpediente) {
        this.idexpediente = idexpediente;
    }

    public String getDescripcione() {
        return descripcione;
    }

    public void setDescripcione(String descripcione) {
        this.descripcione = descripcione;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public List<Caso> getCasoList() {
        return casoList;
    }

    public void setCasoList(List<Caso> casoList) {
        this.casoList = casoList;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexpediente != null ? idexpediente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expediente)) {
            return false;
        }
        Expediente other = (Expediente) object;
        if ((this.idexpediente == null && other.idexpediente != null) || (this.idexpediente != null && !this.idexpediente.equals(other.idexpediente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Expediente[ idexpediente=" + idexpediente + " ]";
    }

}
