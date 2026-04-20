package com.luisdbb.tarea3AD2024base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Credenciales;
import com.luisdbb.tarea3AD2024base.modelo.Persona;
import com.luisdbb.tarea3AD2024base.repositorios.CredencialesRepository;
import com.luisdbb.tarea3AD2024base.repositorios.PersonaRepository;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class RecuperarPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private Label resultadoLabel;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CredencialesRepository credencialesRepository;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void mostrarPassword() {

        String email = emailField.getText();

        if (email == null || email.isBlank()) {
            resultadoLabel.setText("Introduce un email");
            return;
        }

        Persona persona = personaRepository.findByEmail(email);

        if (persona == null) {
            resultadoLabel.setText("No existe ninguna persona con ese email");
            return;
        }

        Credenciales cred = credencialesRepository.findByPersona(persona);

        if (cred == null) {
            resultadoLabel.setText("No hay credenciales asociadas");
            return;
        }

        resultadoLabel.setText("Tu contraseña es: " + cred.getPassword());
    }

    @FXML
    public void volver() {
        stageManager.switchScene(FxmlView.LOGIN);
    }
}