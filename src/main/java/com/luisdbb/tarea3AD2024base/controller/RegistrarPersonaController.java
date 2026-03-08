package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.services.PersonaService;

@Component
public class RegistrarPersonaController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nacionalidadField;

    @FXML
    private ComboBox<String> tipoPersonaCombo;

    @FXML
    private TextField apodoField;

    @FXML
    private CheckBox seniorCheck;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Autowired
    private PersonaService personaService;

    @FXML
    public void initialize() {

        tipoPersonaCombo.getItems().addAll(
                "COORDINADOR",
                "ARTISTA"
        );
    }

    @FXML
    public void registrarPersona() {

        String nombre = nombreField.getText();
        String email = emailField.getText();
        String nacionalidad = nacionalidadField.getText();
        String tipo = tipoPersonaCombo.getValue();
        String apodo = apodoField.getText();
        boolean senior = seniorCheck.isSelected();
        String username = usernameField.getText();
        String password = passwordField.getText();

        personaService.registrarPersona(
                nombre,
                email,
                nacionalidad,
                tipo,
                apodo,
                senior,
                username,
                password
        );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro completado");
        alert.setHeaderText(null);
        alert.setContentText("Persona registrada correctamente");
        alert.showAndWait();
    }
}