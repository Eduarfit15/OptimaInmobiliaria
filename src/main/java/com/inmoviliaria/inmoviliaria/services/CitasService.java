package com.inmoviliaria.inmoviliaria.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inmoviliaria.inmoviliaria.models.entities.Citas;
import com.inmoviliaria.inmoviliaria.models.entities.Proyecto;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;
import com.inmoviliaria.inmoviliaria.repositories.CitasRepository;
import com.inmoviliaria.inmoviliaria.repositories.ProyectoRepository;
import com.inmoviliaria.inmoviliaria.repositories.UsuariosRepository;

import jakarta.transaction.Transactional;

@Service
public class CitasService implements ICitasService{

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;



    @Override
    public Citas save(LocalDate fecha, Long idProyecto, String consulta, Long idCliente) {
        
        Optional<Proyecto> proyecto=proyectoRepository.findById(idProyecto);
        Optional<Usuarios> user=usuariosRepository.findById(idCliente);

        if(proyecto.isPresent() && user.isPresent()){

            Citas cita = new Citas();
            cita.setFecha(fecha);
            cita.setProyecto(proyecto.orElseThrow());
            cita.setConsulta(consulta);
            cita.setCliente(user.orElseThrow());

            return citasRepository.save(cita);
        }

        throw new IllegalArgumentException("Proyecto o cliente no encontrado.");
    }



    @Override
    public List<Citas> findall() {
        return (List<Citas>)citasRepository.findAll();
    }



    @Override
    @Transactional
    public void actualizarCita(Long idAgente, LocalDate fecha_asig, boolean estado, Long id) {
        
        citasRepository.actualizarCita(idAgente, fecha_asig, estado, id);
    }



    @Override
    @Transactional
    public List<Citas> citasPorAgente(Long idAgente) {
        

        Optional<Usuarios> user= usuariosRepository.findById(idAgente);

        if(user.isPresent()){

            return citasRepository.citaPorAgente(idAgente);
        }
        
        
        throw new IllegalArgumentException("Usuario Agente no encontrado");

       
    }



    @Override
    @Transactional
    public void finalizarCita(LocalDate fecha_fin, boolean estado, Long id) {
       
        citasRepository.finzlizarCita(fecha_fin, estado, id);
    }



    @Override
    @Transactional
    public void reactivarCita(boolean estado, Long id) {
        
        citasRepository.reactivarCita(estado, id);
    }


}
