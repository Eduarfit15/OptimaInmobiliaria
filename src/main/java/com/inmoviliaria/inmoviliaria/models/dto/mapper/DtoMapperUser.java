package com.inmoviliaria.inmoviliaria.models.dto.mapper;



import com.inmoviliaria.inmoviliaria.models.dto.UserDTO;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;

public class DtoMapperUser {
    

    private Usuarios user;


    private DtoMapperUser(){

    }

    public static DtoMapperUser builder(){
        return new DtoMapperUser();
    }

    public DtoMapperUser setUser(Usuarios user){

        this.user=user;
        return this;
    }

    public UserDTO build() {

        if (user == null) {
            throw new RuntimeException("Debe pasar el usuario user");
        }

     
        // Devolver el DTO con solo el rol encontrado
        return new UserDTO(
            this.user.getId(),
            user.getUsuario(),
            user.getNombre(),
            user.getCorreo()
            // Aquí solo asignamos el rol que corresponde
        );
    }
}
