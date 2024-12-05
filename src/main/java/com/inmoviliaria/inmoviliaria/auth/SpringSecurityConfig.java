package com.inmoviliaria.inmoviliaria.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.inmoviliaria.inmoviliaria.auth.filters.JWTValidationFilter;
import com.inmoviliaria.inmoviliaria.auth.filters.JwtAuthenticationFilter;
@Configuration
public class SpringSecurityConfig {
    
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;





    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http.authorizeHttpRequests(authRules -> authRules
                        .requestMatchers(HttpMethod.POST,"/usuarios/Client").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuarios/Agent").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuarios/Admin").permitAll()
                        .requestMatchers(HttpMethod.POST,"/citas/crear").hasRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT,"/citas/asignarCita").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/citas/citasPorAgente").hasRole("AGENTE")
                        .requestMatchers(HttpMethod.PUT,"/citas/finalizarCita").hasRole("AGENTE")
                        .requestMatchers(HttpMethod.PUT,"/citas/reactivarCita").hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JWTValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors-> cors.configurationSource(corsConfigurationSource()))
                .build();
    }


    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }


    @Bean
    AuthenticationManager authenticationManager() throws Exception{

        return authenticationConfiguration.getAuthenticationManager();
    }



    //Para la integración en web 
    @Bean
    CorsConfigurationSource corsConfigurationSource(){


        //Con esto configuramos los cors para que todo tipo de metodo sea aceptado en el pueto 5173
        CorsConfiguration config=new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));

        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @SuppressWarnings("null")
    @Bean
    FilterRegistrationBean<CorsFilter> corsfilter(){

        //Le damos de esta maenra filtro con priroridad para que se pueda ejecutar bien la aplicacion
        FilterRegistrationBean<CorsFilter> bean=new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); //para darle la mayor prioridad

        return bean;
    }
}
