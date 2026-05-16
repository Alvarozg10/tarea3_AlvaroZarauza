package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.*;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModificarNumeroController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField duracionField;

    @FXML
    private TextField ordenField;

    @FXML
    private ListView<Artista> artistasList;

    @FXML
    private TableView<Numero> numerosTable;

    @FXML
    private TableColumn<Numero, String> nombreColumn;

    @FXML
    private TableColumn<Numero, Double> duracionColumn;

    @FXML
    private TableColumn<Numero, Integer> ordenColumn;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private StageManager stageManager;

    @Autowired
    private Sesion sesion;

    private Numero numeroActual;

    @FXML
    public void initialize() {

        numerosTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

        nombreColumn.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        duracionColumn.setCellValueFactory(
                new PropertyValueFactory<>("duracion"));

        ordenColumn.setCellValueFactory(
                new PropertyValueFactory<>("orden"));

        numerosTable.getItems().addAll(
                numeroService.obtenerTodos());

        artistasList.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        List<Persona> personas =
                personaService.obtenerTodas();

        for (Persona p : personas) {

            if (p instanceof Artista artista) {

                artistasList.getItems()
                        .add(artista);
            }
        }

        artistasList.setCellFactory(
                param -> new ListCell<>() {

            @Override
            protected void updateItem(
                    Artista item,
                    boolean empty) {

                super.updateItem(item, empty);

                setText(
                        empty || item == null
                                ? null
                                : item.getNombre());
            }
        });
    }

    @FXML
    public void cargarNumero() {

        numeroActual =
                numerosTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (numeroActual == null) {

            mostrarError(
                    "Debes seleccionar un número");

            return;
        }

        nombreField.setText(
                numeroActual.getNombre());

        duracionField.setText(
                String.valueOf(
                        numeroActual.getDuracion()));

        ordenField.setText(
                String.valueOf(
                        numeroActual.getOrden()));

        artistasList.getSelectionModel()
                .clearSelection();

        for (Artista artistaLista :
                artistasList.getItems()) {

            for (Artista artistaNumero :
                    numeroActual.getArtistas()) {

                if (artistaLista.getId()
                        .equals(
                                artistaNumero.getId())) {

                    artistasList
                            .getSelectionModel()
                            .select(artistaLista);
                }
            }
        }
    }

    @FXML
    public void guardarCambios() {

        if (numeroActual == null) {

            mostrarError(
                    "Primero carga un número");

            return;
        }

        try {

            String nombre =
                    nombreField.getText();

            double duracion =
                    Double.parseDouble(
                            duracionField.getText());

            int orden =
                    Integer.parseInt(
                            ordenField.getText());

            List<Artista> seleccionados =
                    artistasList
                            .getSelectionModel()
                            .getSelectedItems();

            List<Long> artistasIds =
                    new ArrayList<>();

            for (Artista a : seleccionados) {

                artistasIds.add(a.getId());
            }

            numeroService.modificarNumero(
                    numeroActual.getId(),
                    nombre,
                    duracion,
                    orden,
                    artistasIds
            );

            mostrarInfo(
                    "Número modificado correctamente");

        } catch (NumberFormatException e) {

            mostrarError(
                    "Duración y orden deben ser números válidos");

        } catch (Exception e) {

            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String msg) {

        Alert a =
                new Alert(Alert.AlertType.ERROR);

        a.setContentText(msg);

        a.showAndWait();
    }

    private void mostrarInfo(String msg) {

        Alert a =
                new Alert(Alert.AlertType.INFORMATION);

        a.setContentText(msg);

        a.showAndWait();
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
        }
    }
}