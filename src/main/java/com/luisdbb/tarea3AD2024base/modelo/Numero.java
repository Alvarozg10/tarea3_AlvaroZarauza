package com.luisdbb.tarea3AD2024base.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "numero")
public class Numero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_numero")
    private int orden;

    private String nombre;

    private double duracion;

    @ManyToOne
    @JoinColumn(name = "espectaculo_id")
    private Espectaculo espectaculo;

    @ManyToMany
    @JoinTable(
        name = "numero_artista",
        joinColumns = @JoinColumn(name = "numero_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas;

    public Numero() {}

    public Long getId() {
        return id;
    }

    public int getOrden() {
        return orden;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public Espectaculo getEspectaculo() {
        return espectaculo;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public void setEspectaculo(Espectaculo espectaculo) {
        this.espectaculo = espectaculo;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
}