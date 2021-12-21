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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "denuncia", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Denuncia.findAll", query = "SELECT d FROM Denuncia d")})
public class Denuncia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddenuncia")
    private Integer iddenuncia;
    @Size(max = 255)
    @Column(name = "descripcionden")
    private String descripcionden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    @JoinTable(name = "denuncia_usuario", joinColumns = {
        @JoinColumn(name = "iddenuncia", referencedColumnName = "iddenuncia")}, inverseJoinColumns = {
        @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")})
    @ManyToMany
    @JsonIgnoreProperties({ "denunciaList","rolsistemaList","declaracionList","expedienteList","comdiscUsuarioList"})
    private List<Usuario> usuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddenuncia")
    @JsonIgnoreProperties({"comisiondisciplinariaList","expedienteList","declaracionList","iddenuncia"})
    private List<Caso> casoList;

    public Denuncia() {
    }

    public Denuncia(Integer iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    public Denuncia(Integer iddenuncia, Date fecha) {
        this.iddenuncia = iddenuncia;
        this.fecha = fecha;
    }
    public Denuncia(String descripcion, Date fecha) {
        this.descripcionden = descripcion;
        this.fecha = fecha;
    }

    public Integer getIddenuncia() {
        return iddenuncia;
    }

    public void setIddenuncia(Integer iddenuncia) {
        this.iddenuncia = iddenuncia;
    }

    public String getDescripcionden() {
        return descripcionden;
    }

    public void setDescripcionden(String descripcionden) {
        this.descripcionden = descripcionden;
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
        hash += (iddenuncia != null ? iddenuncia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Denuncia)) {
            return false;
        }
        Denuncia other = (Denuncia) object;
        if ((this.iddenuncia == null && other.iddenuncia != null) || (this.iddenuncia != null && !this.iddenuncia.equals(other.iddenuncia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Denuncia[ iddenuncia=" + iddenuncia + " ]";
    }
    
}
