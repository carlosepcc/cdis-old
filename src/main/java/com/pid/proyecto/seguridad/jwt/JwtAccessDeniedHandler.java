package com.pid.proyecto.seguridad.jwt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ae) throws IOException, ServletException {
        logger.error("FALLO EN EL METODO HANDLE");
        res.sendError(HttpServletResponse.SC_FORBIDDEN, "ACCESO DENEGADO");
    }
}
