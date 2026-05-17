package com.luisdbb.tarea3AD2024base.services.db4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import com.luisdbb.tarea3AD2024base.modelo.db4o.LogOperacion;
import com.luisdbb.tarea3AD2024base.modelo.db4o.TipoOperacion;

import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogService {

    private static final String RUTA_DB =
            "ficheros/log.db4o";

    public void guardarLog(
            String usuario,
            TipoOperacion tipoOperacion,
            String resumen) {

        File carpeta = new File("ficheros");

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        ObjectContainer db =
                Db4o.openFile(RUTA_DB);

        try {

            long nuevoId =
                    db.query(LogOperacion.class)
                            .size() + 1;

            LogOperacion log =
                    new LogOperacion(
                            nuevoId,
                            LocalDateTime.now(),
                            usuario,
                            tipoOperacion,
                            resumen
                    );

            db.store(log);

            db.commit();

        } finally {

            db.close();
        }
    }

    public List<LogOperacion> obtenerTodos() {

        ObjectContainer db =
                Db4o.openFile(RUTA_DB);

        try {

            return new java.util.ArrayList<>(
                    db.query(LogOperacion.class));

        } finally {

            db.close();
        }
    }
    
    public List<LogOperacion> buscarLogs(
            String usuario,
            List<TipoOperacion> tipos,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        ObjectContainer db =
                Db4o.openFile(RUTA_DB);

        try {

            List<LogOperacion> resultados =
                    db.query(
                            new com.db4o.query.Predicate<LogOperacion>() {

                @Override
                public boolean match(LogOperacion log) {

                    boolean coincideUsuario =
                            usuario == null
                            || usuario.isBlank()
                            || log.getUsuario()
                                    .equalsIgnoreCase(usuario);

                    boolean coincideTipo =
                            tipos == null
                            || tipos.isEmpty()
                            || tipos.contains(
                                    log.getTipoOperacion());

                    boolean coincideFecha =

                            (fechaInicio == null
                                    || !log.getFechaHora()
                                    .isBefore(fechaInicio))

                            &&

                            (fechaFin == null
                                    || !log.getFechaHora()
                                    .isAfter(fechaFin));

                    return coincideUsuario
                            && coincideTipo
                            && coincideFecha;
                }
            });

            return new java.util.ArrayList<>(resultados);

        } finally {

            db.close();
        }
    }
}