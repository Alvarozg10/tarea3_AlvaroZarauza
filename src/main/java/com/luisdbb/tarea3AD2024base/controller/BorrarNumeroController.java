package com.luisdbb.tarea3AD2024base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.services.NumeroService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
public class BorrarNumeroController {

    @FXML
    private TableView<Numero> numerosTable;

    @FXML
    private TableColumn<Numero, Number> idColumn;

    @FXML
    private TableColumn<Numero, String> nombreColumn;

    @FXML
    private TableColumn<Numero, String> espectaculoColumn;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(data ->
                new SimpleLongProperty(
                        data.getValue().getId()));

        nombreColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getNombre()));

        espectaculoColumn.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getEspectaculo()
                                .getNombre()));

        cargarNumeros();
    }

    private void cargarNumeros() {

        List<Numero> numeros =
                numeroService.obtenerTodos();

        numerosTable.getItems().setAll(numeros);
    }

    @FXML
    public void borrarNumero() {

        Numero numero =
                numerosTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (numero == null) {

            mostrarError(
                    "Selecciona un número");

            return;
        }

        try {

            numeroService.borrarNumero(
                    numero.getId());

            cargarNumeros();

            mostrarInfo(
                    "Número eliminado correctamente");

        } catch (Exception e) {

            mostrarError(
                    e.getMessage());
        }
    }

    @FXML
    public void volver() {

        stageManager.switchScene(
                FxmlView.ADMIN);
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