package com.inmoviliaria.inmoviliaria.models.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Citas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;


    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "proyecto", nullable = false) 
    private Proyecto proyecto;

    @Column(name = "consulta", length = 300, nullable = false)
    private String consulta;

    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false) 
    private Usuarios cliente;


    @Column(name = "fecha_asig")
    private LocalDate fecha_asig;

    @ManyToOne
    @JoinColumn(name = "agente") 
    private Usuarios agente;

    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @Column(name = "estado")
    private Boolean estado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha=fecha;
    }



    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public Usuarios getCliente() {
        return cliente;
    }

    public void setCliente(Usuarios cliente) {
        this.cliente = cliente;
    }

    

    public Usuarios getAgente() {
        return agente;
    }

    public void setAgente(Usuarios agente) {
        this.agente = agente;
    }

   

    public LocalDate getFecha_asig() {
        return fecha_asig;
    }

    public void setFecha_asig(LocalDate fecha_asig) {
        this.fecha_asig = fecha_asig;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
}
