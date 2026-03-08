package com.luisdbb.tarea3AD2024base.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "espectaculo")
public class Espectaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 25)
    private String nombre;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "coordinador_id")
    private Coordinacion coordinador;

    @OneToMany(mappedBy = "espectaculo", cascade = CascadeType.ALL)
    private List<Numero> numeros;

    public Espectaculo() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Coordinacion getCoordinador() {
        return coordinador;
    }

    public List<Numero> getNumeros() {
        return numeros;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCoordinador(Coordinacion coordinador) {
        this.coordinador = coordinador;
    }

    public void setNumeros(List<Numero> numeros) {
        this.numeros = numeros;
    }
}