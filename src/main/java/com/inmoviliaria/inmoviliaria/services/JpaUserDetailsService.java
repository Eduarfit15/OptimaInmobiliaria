package com.inmoviliaria.inmoviliaria.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;
import com.inmoviliaria.inmoviliaria.repositories.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService{


    @Autowired
    private UsuariosRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Usuarios> o=userRepository.findByUsuario(username);

    if(!o.isPresent()){

            throw new UsernameNotFoundException(String.format("Username no existe en el sistema",username));
        }

        Usuarios user=o.orElseThrow();

        List<GrantedAuthority> authorities =user.getRoles().
        stream().
        map(r-> new SimpleGrantedAuthority(r.getNombre()))
        .collect(Collectors.toList());

        //De esta manera obtenemos lo que sono los roles de los usuarios usando diferentes atajos de java


        return new org.springframework.security.core.userdetails.User(
            user.getUsuario(), 
            user.getPassword(), 
            true, 
            true, 
            true, 
            true, 
            authorities
        );



    }
    
}
