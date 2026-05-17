package com.luisdbb.tarea3AD2024base.modelo.db4o;

import java.time.LocalDateTime;

public class LogOperacion {

    private long id;

    private LocalDateTime fechaHora;

    private String usuario;

    private TipoOperacion tipoOperacion;

    private String resumen;

    public LogOperacion() {
    }

    public LogOperacion(
            long id,
            LocalDateTime fechaHora,
            String usuario,
            TipoOperacion tipoOperacion,
            String resumen) {

        this.id = id;
        this.fechaHora = fechaHora;
        this.usuario = usuario;
        this.tipoOperacion = tipoOperacion;
        this.resumen = resumen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(
            TipoOperacion tipoOperacion) {

        this.tipoOperacion = tipoOperacion;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}