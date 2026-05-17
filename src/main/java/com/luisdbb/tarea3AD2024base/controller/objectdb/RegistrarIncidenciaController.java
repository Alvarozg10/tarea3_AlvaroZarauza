package com.luisdbb.tarea3AD2024base.controller.objectdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.objectdb.TipoIncidencia;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;
import com.luisdbb.tarea3AD2024base.services.NumeroService;
import com.luisdbb.tarea3AD2024base.services.objectdb.IncidenciaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

@Component
public class RegistrarIncidenciaController {

    @FXML
    private ComboBox<TipoIncidencia> tipoCombo;

    @FXML
    private TextArea descripcionArea;

    @FXML
    private ComboBox<Espectaculo> espectaculoCombo;

    @FXML
    private ComboBox<Numero> numeroCombo;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private StageManager stageManager;
    
    @Autowired
    private Sesion sesion;

    @FXML
    public void initialize() {

        tipoCombo.getItems().setAll(
                TipoIncidencia.values());

        cargarEspectaculos();

        cargarNumeros();
    }

    private void cargarEspectaculos() {

        List<Espectaculo> espectaculos =
                espectaculoService.obtenerTodos();

        espectaculoCombo.getItems()
                .setAll(espectaculos);
    }

    private void cargarNumeros() {

        List<Numero> numeros =
                numeroService.obtenerTodos();

        numeroCombo.getItems()
                .setAll(numeros);
    }

    @FXML
    public void registrarIncidencia() {

        try {

            TipoIncidencia tipo =
                    tipoCombo.getValue();

            String descripcion =
                    descripcionArea.getText();

            Long idEspectaculo = null;

            Long idNumero = null;

            if (espectaculoCombo.getValue() != null) {

                idEspectaculo =
                        espectaculoCombo
                                .getValue()
                                .getId();
            }

            if (numeroCombo.getValue() != null) {

                idNumero =
                        numeroCombo
                                .getValue()
                                .getId();
            }

            incidenciaService
                    .registrarIncidencia(

                            tipo,

                            descripcion,

                            idEspectaculo,

                            idNumero);

            mostrarInfo(
                    "Incidencia registrada correctamente");

            limpiarFormulario();

        } catch (Exception e) {

            mostrarError(
                    e.getMessage());
        }
    }

    private void limpiarFormulario() {

        tipoCombo.setValue(null);

        descripcionArea.clear();

        espectaculoCombo.setValue(null);

        numeroCombo.setValue(null);
    }

    @FXML
    public void volver() {

        switch (sesion.getPerfil()) {

            case ADMIN ->

                    stageManager.switchScene(
                            FxmlView.ADMIN);

            case COORDINACION ->

                    stageManager.switchScene(
                            FxmlView.COORDINADOR);

            default ->

                    stageManager.switchScene(
                            FxmlView.ARTISTA);
        }
    }

    private void mostrarError(String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Información");

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}