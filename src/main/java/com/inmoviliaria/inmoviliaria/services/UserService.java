package com.inmoviliaria.inmoviliaria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmoviliaria.inmoviliaria.models.dto.UserDTO;
import com.inmoviliaria.inmoviliaria.models.dto.mapper.DtoMapperUser;
import com.inmoviliaria.inmoviliaria.models.entities.Role;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;
import com.inmoviliaria.inmoviliaria.repositories.RoleRepository;
import com.inmoviliaria.inmoviliaria.repositories.UsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService implements IUserService{


    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder; 

    @Override
    @Transactional
    public Optional<Usuarios> findByUsuario(String username) {
       return usuariosRepository.findByUsuario(username);
    }


    @Override
    @Transactional
    public UserDTO saveCliente(Usuarios user) {
        String passwordBc=passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordBc);


        List<Role> roles=new ArrayList<Role>();
        roles=getRoles("CLIENTE");
        user.setRoles(roles);

        return DtoMapperUser.builder().setUser(usuariosRepository.save(user)).build();
    }


    @Override
    @Transactional
    public UserDTO saveAdministrador(Usuarios user) {
        String passwordBc=passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordBc);


        List<Role> roles=new ArrayList<Role>();
        roles=getRoles("ADMINISTRADOR");
        user.setRoles(roles);

        return DtoMapperUser.builder().setUser(usuariosRepository.save(user)).build();
    }


    @Override
    @Transactional
    public UserDTO saveAgente(Usuarios user) {
        String passwordBc=passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordBc);


        List<Role> roles=new ArrayList<Role>();
        roles=getRoles("AGENTE");
        user.setRoles(roles);

        return DtoMapperUser.builder().setUser(usuariosRepository.save(user)).build();
    }


     private List<Role> getRoles(String rol){


        Optional<Role> ou=roleRepository.findByNombre(rol);
        List<Role> roles=new ArrayList<>();

        if(ou.isPresent()){
            roles.add(ou.orElseThrow());
        }

        return roles;

    }
    



}
