package com.pid.proyecto.seguridad.auxiliares;

import com.pid.proyecto.entity.Usuario;
import com.pid.proyecto.service.UsuarioService;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Filtrador {

    @Autowired
    UsuarioService us;

    // devuelve falso si alguno de los elementos de la lista de usuarios no es un estudiante
    public boolean soloEstudiantes(List<Usuario> Lu) {

        return Lu.stream().noneMatch((u) -> (u.getEstudiante() == false));
    }

    public List<Usuario> listaEstudiantes(List<Usuario> Lu) {

        List<Usuario> L = new LinkedList<>();
        for (Usuario u : Lu) {
            if (u.getEstudiante()) {
                L.add(u);
            }
        }

        return Lu;
    }

    // devuelve falso si alguno de los elementos de la lista de String no es un usuario existente
    public boolean todosExisten(List<String> Ls) {

        return Ls.stream().noneMatch((u) -> (us.existsByUsuario(u) == false));
    }
}
