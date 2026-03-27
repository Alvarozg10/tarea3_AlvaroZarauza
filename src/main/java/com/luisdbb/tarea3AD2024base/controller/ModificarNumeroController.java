package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModificarNumeroController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField duracionField;

    @FXML
    private TextField ordenField;

    @FXML
    private ListView<Persona> artistasList;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private PersonaService personaService;

    private Numero numeroActual;

    @FXML
    public void initialize() {

        artistasList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<Persona> personas = personaService.obtenerTodas();

        for (Persona p : personas) {
            if (p instanceof Artista) {
                artistasList.getItems().add(p);
            }
        }

        artistasList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Persona item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
    }

    @FXML
    public void cargarNumero() {

        try {
            Long id = Long.parseLong(idField.getText());

            numeroActual = numeroService.buscarPorId(id);

            if (numeroActual == null) {
                mostrarError("No existe ese número");
                return;
            }

            nombreField.setText(numeroActual.getNombre());
            duracionField.setText(String.valueOf(numeroActual.getDuracion()));
            ordenField.setText(String.valueOf(numeroActual.getOrden()));

            artistasList.getSelectionModel().clearSelection();

            for (Persona p : artistasList.getItems()) {
                if (numeroActual.getArtistas().contains(p)) {
                    artistasList.getSelectionModel().select(p);
                }
            }

        } catch (NumberFormatException e) {
            mostrarError("ID inválido");
        }
    }

    @FXML
    public void guardarCambios() {

        if (numeroActual == null) {
            mostrarError("Primero carga un número");
            return;
        }

        try {
            String nombre = nombreField.getText();
            double duracion = Double.parseDouble(duracionField.getText());
            int orden = Integer.parseInt(ordenField.getText());

            List<Persona> seleccionados = artistasList.getSelectionModel().getSelectedItems();

            List<Long> artistasIds = new ArrayList<>();

            for (Persona p : seleccionados) {
                artistasIds.add(p.getId());
            }

            numeroService.modificarNumero(
                    numeroActual.getId(),
                    nombre,
                    duracion,
                    orden,
                    artistasIds
            );

            mostrarInfo("Número modificado correctamente");

        } catch (NumberFormatException e) {
            mostrarError("Duración y orden deben ser números válidos");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void mostrarInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.showAndWait();
    }
}