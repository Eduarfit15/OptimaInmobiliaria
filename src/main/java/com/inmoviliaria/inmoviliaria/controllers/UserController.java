package com.inmoviliaria.inmoviliaria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;
import com.inmoviliaria.inmoviliaria.services.UserService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(originPatterns = "*")
public class UserController {
    

    @Autowired
    private UserService usuariosService;


    @PostMapping("/Client")
    public ResponseEntity<?> createC(@RequestBody Usuarios user){

        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.saveCliente(user));
        
    }

    @PostMapping("/Agent")
    public ResponseEntity<?> createA(@RequestBody Usuarios user){

        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.saveAgente(user));
        
    }

    @PostMapping("/Admin")
    public ResponseEntity<?> createAd(@RequestBody Usuarios user){

        return ResponseEntity.status(HttpStatus.CREATED).body(usuariosService.saveAdministrador(user));
        
    }
}
