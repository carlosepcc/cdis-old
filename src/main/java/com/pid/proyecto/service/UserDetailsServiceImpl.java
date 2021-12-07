package com.pid.proyecto.service;

import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.seguridad.auxiliares.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// convierte un Usuario de la base de datos en un UsuarioPrincipal
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // inyectamos
    @Autowired
    UsuarioService usuarioService;

    // cargamos usuarios por nombre de usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByUsuario(username) // obtenemos un Optional por nombre de usuario (username)
                .get(); // obtenemos el usuario de ese optional
        
        // construimos el UsuarioPrincipal
        return UsuarioPrincipal.build(usuario);
    }

}
