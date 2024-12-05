package com.inmoviliaria.inmoviliaria.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inmoviliaria.inmoviliaria.models.entities.Proyecto;
import com.inmoviliaria.inmoviliaria.services.ProyectoService;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {
    

    @Autowired
    private ProyectoService proyectoService;


    //Listar los proyecto de la inmoviliaria
    @GetMapping
    public List<Proyecto> list(){

        return proyectoService.findall();
    }

}
