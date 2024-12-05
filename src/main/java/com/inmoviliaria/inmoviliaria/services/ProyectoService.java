package com.inmoviliaria.inmoviliaria.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmoviliaria.inmoviliaria.models.entities.Proyecto;
import com.inmoviliaria.inmoviliaria.repositories.ProyectoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProyectoService implements IProyectoService{
    

    @Autowired ProyectoRepository proyectoRepository;

    @Override
    @Transactional
    public List<Proyecto> findall() {
        return (List<Proyecto>)proyectoRepository.findAll();
    }

    
}
