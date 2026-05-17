package com.luisdbb.tarea3AD2024base.controller.objectdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Perfil;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.objectdb.Incidencia;
import com.luisdbb.tarea3AD2024base.services.objectdb.IncidenciaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

@Component
public class ResolverIncidenciaController {

    @FXML
    private TableView<Incidencia> incidenciasTable;

    @FXML
    private TableColumn<Incidencia, String> tipoColumn;

    @FXML
    private TableColumn<Incidencia, String> descripcionColumn;

    @FXML
    private TableColumn<Incidencia, Boolean> resueltaColumn;

    @FXML
    private TextArea accionesArea;

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        if (sesion.getPerfil() != Perfil.ADMIN

                &&

                sesion.getPerfil()
                        != Perfil.COORDINACION) {

            mostrarError(
                    "No autorizado");

            volver();

            return;
        }

        incidenciasTable.setColumnResizePolicy(

                TableView.CONSTRAINED_RESIZE_POLICY);

        tipoColumn.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()
                                .getTipo()
                                .toString()));

        descripcionColumn.setCellValueFactory(data ->

                new SimpleStringProperty(
                        data.getValue()
                                .getDescripcion()));

        resueltaColumn.setCellValueFactory(data ->

                new SimpleBooleanProperty(
                        data.getValue()
                                .isResuelta()));

        resueltaColumn.setCellFactory(column ->

                new TableCell<>() {

                    @Override
                    protected void updateItem(
                            Boolean resuelta,
                            boolean empty) {

                        super.updateItem(
                                resuelta,
                                empty);

                        if (empty || resuelta == null) {

                            setText(null);

                        } else {

                            setText(
                                    resuelta ? "Sí" : "No");
                        }
                    }
                });

        cargarIncidencias();
    }

    private void cargarIncidencias() {

        List<Incidencia> incidencias =

                incidenciaService
                        .obtenerNoResueltas();

        incidenciasTable.getItems()
                .setAll(incidencias);
    }

    @FXML
    public void resolverIncidencia() {

        Incidencia incidencia =

                incidenciasTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (incidencia == null) {

            mostrarError(
                    "Selecciona una incidencia");

            return;
        }

        try {

            incidenciaService
                    .resolverIncidencia(

                            incidencia.getId(),

                            accionesArea.getText());

            mostrarInfo(
                    "Incidencia resuelta correctamente");

            accionesArea.clear();

            cargarIncidencias();

        } catch (Exception e) {

            mostrarError(
                    e.getMessage());
        }
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
                            FxmlView.LOGIN);
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