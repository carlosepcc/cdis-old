package com.pid.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario", catalog = "PID", schema = "public")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estudiante")
    private boolean estudiante;
    @ManyToMany(mappedBy = "usuarioList")
    @JsonIgnoreProperties({"usuarioList","casoList"})
    private List<Denuncia> denunciaList;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolSistema> rolsistemaList = new HashSet<>();
    @ManyToMany(mappedBy = "usuarioList")
    @JsonIgnoreProperties({"usuarioList", "casoList"})
    private List<Declaracion> declaracionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonIgnoreProperties({"casoList","idusuario"})
    private List<Expediente> expedienteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idusuario")
    @JsonIgnoreProperties({"comisiondisciplinaria","usuario"})
    private List<ComdiscUsuario> comdiscUsuarioList;

    public Usuario() {
    }

    public Usuario(String nombre, String apellidos, String usuario, String contrasena, boolean profesor) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estudiante = profesor;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(boolean estudiante) {
        this.estudiante = estudiante;
    }

    public List<Denuncia> getDenunciaList() {
        return denunciaList;
    }

    public void setDenunciaList(List<Denuncia> denunciaList) {
        this.denunciaList = denunciaList;
    }

    public Set<RolSistema> getRolsistemaList() {
        return rolsistemaList;
    }

    public void setRolsistemaList(Set<RolSistema> rolsistemaList) {
        this.rolsistemaList = rolsistemaList;
    }

    public List<Declaracion> getDeclaracionList() {
        return declaracionList;
    }

    public void setDeclaracionList(List<Declaracion> declaracionList) {
        this.declaracionList = declaracionList;
    }

    public List<Expediente> getExpedienteList() {
        return expedienteList;
    }

    public void setExpedienteList(List<Expediente> expedienteList) {
        this.expedienteList = expedienteList;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pid.proyecto.entity.Usuario[ idusuario=" + idusuario + " ]";
    }

}
