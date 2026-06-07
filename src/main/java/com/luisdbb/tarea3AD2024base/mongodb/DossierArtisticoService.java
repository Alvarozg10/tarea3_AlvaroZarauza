package com.luisdbb.tarea3AD2024base.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.repositorios.NumeroRepository;
import com.luisdbb.tarea3AD2024base.repositorios.PersonaRepository;

import jakarta.transaction.Transactional;

@Service
public class DossierArtisticoService {

    @Autowired
    private DossierArtisticoRepository
            dossierRepository;

    @Autowired
    private PersonaRepository
            personaRepository;

    @Autowired
    private NumeroRepository
            numeroRepository;

    public void crearDossier(
            Artista artista) {

        DossierArtistico dossier =
                new DossierArtistico();

        dossier.setIdArtista(
                artista.getId());

        dossier.setNombre(
                artista.getNombre());

        dossier.setNacionalidad(

                artista.getNacionalidad()
                        .toString());

        dossier.setEmail(
                artista.getEmail());

        dossier.setApodo(
                artista.getApodo());

        List<String> especialidades =
                artista.getEspecialidades()
                        .stream()
                        .map(Enum::name)
                        .toList();

        dossier.setEspecialidades(
                especialidades);

        dossierRepository.save(
                dossier);

        System.out.println(
                "Dossier MongoDB creado");
    }

    public void actualizarDossier(
            Artista artista) {

        DossierArtistico dossier =

                dossierRepository.findByIdArtista(
                        artista.getId());

        if (dossier == null) {
            return;
        }

        dossier.setNombre(
                artista.getNombre());

        dossier.setEmail(
                artista.getEmail());

        dossier.setNacionalidad(
                artista.getNacionalidad());

        dossier.setApodo(
                artista.getApodo());

        List<String> especialidades =
                artista.getEspecialidades()
                        .stream()
                        .map(Enum::name)
                        .toList();

        dossier.setEspecialidades(
                especialidades);

        dossierRepository.save(
                dossier);

        System.out.println(
                "Dossier actualizado");
    }

    public void agregarEvaluacion(

            Long idArtista,

            EvaluacionDossier evaluacion) {

        DossierArtistico dossier =

                dossierRepository.findByIdArtista(
                        idArtista);

        if (dossier == null) {

            throw new RuntimeException(
                    "Dossier no encontrado");
        }

        dossier.getEvaluaciones()
                .add(evaluacion);

        dossierRepository.save(
                dossier);

        System.out.println(
                "Evaluación añadida");
    }

    public void agregarObservacion(

            Long idArtista,

            ObservacionDossier observacion) {

        DossierArtistico dossier =

                dossierRepository.findByIdArtista(
                        idArtista);

        if (dossier == null) {

            throw new RuntimeException(
                    "Dossier no encontrado");
        }

        dossier.getObservaciones()
                .add(observacion);

        dossierRepository.save(
                dossier);

        System.out.println(
                "Observación añadida");
    }

    @Transactional
    public void actualizarTrayectoria(
            Artista artista) {

        Artista artistaBD =

                (Artista)

                        personaRepository
                                .findById(
                                        artista.getId())
                                .orElse(null);

        if (artistaBD == null) {
            return;
        }

        DossierArtistico dossier =

                dossierRepository.findByIdArtista(
                        artistaBD.getId());

        if (dossier == null) {
            return;
        }

        List<Numero> numeros =

                numeroRepository.findByArtistas_Id(
                        artistaBD.getId());

        List<TrayectoriaDossier> trayectoria =
                new ArrayList<>();

        for (Numero numero : numeros) {

            TrayectoriaDossier tray =
                    new TrayectoriaDossier();

            tray.setIdEspectaculo(

                    numero.getEspectaculo()
                            .getId());

            tray.setNombreEspectaculo(

                    numero.getEspectaculo()
                            .getNombre());

            NumeroTrayectoria nt =
                    new NumeroTrayectoria();

            nt.setIdNumero(
                    numero.getId());

            nt.setNombreNumero(
                    numero.getNombre());

            tray.getNumeros()
                    .add(nt);

            trayectoria.add(tray);
        }

        dossier.setTrayectoria(
                trayectoria);

        dossierRepository.save(
                dossier);

        System.out.println(
                "Trayectoria actualizada");
    }

    public DossierArtistico buscarPorArtista(
            Long idArtista) {

        return dossierRepository
                .findByIdArtista(
                        idArtista);
    }
}