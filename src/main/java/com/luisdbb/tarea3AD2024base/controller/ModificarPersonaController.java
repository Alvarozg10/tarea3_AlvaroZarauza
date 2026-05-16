package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.PersonaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ModificarPersonaController {

    @FXML private TableView<Persona> personasTable;
    @FXML private TableColumn<Persona, String> nombreColumn;
    @FXML private TableColumn<Persona, String> tipoColumn;

    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private ComboBox<String> nacionalidadCombo;
    @FXML private TextField apodoField;

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML private CheckBox seniorCheck;
    @FXML private DatePicker fechaSeniorPicker;

    @FXML private CheckBox acroCheck;
    @FXML private CheckBox humorCheck;
    @FXML private CheckBox magiaCheck;
    @FXML private CheckBox equilibrioCheck;
    @FXML private CheckBox malabaresCheck;

    @FXML private VBox artistaBox;
    @FXML private VBox coordBox;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private StageManager stageManager;

    private Persona personaActual;

    @FXML
    public void initialize() {

        personasTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

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

        nombreColumn.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        tipoColumn.setCellValueFactory(cellData -> {

            Persona p = cellData.getValue();

            String tipo =
                    p instanceof Artista
                            ? "ARTISTA"
                            : "COORDINACION";

            return new javafx.beans.property
                    .SimpleStringProperty(tipo);
        });

        personasTable.getItems().addAll(
                personaService.obtenerTodas());
    }

    @FXML
    public void cargarPersona() {

        personaActual = personasTable
                .getSelectionModel()
                .getSelectedItem();

        if (personaActual == null) {

            mostrarError(
                    "Debes seleccionar una persona");

            return;
        }

        nombreField.setText(
                personaActual.getNombre());

        emailField.setText(
                personaActual.getEmail());

        nacionalidadCombo.setValue(
                personaActual.getNacionalidad());

        if (personaActual.getCredenciales() != null) {

            usernameField.setText(
                    personaActual
                            .getCredenciales()
                            .getUsername());
        }

        passwordField.clear();

        apodoField.clear();

        seniorCheck.setSelected(false);

        fechaSeniorPicker.setValue(null);

        acroCheck.setSelected(false);
        humorCheck.setSelected(false);
        magiaCheck.setSelected(false);
        equilibrioCheck.setSelected(false);
        malabaresCheck.setSelected(false);

        if (personaActual instanceof Artista artista) {

            artistaBox.setVisible(true);
            artistaBox.setManaged(true);

            coordBox.setVisible(false);
            coordBox.setManaged(false);

            apodoField.setText(
                    artista.getApodo());

            if (artista.getEspecialidades() != null) {

                for (Especialidad esp :
                        artista.getEspecialidades()) {

                    switch (esp) {

                        case ACROBACIA ->
                                acroCheck.setSelected(true);

                        case HUMOR ->
                                humorCheck.setSelected(true);

                        case MAGIA ->
                                magiaCheck.setSelected(true);

                        case EQUILIBRISMO ->
                                equilibrioCheck.setSelected(true);

                        case MALABARISMO ->
                                malabaresCheck.setSelected(true);
                    }
                }
            }
        }

        if (personaActual instanceof Coordinacion coord) {

            coordBox.setVisible(true);
            coordBox.setManaged(true);

            artistaBox.setVisible(false);
            artistaBox.setManaged(false);

            seniorCheck.setSelected(
                    coord.isSenior());

            if (coord.getFechaSenior() != null) {

                fechaSeniorPicker.setValue(
                        coord.getFechaSenior());
            }
        }
    }

    @FXML
    public void guardarCambios() {

        if (personaActual == null) {

            mostrarError(
                    "Primero debes cargar una persona");

            return;
        }

        try {

            List<Especialidad> especialidades =
                    new ArrayList<>();

            if (acroCheck.isSelected()) {
                especialidades.add(
                        Especialidad.ACROBACIA);
            }

            if (humorCheck.isSelected()) {
                especialidades.add(
                        Especialidad.HUMOR);
            }

            if (magiaCheck.isSelected()) {
                especialidades.add(
                        Especialidad.MAGIA);
            }

            if (equilibrioCheck.isSelected()) {
                especialidades.add(
                        Especialidad.EQUILIBRISMO);
            }

            if (malabaresCheck.isSelected()) {
                especialidades.add(
                        Especialidad.MALABARISMO);
            }

            String username = usernameField.getText();
            String password = passwordField.getText();

            personaService.modificarPersona(
                    personaActual.getId(),
                    nombreField.getText(),
                    emailField.getText(),
                    nacionalidadCombo.getValue(),
                    apodoField.getText(),
                    seniorCheck.isSelected(),
                    fechaSeniorPicker.getValue(),
                    especialidades,
                    username,
                    password
            );

            mostrarInfo(
                    "Persona modificada correctamente");

        } catch (Exception e) {

            mostrarError(
                    "Error al modificar: "
                            + e.getMessage());
        }
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

        alert.setTitle("OK");

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    @FXML
    public void volver() {

        stageManager.switchScene(FxmlView.ADMIN);
    }
}