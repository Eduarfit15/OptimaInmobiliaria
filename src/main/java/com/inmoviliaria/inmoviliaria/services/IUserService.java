package com.inmoviliaria.inmoviliaria.services;

import java.util.Optional;

import com.inmoviliaria.inmoviliaria.models.dto.UserDTO;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;

public interface IUserService {
    
    Optional<Usuarios> findByUsuario(String username);

    UserDTO saveCliente(Usuarios user);
    UserDTO saveAdministrador(Usuarios user);
    UserDTO saveAgente(Usuarios user);
}
