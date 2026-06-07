package com.luisdbb.tarea3AD2024base.mongodb;

public class NumeroTrayectoria {

    private Long idNumero;

    private String nombreNumero;

    public Long getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(
            Long idNumero) {

        this.idNumero =
                idNumero;
    }

    public String getNombreNumero() {
        return nombreNumero;
    }

    public void setNombreNumero(
            String nombreNumero) {

        this.nombreNumero =
                nombreNumero;
    }
}