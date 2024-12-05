package com.inmoviliaria.inmoviliaria.services;

import java.time.LocalDate;
import java.util.List;

import com.inmoviliaria.inmoviliaria.models.entities.Citas;





public interface ICitasService {
    Citas save(LocalDate fecha, Long idProyecto, String consulta, Long idCliente);

    List<Citas>findall();

    List<Citas> citasPorAgente(Long idAgente);

    void actualizarCita(Long idAgente, LocalDate fecha_asig, boolean estado, Long id);
    void finalizarCita(LocalDate fecha_fin, boolean estado, Long id);

    void reactivarCita(boolean estado, Long id);
}
