package com.luisdbb.tarea3AD2024base.modelo;

import org.springframework.stereotype.Component;

@Component
public class Sesion {

    private Long espectaculoId;

    private Persona usuario;

    private Perfil perfil;

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
}