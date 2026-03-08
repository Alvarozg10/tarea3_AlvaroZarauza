package com.luisdbb.tarea3AD2024base.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "credenciales")
public class Credenciales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @OneToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    public Credenciales() {}

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}