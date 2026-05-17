package com.luisdbb.tarea3AD2024base.controller.objectdb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.objectdb.Incidencia;
import com.luisdbb.tarea3AD2024base.objectdb.TipoIncidencia;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;
import com.luisdbb.tarea3AD2024base.services.NumeroService;
import com.luisdbb.tarea3AD2024base.services.objectdb.IncidenciaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

@Controller
public class ConsultarIncidenciasController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private StageManager stageManager;

    @FXML
    private ComboBox<TipoIncidencia> tipoCombo;

    @FXML
    private ComboBox<String> estadoCombo;

    @FXML
    private ComboBox<Espectaculo> espectaculoCombo;

    @FXML
    private ComboBox<Numero> numeroCombo;

    @FXML
    private DatePicker fechaInicioPicker;

    @FXML
    private DatePicker fechaFinPicker;

    @FXML
    private TableView<Incidencia> incidenciasTable;

    @FXML
    private TableColumn<Incidencia, LocalDateTime> fechaColumn;

    @FXML
    private TableColumn<Incidencia, TipoIncidencia> tipoColumn;

    @FXML
    private TableColumn<Incidencia, String> descripcionColumn;

    @FXML
    private TableColumn<Incidencia, Boolean> resueltaColumn;

    @FXML
    public void initialize() {

        tipoCombo.setItems(

                FXCollections.observableArrayList(
                        TipoIncidencia.values()));
        
        tipoCombo.setPromptText(
                "TODOS");

        estadoCombo.setItems(

                FXCollections.observableArrayList(

                        "TODAS",

                        "RESUELTAS",

                        "NO RESUELTAS"));
        
        estadoCombo.setValue(
                "TODAS");

        espectaculoCombo.getItems().add(null);

        espectaculoCombo.getItems().addAll(

                espectaculoService.obtenerTodos());

        espectaculoCombo.setPromptText(
                "Todos");

        numeroCombo.getItems().add(null);

        numeroCombo.getItems().addAll(

                numeroService.obtenerTodos());

        numeroCombo.setPromptText(
                "Todos");

        fechaColumn.setCellValueFactory(

                data ->

                        new javafx.beans.property.SimpleObjectProperty<>(

                                data.getValue().getFechaHora()));

        fechaColumn.setCellFactory(column ->

                new TableCell<>() {

                    @Override
                    protected void updateItem(
                            LocalDateTime fecha,
                            boolean empty) {

                        super.updateItem(
                                fecha,
                                empty);

                        if (empty || fecha == null) {

                            setText(null);

                        } else {

                            setText(

                                    fecha.format(

                                            DateTimeFormatter.ofPattern(
                                                    "dd/MM/yyyy HH:mm")));
                        }
                    }
                });

        tipoColumn.setCellValueFactory(

                data ->

                        new javafx.beans.property.SimpleObjectProperty<>(

                                data.getValue().getTipo()));

        descripcionColumn.setCellValueFactory(

                data ->

                        new javafx.beans.property.SimpleStringProperty(

                                data.getValue().getDescripcion()));

        resueltaColumn.setCellValueFactory(

                data ->

                        new javafx.beans.property.SimpleObjectProperty<>(

                                data.getValue().isResuelta()));
        
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

        incidenciasTable.getItems().setAll(

                incidenciaService.obtenerTodas());
        
        incidenciasTable.setColumnResizePolicy(

                TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void buscar() {

        TipoIncidencia tipo =
                tipoCombo.getValue();

        Boolean resuelta = null;

        if ("RESUELTAS".equals(
                estadoCombo.getValue())) {

            resuelta = true;
        }

        if ("NO RESUELTAS".equals(
                estadoCombo.getValue())) {

            resuelta = false;
        }

        Long idEspectaculo = null;

        if (espectaculoCombo.getValue() != null) {

            idEspectaculo =

                    espectaculoCombo.getValue()
                            .getId();
        }

        Long idNumero = null;

        if (numeroCombo.getValue() != null) {

            idNumero =

                    numeroCombo.getValue()
                            .getId();
        }

        LocalDateTime fechaInicio = null;

        if (fechaInicioPicker.getValue() != null) {

            fechaInicio =

                    fechaInicioPicker.getValue()
                            .atStartOfDay();
        }

        LocalDateTime fechaFin = null;

        if (fechaFinPicker.getValue() != null) {

            fechaFin =

                    fechaFinPicker.getValue()
                            .atTime(
                                    LocalTime.MAX);
        }

        incidenciasTable.getItems().setAll(

                incidenciaService.buscarIncidencias(

                        tipo,

                        resuelta,

                        idEspectaculo,

                        idNumero,

                        fechaInicio,

                        fechaFin));
    }

    @FXML
    public void limpiarFiltros() {

    	tipoCombo.setValue(null);

    	tipoCombo.setPromptText(
    	        "TODOS");

        estadoCombo.setValue(
                "TODAS");

        espectaculoCombo.getSelectionModel()
                .clearSelection();

        numeroCombo.getSelectionModel()
                .clearSelection();

        fechaInicioPicker.setValue(null);

        fechaFinPicker.setValue(null);

        incidenciasTable.getItems().setAll(

                incidenciaService.obtenerTodas());
    }

    @FXML
    public void volver() {

        stageManager.switchScene(
                FxmlView.ADMIN);
    }
}