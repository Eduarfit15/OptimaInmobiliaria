package com.inmoviliaria.inmoviliaria.models.dto;



public class UserDTO {
    
    private Long Idusuario;
    private String username;
    private String nombre;
    private String correo;
  

   

    

    public UserDTO() {
    }

    public UserDTO(Long idusuario, String username, String nombre, String correo) {
        Idusuario = idusuario;
        this.username = username;
        this.nombre = nombre;
        this.correo = correo;
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

  

    



    public Long getIdusuario() {
        return Idusuario;
    }



    public void setIdusuario(Long idusuario) {
        Idusuario = idusuario;
    }

    
}
