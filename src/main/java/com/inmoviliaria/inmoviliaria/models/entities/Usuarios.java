package com.inmoviliaria.inmoviliaria.models.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Usuarios {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @NotEmpty
    private String correo;

    @NotEmpty
    private String usuario;

    @NotEmpty
    private String password;

    @ManyToMany
    @JoinTable(
    name = "usuarios_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"), 
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "rol_id"})
    )
    private List<Role> roles;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public List<Role> getRoles() {
        return roles;
    }


    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsuario() {
        return usuario;
    }


    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


  




    
}
