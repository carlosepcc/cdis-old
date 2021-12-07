package com.pid.proyecto.seguridad.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// Esta clase va a comprobar si hay un token valido
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    //para verificar errores mientras se programa
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    //esto rechaza a las peticiones no autorizadas porque no esten autenticadas
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ae) throws IOException, ServletException {
        logger.error("FALLO EN EL METODO COMMENCE");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NO AUTORIZADO");
    }

}
