package com.luisdbb.tarea3AD2024base.controller.db4o;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.db4o.LogOperacion;
import com.luisdbb.tarea3AD2024base.modelo.db4o.TipoOperacion;
import com.luisdbb.tarea3AD2024base.services.db4o.LogService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
public class ConsultarLogsController {

    @FXML
    private ComboBox<String> usuarioCombo;

    @FXML
    private CheckBox nuevoCheck;

    @FXML
    private CheckBox actualizacionCheck;

    @FXML
    private CheckBox borradoCheck;

    @FXML
    private DatePicker inicioPicker;

    @FXML
    private DatePicker finPicker;

    @FXML
    private TableView<LogOperacion> logsTable;

    @FXML
    private TableColumn<LogOperacion, String> fechaColumn;

    @FXML
    private TableColumn<LogOperacion, String> usuarioColumn;

    @FXML
    private TableColumn<LogOperacion, String> tipoColumn;

    @FXML
    private TableColumn<LogOperacion, String> resumenColumn;

    @Autowired
    private LogService logService;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        fechaColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getFechaHora()
                                .toString()));

        usuarioColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getUsuario()));

        tipoColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getTipoOperacion()
                                .toString()));

        resumenColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getResumen()));

        cargarUsuarios();

        logsTable.getItems().setAll(
                logService.obtenerTodos());
    }

    private void cargarUsuarios() {

        List<LogOperacion> logs =
                logService.obtenerTodos();

        List<String> usuarios =
                new ArrayList<>();

        for (LogOperacion log : logs) {

            if (!usuarios.contains(log.getUsuario())) {
                usuarios.add(log.getUsuario());
            }
        }

        usuarioCombo.getItems().addAll(usuarios);
    }

    @FXML
    public void buscar() {

        List<TipoOperacion> tipos =
                new ArrayList<>();

        if (nuevoCheck.isSelected()) {
            tipos.add(TipoOperacion.NUEVO);
        }

        if (actualizacionCheck.isSelected()) {
            tipos.add(TipoOperacion.ACTUALIZACION);
        }

        if (borradoCheck.isSelected()) {
            tipos.add(TipoOperacion.BORRADO);
        }

        LocalDateTime inicio = null;
        LocalDateTime fin = null;

        if (inicioPicker.getValue() != null) {

            inicio = inicioPicker
                    .getValue()
                    .atStartOfDay();
        }

        if (finPicker.getValue() != null) {

            fin = finPicker
                    .getValue()
                    .atTime(23, 59, 59);
        }

        List<LogOperacion> logs =
                logService.buscarLogs(
                        usuarioCombo.getValue(),
                        tipos,
                        inicio,
                        fin);

        logsTable.getItems().setAll(logs);
    }

    @FXML
    public void volver() {

        stageManager.switchScene(
                FxmlView.ADMIN);
    }
}





