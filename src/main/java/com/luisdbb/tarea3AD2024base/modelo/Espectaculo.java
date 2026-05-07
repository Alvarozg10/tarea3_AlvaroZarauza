package com.luisdbb.tarea3AD2024base.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Espectaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 25)
    private String nombre;

    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "coordinador_id")
    private Coordinacion coordinador;
    
    @OneToMany(mappedBy = "espectaculo")
    @OrderBy("orden ASC")
    private List<Numero> numeros;

    public Espectaculo() {}

    public Long getId() { return id; }

    public String getNombre(){
    	return nombre; 
    	}
    
    public void setNombre(String nombre){
    	this.nombre = nombre; 
    	}

    public LocalDate getFechaInicio(){
    	return fechaInicio; 
    	}
    
    public void setFechaInicio(LocalDate fechaInicio){
    	this.fechaInicio = fechaInicio; 
    	}

    public LocalDate getFechaFin(){
    	return fechaFin; 
    	}
    
    public void setFechaFin(LocalDate fechaFin){ 
    	this.fechaFin = fechaFin; 
    	}

    public Coordinacion getCoordinador(){
    	return coordinador; 
    	}
    
    public void setCoordinador(Coordinacion coordinador){
    	this.coordinador = coordinador; 
    	}
    
    public List<Numero> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Numero> numeros) {
        this.numeros = numeros;
    }
}