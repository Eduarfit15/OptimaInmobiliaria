package com.inmoviliaria.inmoviliaria.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inmoviliaria.inmoviliaria.models.entities.Citas;
import com.inmoviliaria.inmoviliaria.models.entities.Usuarios;
import com.inmoviliaria.inmoviliaria.repositories.CitasRepository;
import com.inmoviliaria.inmoviliaria.repositories.UsuariosRepository;
import com.inmoviliaria.inmoviliaria.services.CitasService;

@RestController
@RequestMapping("/citas")
public class CitasController {
    

    @Autowired
    private CitasService citasService;

    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;


    //Listar todas las citas
    @GetMapping
    public List<Citas> list(){

        return citasService.findall();
    }

    //crear nueva cita
    @PostMapping("/crear")
    public ResponseEntity<?> save( @RequestParam("proyecto") Long idProyecto,
                                    @RequestParam("consulta") String consulta,
                                    @RequestParam("cliente") Long idCliente){

        return ResponseEntity.status(HttpStatus.CREATED).body(citasService.save(LocalDate.now(),idProyecto,consulta,idCliente)); 
        
    }


    //Asignar nueva cita
    @PutMapping ("/asignarCita")
    public ResponseEntity<?> asignarCita(@RequestParam("Agente") Long idAgente, @RequestParam("cita") Long idCita){
        
        Optional<Citas> cita= citasRepository.findById(idCita);
        Optional<Usuarios> user= usuariosRepository.findById(idAgente);

        if(cita.isPresent() && user.isPresent()){

            citasService.actualizarCita(idAgente, LocalDate.now(), true, idCita);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }



    @GetMapping("/citasPorAgente")
    public List<Citas> citasPorAgente(@RequestParam("agente") Long idAgente){

         return citasRepository.citaPorAgente(idAgente);
    }

    @PutMapping ("/finalizarCita")
    public ResponseEntity<?> finalizarCita(@RequestParam("cita") Long idCita){
        
        Optional<Citas> cita= citasRepository.findById(idCita);

        if(cita.isPresent()){

            citasService.finalizarCita(LocalDate.now(), false, idCita);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping ("/reactivarCita")
    public ResponseEntity<?> reactivarCita(@RequestParam("cita") Long idCita){
        
        Optional<Citas> cita= citasRepository.findById(idCita);

        if(cita.isPresent()){

            citasService.reactivarCita(true, idCita);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
