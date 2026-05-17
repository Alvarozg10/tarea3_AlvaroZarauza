package com.luisdbb.tarea3AD2024base.services.objectdb;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.objectdb.Incidencia;
import com.luisdbb.tarea3AD2024base.objectdb.ResolucionIncidencia;
import com.luisdbb.tarea3AD2024base.objectdb.TipoIncidencia;
import com.luisdbb.tarea3AD2024base.repositorios.objectdb.IncidenciaRepository;
import com.luisdbb.tarea3AD2024base.repositorios.objectdb.ResolucionIncidenciaRepository;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private ResolucionIncidenciaRepository resolucionRepository;

    @Autowired
    private Sesion sesion;

    public void registrarIncidencia(

            TipoIncidencia tipo,

            String descripcion,

            Long idEspectaculo,

            Long idNumero) {

        if (tipo == null) {

            throw new RuntimeException(
                    "Tipo obligatorio");
        }

        if (descripcion == null
                || descripcion.isBlank()) {

            throw new RuntimeException(
                    "Descripción obligatoria");
        }

        if (descripcion.length() > 1000) {

            throw new RuntimeException(
                    "Máximo 1000 caracteres");
        }

        Incidencia incidencia =
                new Incidencia();

        incidencia.setFechaHora(
                LocalDateTime.now());

        incidencia.setTipo(tipo);

        incidencia.setDescripcion(
                descripcion);

        incidencia.setResuelta(false);

        incidencia.setIdPersonaReporta(

                sesion.getUsuario()
                        .getId());

        incidencia.setIdEspectaculo(
                idEspectaculo);

        incidencia.setIdNumero(
                idNumero);

        incidenciaRepository.save(
                incidencia);
    }

    public void resolverIncidencia(

            Long incidenciaId,

            String accionesRealizadas) {

        if (accionesRealizadas == null
                || accionesRealizadas.isBlank()) {

            throw new RuntimeException(
                    "Debes indicar las acciones realizadas");
        }

        Incidencia incidencia =

                incidenciaRepository.findById(
                        incidenciaId);

        if (incidencia == null) {

            throw new RuntimeException(
                    "La incidencia no existe");
        }

        if (incidencia.isResuelta()) {

            throw new RuntimeException(
                    "La incidencia ya está resuelta");
        }

        incidencia.setResuelta(true);

        incidenciaRepository.save(
                incidencia);

        ResolucionIncidencia resolucion =
                new ResolucionIncidencia();

        resolucion.setIncidencia(
                incidencia);

        resolucion.setFechaHoraResolucion(
                LocalDateTime.now());

        resolucion.setAccionesRealizadas(
                accionesRealizadas);

        resolucion.setIdPersonaResuelve(

                sesion.getUsuario()
                        .getId());

        resolucionRepository.save(
                resolucion);
    }

    public List<Incidencia> obtenerTodas() {

        return incidenciaRepository.findAll();
    }

    public List<Incidencia> obtenerNoResueltas() {

        return incidenciaRepository
                .findByResueltaFalse();
    }

    public List<Incidencia> buscarIncidencias(

            TipoIncidencia tipo,

            Boolean resuelta,

            Long idEspectaculo,

            Long idNumero,

            LocalDateTime fechaInicio,

            LocalDateTime fechaFin) {

        return incidenciaRepository
                .buscarIncidencias(

                        tipo,

                        resuelta,

                        idEspectaculo,

                        idNumero,

                        fechaInicio,

                        fechaFin);
    }
}