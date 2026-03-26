package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.PersonaService;

@Component
public class ModificarPersonaController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nacionalidadField;

    @FXML
    private TextField apodoField;

    @FXML
    private CheckBox seniorCheck;

    @Autowired
    private PersonaService personaService;

    private Persona personaActual;

    @FXML
    public void cargarPersona() {
        try {
            Long id = Long.parseLong(idField.getText());

            personaActual = personaService.buscarPorId(id);

            if (personaActual == null) {
                mostrarError("No existe ninguna persona con ese ID");
                return;
            }

            nombreField.setText(personaActual.getNombre());
            emailField.setText(personaActual.getEmail());
            nacionalidadField.setText(personaActual.getNacionalidad());

            apodoField.clear();
            seniorCheck.setSelected(false);

            if (personaActual instanceof Artista artista) {
                apodoField.setText(artista.getApodo());
            }

            if (personaActual instanceof Coordinacion coord) {
                seniorCheck.setSelected(coord.isSenior());
            }

        } catch (NumberFormatException e) {
            mostrarError("ID inválido");
        }
    }

    @FXML
    public void guardarCambios() {

        if (personaActual == null) {
            mostrarError("Primero debes cargar una persona");
            return;
        }

        try {
            personaService.modificarPersona(
                    personaActual.getId(),
                    nombreField.getText(),
                    emailField.getText(),
                    nacionalidadField.getText(),
                    apodoField.getText(),
                    seniorCheck.isSelected()
            );

            mostrarInfo("Persona modificada correctamente");

        } catch (Exception e) {
            mostrarError("Error al modificar: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OK");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}