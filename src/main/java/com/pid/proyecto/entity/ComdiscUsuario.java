package com.pid.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comdisc_usuario", catalog = "PID", schema = "public")
@NamedQueries({
    @NamedQuery(name = "ComdiscUsuario.findAll", query = "SELECT c FROM ComdiscUsuario c")})
public class ComdiscUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ComdiscUsuarioPK comdiscUsuarioPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rolcomision")
    private String rolcomision;
    @JoinColumn(name = "idcomision", referencedColumnName = "idcomision", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "casoList","comdiscUsuarioList" })
    private Comisiondisciplinaria comisiondisciplinaria;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "denunciaList","rolsistemaList","declaracionList","expedienteList","comdiscUsuarioList"})
    private Usuario idusuario;

    public ComdiscUsuario() {
    }

    public ComdiscUsuario(ComdiscUsuarioPK comdiscUsuarioPK) {
        this.comdiscUsuarioPK = comdiscUsuarioPK;
    }

    public ComdiscUsuario(ComdiscUsuarioPK comdiscUsuarioPK, String rolcomision) {
        this.comdiscUsuarioPK = comdiscUsuarioPK;
        this.rolcomision = rolcomision;
    }

    public ComdiscUsuario(int idcomision, int idusuario) {
        this.comdiscUsuarioPK = new ComdiscUsuarioPK(idcomision, idusuario);
    }

    public ComdiscUsuarioPK getComdiscUsuarioPK() {
        return comdiscUsuarioPK;
    }

    public void setComdiscUsuarioPK(ComdiscUsuarioPK comdiscUsuarioPK) {
        this.comdiscUsuarioPK = comdiscUsuarioPK;
    }

    public String getRolcomision() {
        return rolcomision;
    }

    public void setRolcomision(String rolcomision) {
        this.rolcomision = rolcomision;
    }

    public Comisiondisciplinaria getComisiondisciplinaria() {
        return comisiondisciplinaria;
    }

    public void setComisiondisciplinaria(Comisiondisciplinaria comisiondisciplinaria) {
        this.comisiondisciplinaria = comisiondisciplinaria;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comdiscUsuarioPK != null ? comdiscUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComdiscUsuario)) {
            return false;
        }
        ComdiscUsuario other = (ComdiscUsuario) object;
        if ((this.comdiscUsuarioPK == null && other.comdiscUsuarioPK != null) || (this.comdiscUsuarioPK != null && !this.comdiscUsuarioPK.equals(other.comdiscUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.ComdiscUsuario[ comdiscUsuarioPK=" + comdiscUsuarioPK + " ]";
    }

}
