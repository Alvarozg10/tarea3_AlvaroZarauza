package com.luisdbb.tarea3AD2024base.mongodb;

import java.time.LocalDate;

public class EvaluacionDossier {

    private LocalDate fecha;

    private Long idPersona;

    private String rol;

    private String comentario;

    private String nivel;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(
            LocalDate fecha) {

        this.fecha = fecha;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(
            Long idPersona) {

        this.idPersona =
                idPersona;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(
            String rol) {

        this.rol = rol;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(
            String comentario) {

        this.comentario =
                comentario;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(
            String nivel) {

        this.nivel = nivel;
    }
}