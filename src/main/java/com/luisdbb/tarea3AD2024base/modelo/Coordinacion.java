package com.luisdbb.tarea3AD2024base.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Coordinacion extends Persona {

    private boolean senior;

    @Column(name = "fecha_senior")
    private LocalDate fechaSenior;

    @OneToMany(mappedBy = "coordinador")
    private List<Espectaculo> espectaculosDirigidos;

    public Coordinacion() {}

    public boolean isSenior() {
        return senior;
    }

    public void setSenior(boolean senior) {
        this.senior = senior;
    }

    public LocalDate getFechaSenior() {
        return fechaSenior;
    }

    public void setFechaSenior(LocalDate fechaSenior) {
        this.fechaSenior = fechaSenior;
    }

    public List<Espectaculo> getEspectaculosDirigidos() {
        return espectaculosDirigidos;
    }

    public void setEspectaculosDirigidos(List<Espectaculo> espectaculosDirigidos) {
        this.espectaculosDirigidos = espectaculosDirigidos;
    }
}