package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.luisdbb.tarea3AD2024base.services.PersonaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;
import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Especialidad;

@Component
public class RegistrarPersonaController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField emailField;

    @FXML
    private ComboBox<String> nacionalidadCombo;

    @FXML
    private ComboBox<String> tipoPersonaCombo;

    @FXML
    private TextField apodoField;

    @FXML
    private CheckBox seniorCheck;

    @FXML
    private DatePicker fechaSeniorPicker;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private VBox especialidadesBox;

    @FXML
    private VBox artistaBox;

    @FXML
    private VBox coordBox;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        tipoPersonaCombo.getItems().addAll(
                "COORDINADOR",
                "ARTISTA"
        );

        tipoPersonaCombo.setOnAction(
                e -> actualizarFormulario()
        );

        artistaBox.setVisible(false);
        artistaBox.setManaged(false);

        coordBox.setVisible(false);
        coordBox.setManaged(false);

        fechaSeniorPicker.setDisable(true);

        seniorCheck.selectedProperty().addListener(
                (obs, oldVal, selected) -> {

            fechaSeniorPicker.setDisable(!selected);

            if (!selected) {
                fechaSeniorPicker.setValue(null);
            }
        });

        String[] codigos = Locale.getISOCountries();

        for (String codigo : codigos) {

            Locale locale = new Locale("", codigo);

            nacionalidadCombo.getItems()
                    .add(locale.getDisplayCountry());
        }

        nacionalidadCombo.getItems()
                .sort(String::compareTo);
    }

    private void actualizarFormulario() {

        String tipo = tipoPersonaCombo.getValue();

        if (tipo == null) return;

        if (tipo.equals("ARTISTA")) {

            artistaBox.setVisible(true);
            artistaBox.setManaged(true);

            coordBox.setVisible(false);
            coordBox.setManaged(false);

            seniorCheck.setSelected(false);
            fechaSeniorPicker.setValue(null);

        } else {

            artistaBox.setVisible(false);
            artistaBox.setManaged(false);

            coordBox.setVisible(true);
            coordBox.setManaged(true);

            apodoField.clear();

            for (Node node : especialidadesBox.getChildren()) {

                CheckBox cb = (CheckBox) node;

                cb.setSelected(false);
            }
        }
    }

    private List<Especialidad> obtenerEspecialidadesSeleccionadas() {

        List<Especialidad> lista = new ArrayList<>();

        for (Node node : especialidadesBox.getChildren()) {

            CheckBox cb = (CheckBox) node;

            if (cb.isSelected()) {

                lista.add(
                        Especialidad.valueOf(cb.getText())
                );
            }
        }

        return lista;
    }

    @FXML
    public void registrarPersona() {

        String nombre = nombreField.getText();

        String email = emailField.getText();

        String nacionalidad =
                nacionalidadCombo.getValue();

        String tipo = tipoPersonaCombo.getValue();

        String apodo = apodoField.getText();

        boolean senior = seniorCheck.isSelected();

        LocalDate fechaSenior =
                fechaSeniorPicker.getValue();

        String username = usernameField.getText();

        String password = passwordField.getText();

        List<Especialidad> especialidades =
                obtenerEspecialidadesSeleccionadas();

        try {

            if (tipo == null) {

                throw new RuntimeException(
                        "Debes seleccionar un tipo");
            }

            if (nacionalidad == null
                    || nacionalidad.isBlank()) {

                throw new RuntimeException(
                        "Debes seleccionar una nacionalidad");
            }

            if (tipo.equals("COORDINADOR")
                    && senior
                    && fechaSenior == null) {

                throw new RuntimeException(
                        "Debes indicar la fecha si es senior");
            }

            personaService.registrarPersona(
                    nombre,
                    email,
                    nacionalidad,
                    tipo,
                    apodo,
                    senior,
                    fechaSenior,
                    username,
                    password,
                    especialidades
            );

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Registro completado");
            alert.setHeaderText(null);

            alert.setContentText(
                    "Persona registrada correctamente");

            alert.showAndWait();
            limpiarFormulario();

        } catch (Exception e) {

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error en el registro");
            alert.setHeaderText(null);

            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }
    
    private void limpiarFormulario() {

        nombreField.clear();

        emailField.clear();

        nacionalidadCombo
                .getSelectionModel()
                .clearSelection();

        tipoPersonaCombo
                .getSelectionModel()
                .clearSelection();

        apodoField.clear();

        seniorCheck.setSelected(false);

        fechaSeniorPicker.setValue(null);

        usernameField.clear();

        passwordField.clear();

        for (Node node : especialidadesBox.getChildren()) {

            CheckBox cb = (CheckBox) node;

            cb.setSelected(false);
        }

        artistaBox.setVisible(false);
        artistaBox.setManaged(false);

        coordBox.setVisible(false);
        coordBox.setManaged(false);
    }

    @FXML
    public void volver() {

        stageManager.switchScene(FxmlView.ADMIN);
    }
}