package com.pid.proyecto.seguridad.auxiliares;

import com.pid.proyecto.entity.Usuario;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//clase encargada de la seguridad
public class UsuarioPrincipal implements UserDetails {

    private String nombre;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private boolean profesor;
    private Collection<? extends GrantedAuthority> authoritys;

    public UsuarioPrincipal(String nombre, String apellidos, String usuario, String contrasena, boolean profesor, Collection<? extends GrantedAuthority> authoritys) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.profesor = profesor;
        this.authoritys = authoritys;
    }

    // convertimos un objeto de la entidad Usuario en UsuarioPrincipal para obtener sus privilegios (roles)
    // obtenemos los roles y los guardamos en una lista
    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authoritys
                = usuario.getRolsistemaList() // Obtenemos los roles
                        .stream() //recogemos cada objeto de la lista de RolSistema obtenida antes
                        .map(rol -> new SimpleGrantedAuthority(rol.getRol().name())) //convertimos esos roles obtenidos en authoritys a partir del nombre del rol
                        .collect(Collectors.toList()); //recolectamos lo que convertimos

        //devolvemos ese UsuarioPrincipal creado con su lista de roles
        return new UsuarioPrincipal(usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getUsuario(),
                usuario.getContrasena(),
                usuario.getEstudiante(),
                authoritys);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritys;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public boolean isProfesor() {
        return profesor;
    }

}
