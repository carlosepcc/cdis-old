package com.pid.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comisiondisciplinaria", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Comisiondisciplinaria.findAll", query = "SELECT c FROM Comisiondisciplinaria c")})
public class Comisiondisciplinaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomision")
    private Integer idcomision;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipocomision")
    private String tipocomision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @ManyToMany(mappedBy = "comisiondisciplinariaList")
    @JsonIgnoreProperties({"comisiondisciplinariaList","expedienteList","declaracionList","iddenuncia"})
    private List<Caso> casoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comisiondisciplinaria")
    @JsonIgnoreProperties({"comisiondisciplinaria","usuario"})
    private List<ComdiscUsuario> comdiscUsuarioList;

    public Comisiondisciplinaria() {
    }

    public Comisiondisciplinaria(Integer idcomision) {
        this.idcomision = idcomision;
    }

    public Comisiondisciplinaria(Integer idcomision, String tipocomision, Date fechacreacion) {
        this.idcomision = idcomision;
        this.tipocomision = tipocomision;
        this.fechacreacion = fechacreacion;
    }
    public Comisiondisciplinaria(String tipocomision, Date fechacreacion) {
        this.tipocomision = tipocomision;
        this.fechacreacion = fechacreacion;
    }

    public Integer getIdcomision() {
        return idcomision;
    }

    public void setIdcomision(Integer idcomision) {
        this.idcomision = idcomision;
    }

    public String getTipocomision() {
        return tipocomision;
    }

    public void setTipocomision(String tipocomision) {
        this.tipocomision = tipocomision;
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

    public List<ComdiscUsuario> getComdiscUsuarioList() {
        return comdiscUsuarioList;
    }

    public void setComdiscUsuarioList(List<ComdiscUsuario> comdiscUsuarioList) {
        this.comdiscUsuarioList = comdiscUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomision != null ? idcomision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comisiondisciplinaria)) {
            return false;
        }
        Comisiondisciplinaria other = (Comisiondisciplinaria) object;
        if ((this.idcomision == null && other.idcomision != null) || (this.idcomision != null && !this.idcomision.equals(other.idcomision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Comisiondisciplinaria[ idcomision=" + idcomision + " ]";
    }
    
}
