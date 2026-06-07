package com.luisdbb.tarea3AD2024base.mongodb;

import java.time.LocalDate;

public class ObservacionDossier {

    private LocalDate fecha;

    private String texto;

    private String autor;

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(
            LocalDate fecha) {

        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(
            String texto) {

        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(
            String autor) {

        this.autor = autor;
    }
}