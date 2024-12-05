package com.inmoviliaria.inmoviliaria.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inmoviliaria.inmoviliaria.models.entities.Citas;


public interface CitasRepository extends CrudRepository<Citas,Long> {
    


    @Modifying
    @Query(value = ("update citas c set c.agente=?1, c.fecha_asig=?2, c.estado=?3 where c.id=?4"),nativeQuery = true)
    void actualizarCita(Long  idAgente, LocalDate fecha_asig, boolean estado, Long id);


    @Query(value= ("select * from citas c where c.agente=?1 and c.estado=1"), nativeQuery = true)
    List<Citas> citaPorAgente(Long idAgente);


    @Modifying
    @Query(value = ("update citas c set c.fecha_fin=?1, c.estado=?2 where c.id=?3"),nativeQuery = true)
    void finzlizarCita(LocalDate fecha_fin,Boolean estado, Long idCita);

    @Modifying
    @Query(value = ("update citas c set c.estado=?1 where c.id=?2"),nativeQuery = true)
    void reactivarCita(Boolean estado, Long idCita);
}
