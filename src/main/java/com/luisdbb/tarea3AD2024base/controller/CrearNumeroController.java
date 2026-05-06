package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.*;
import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import java.util.List;

@Component
public class CrearNumeroController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField duracionField;

    @FXML
    private TextField ordenField;

    @FXML
    private TextField espectaculoField;

    @FXML
    private ListView<Persona> artistasList;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        List<Persona> personas = personaService.obtenerTodas();

        for (Persona p : personas) {

            if (p instanceof Artista) {
                artistasList.getItems().add(p);
            }
        }

        artistasList.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        artistasList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Persona item, boolean empty) {

                super.updateItem(item, empty);

                setText(empty || item == null
                        ? null
                        : item.getNombre());
            }
        });

        Long id = sesion.getEspectaculoId();

        if (id != null) {

            espectaculoField.setVisible(false);
            espectaculoField.setManaged(false);

        } else {

            espectaculoField.setVisible(true);
            espectaculoField.setManaged(true);
        }
    }

    @FXML
    public void crearNumero() {

        try {

            String nombre = nombreField.getText();

            double duracion =
                    Double.parseDouble(duracionField.getText());

            int orden =
                    Integer.parseInt(ordenField.getText());

            Long espectaculoId = sesion.getEspectaculoId();

            if (espectaculoId == null) {

                if (espectaculoField.getText().isBlank()) {

                    throw new RuntimeException(
                            "Debes introducir ID de espectáculo");
                }

                espectaculoId =
                        Long.parseLong(espectaculoField.getText());
            }

            List<Long> artistasIds = artistasList
                    .getSelectionModel()
                    .getSelectedItems()
                    .stream()
                    .map(Persona::getId)
                    .toList();

            numeroService.crearNumero(
                    nombre,
                    duracion,
                    orden,
                    espectaculoId,
                    artistasIds
            );

            mostrarInfo("Número creado correctamente");

            sesion.setEspectaculoId(null);

        } catch (Exception e) {

            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String msg) {

        new Alert(Alert.AlertType.ERROR, msg)
                .showAndWait();
    }

    private void mostrarInfo(String msg) {

        new Alert(Alert.AlertType.INFORMATION, msg)
                .showAndWait();
    }

    @FXML
    public void volver() {

        switch (sesion.getPerfil()) {

            case ADMIN ->
                    stageManager.switchScene(FxmlView.ADMIN);

            case COORDINACION ->
                    stageManager.switchScene(FxmlView.COORDINADOR);
        }
    }
}