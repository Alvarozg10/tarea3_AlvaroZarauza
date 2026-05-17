package com.luisdbb.tarea3AD2024base.objectdb;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ResolucionIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHoraResolucion;

    private String accionesRealizadas;

    private Long idPersonaResuelve;

    @ManyToOne
    private Incidencia incidencia;

    public ResolucionIncidencia() {

        this.fechaHoraResolucion =
                LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraResolucion() {
        return fechaHoraResolucion;
    }

    public void setFechaHoraResolucion(
            LocalDateTime fechaHoraResolucion) {

        this.fechaHoraResolucion =
                fechaHoraResolucion;
    }

    public String getAccionesRealizadas() {
        return accionesRealizadas;
    }

    public void setAccionesRealizadas(
            String accionesRealizadas) {

        this.accionesRealizadas =
                accionesRealizadas;
    }

    public Long getIdPersonaResuelve() {
        return idPersonaResuelve;
    }

    public void setIdPersonaResuelve(
            Long idPersonaResuelve) {

        this.idPersonaResuelve =
                idPersonaResuelve;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(
            Incidencia incidencia) {

        this.incidencia = incidencia;
    }
}