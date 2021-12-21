package com.pid.proyecto.seguridad.auxiliares;

import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.service.UsuarioService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertidorToListEntity {

    @Autowired
    UsuarioService usuarioService;

    public List<Usuario> convertirListaStringToListaUsuario(List<String> usuarios) {

        List<Usuario> u = new LinkedList<>();
        for (String n : usuarios) {
            if (!usuarioService.existsByUsuario(n)) {
                return null;
            }
            u.add(usuarioService.getByUsuario(n).get());
        }
        return u;
    }

    public List<Integer> convertirListaUsuarioToListaIdUsuarios(List<Usuario> usuarios) {

        List<Integer> i = new LinkedList<>();
        for (Usuario u : usuarios) {

            i.add(u.getIdusuario());
        }
        return i;
    }

}
