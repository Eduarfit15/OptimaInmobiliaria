package com.inmoviliaria.inmoviliaria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;

public interface UsuariosRepository extends CrudRepository<Usuarios,Long>{
    @Query(value = "select * from usuarios where usuario=?1",nativeQuery = true)
    Optional<Usuarios> findByUsuario(String username);
}
