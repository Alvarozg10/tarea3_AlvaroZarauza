package com.luisdbb.tarea3AD2024base.modelo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Sesion {

    private Long espectaculoId;

    private Persona usuario;

    private Perfil perfil;
    
    private Espectaculo espectaculoTemporal;

    private List<Numero> numerosTemporales =
            new ArrayList<>();

    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    public Long getEspectaculoId() {
        return espectaculoId;
    }

    public void setEspectaculoId(Long espectaculoId) {
        this.espectaculoId = espectaculoId;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public Espectaculo getEspectaculoTemporal() {

        return espectaculoTemporal;
    }

    public void setEspectaculoTemporal(
            Espectaculo espectaculoTemporal) {

        this.espectaculoTemporal =
                espectaculoTemporal;
    }

    public List<Numero> getNumerosTemporales() {

        return numerosTemporales;
    }

    public void setNumerosTemporales(
            List<Numero> numerosTemporales) {

        this.numerosTemporales =
                numerosTemporales;
    }
}