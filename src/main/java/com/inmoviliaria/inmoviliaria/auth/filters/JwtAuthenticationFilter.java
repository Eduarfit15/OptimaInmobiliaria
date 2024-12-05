package com.inmoviliaria.inmoviliaria.auth.filters;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;

import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static com.inmoviliaria.inmoviliaria.auth.TokenJWTConfig.*;
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
          Usuarios user=null;
                String username=null;
                String password=null;


                try {
                    user=new ObjectMapper().readValue(request.getInputStream(), Usuarios.class);

                    username=user.getUsuario();
                    password=user.getPassword();

                    
                } 
                catch(StreamReadException e) {
                    e.printStackTrace();
                }
                catch(DatabindException e){
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username, password);
                return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        
        // Obtener el nombre de usuario autenticado
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();

        // Obtener los roles del usuario autenticado
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

      
        // Crear claims para incluir roles en el token JWT
        ClaimsBuilder claims = Jwts.claims();
        claims.add("authorities", new ObjectMapper().writeValueAsString(roles)); // Serializar roles a JSON
       

        // Generar el token JWT
        String token = Jwts.builder()
                .claims(claims.build())
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 3600000)) // 1 hora de validez
                .compact();

        // Agregar el token a la cabecera de la respuesta
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        // Crear el cuerpo de la respuesta
        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("message", String.format("Hola %s, has iniciado sesión con éxito", username));
        body.put("username", username);
        body.put("authorities", roles);

        // Escribir la respuesta como JSON
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200); // Estado HTTP 200 (OK)
        response.setContentType("application/json");
}


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
                Map<String,Object> body=new HashMap<>();

                body.put("message","Error en la autenticación, username o password incorrecto");
                body.put("error",failed.getMessage());

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(401);
                response.setContentType("application/json");
    }
    
}
