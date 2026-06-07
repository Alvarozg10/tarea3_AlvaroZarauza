package com.luisdbb.tarea3AD2024base.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.repositorios.ArtistaRepository;
import com.luisdbb.tarea3AD2024base.repositorios.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class ArtistaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional
    public Artista obtenerFichaArtista(
            Long id) {

        Artista artista =

                (Artista)

                        personaRepository
                                .findById(id)
                                .orElse(null);

        if (artista != null) {

            artista.getEspecialidades()
                    .size();

            artista.getNumeros()
                    .size();

            for (Numero n : artista.getNumeros()) {

                n.getEspectaculo()
                        .getNombre();
            }
        }

        return artista;
    }

    @Transactional
    public Artista obtenerArtistaCompleto(
            Long id) {

        Artista artista =

                (Artista)

                        personaRepository
                                .findById(id)
                                .orElse(null);

        if (artista != null) {

            artista.getNumeros()
                    .size();
        }

        return artista;
    }

    public List<Artista> obtenerTodos() {

        return artistaRepository.findAll();
    }
}