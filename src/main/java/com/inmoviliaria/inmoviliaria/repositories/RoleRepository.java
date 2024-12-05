package com.inmoviliaria.inmoviliaria.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inmoviliaria.inmoviliaria.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long>{
    
    Optional<Role> findByNombre(String username);
}
