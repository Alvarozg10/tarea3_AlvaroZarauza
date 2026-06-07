package com.luisdbb.tarea3AD2024base.mongodb;

import java.util.ArrayList;
import java.util.List;

public class TrayectoriaDossier {

    private Long idEspectaculo;

    private String nombreEspectaculo;

    private List<NumeroTrayectoria> numeros =
            new ArrayList<>();

    public Long getIdEspectaculo() {
        return idEspectaculo;
    }

    public void setIdEspectaculo(
            Long idEspectaculo) {

        this.idEspectaculo =
                idEspectaculo;
    }

    public String getNombreEspectaculo() {
        return nombreEspectaculo;
    }

    public void setNombreEspectaculo(
            String nombreEspectaculo) {

        this.nombreEspectaculo =
                nombreEspectaculo;
    }

    public List<NumeroTrayectoria> getNumeros() {
        return numeros;
    }

    public void setNumeros(
            List<NumeroTrayectoria> numeros) {

        this.numeros =
                numeros;
    }
}
