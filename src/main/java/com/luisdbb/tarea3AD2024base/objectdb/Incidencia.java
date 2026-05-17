package com.luisdbb.tarea3AD2024base.objectdb;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;

    private TipoIncidencia tipo;

    private String descripcion;

    private boolean resuelta;

    private Long idPersonaReporta;

    private Long idEspectaculo;

    private Long idNumero;

    public Incidencia() {

        this.fechaHora =
                LocalDateTime.now();

        this.resuelta = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(
            LocalDateTime fechaHora) {

        this.fechaHora = fechaHora;
    }

    public TipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(
            TipoIncidencia tipo) {

        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(
            String descripcion) {

        this.descripcion = descripcion;
    }

    public boolean isResuelta() {
        return resuelta;
    }

    public void setResuelta(
            boolean resuelta) {

        this.resuelta = resuelta;
    }

    public Long getIdPersonaReporta() {
        return idPersonaReporta;
    }

    public void setIdPersonaReporta(
            Long idPersonaReporta) {

        this.idPersonaReporta =
                idPersonaReporta;
    }

    public Long getIdEspectaculo() {
        return idEspectaculo;
    }

    public void setIdEspectaculo(
            Long idEspectaculo) {

        this.idEspectaculo =
                idEspectaculo;
    }

    public Long getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(
            Long idNumero) {

        this.idNumero = idNumero;
    }
}