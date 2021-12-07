package com.pid.proyecto.seguridad;

import com.pid.proyecto.seguridad.jwt.JwtEntryPoint;
import com.pid.proyecto.seguridad.jwt.JwtTokenFilter;
import com.pid.proyecto.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {

        return new JwtTokenFilter();// Manejador de excepciones de autenticación;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // obtenemos el usuario y ciframos la contrasena
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests()
                //para crear nuevo usuario y logearse no hay q estar autenticado
                .antMatchers("/Usuario/crearUsuario","/Usuario/loginUsuario").permitAll() 
                
                //para todo lo demas hay q estar autenticado
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint) // control de excepciones
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    
}
