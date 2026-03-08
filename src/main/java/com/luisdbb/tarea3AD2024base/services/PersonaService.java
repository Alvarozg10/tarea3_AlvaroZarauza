package com.luisdbb.tarea3AD2024base.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.repositorios.*;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CredencialesRepository credencialesRepository;

    public void registrarPersona(
            String nombre,
            String email,
            String nacionalidad,
            String tipo,
            String apodo,
            boolean senior,
            String username,
            String password) {

        Persona persona;

        if (tipo.equals("COORDINADOR")) {

            Coordinacion coord = new Coordinacion();
            coord.setNombre(nombre);
            coord.setEmail(email);
            coord.setNacionalidad(nacionalidad);
            coord.setSenior(senior);

            persona = personaRepository.save(coord);

        } else {

            Artista artista = new Artista();
            artista.setNombre(nombre);
            artista.setEmail(email);
            artista.setNacionalidad(nacionalidad);
            artista.setApodo(apodo);

            persona = personaRepository.save(artista);
        }

        Credenciales cred = new Credenciales();
        cred.setUsername(username);
        cred.setPassword(password);
        cred.setPersona(persona);

        credencialesRepository.save(cred);
    }
}