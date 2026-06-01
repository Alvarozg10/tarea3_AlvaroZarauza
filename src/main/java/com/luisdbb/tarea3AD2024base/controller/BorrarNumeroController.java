package com.luisdbb.tarea3AD2024base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.modelo.Perfil;
import com.luisdbb.tarea3AD2024base.modelo.Persona;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.services.NumeroService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

@Component
public class BorrarNumeroController {

    @FXML
    private TableView<Numero> numerosTable;

    @FXML
    private TableColumn<Numero, Long> idColumn;

    @FXML
    private TableColumn<Numero, String> nombreColumn;

    @FXML
    private TableColumn<Numero, String> espectaculoColumn;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        Persona usuario =
                sesion.getUsuario();

        List<Numero> numeros;

        if (usuario != null
                && usuario.getCredenciales() != null
                && usuario.getCredenciales().getPerfil()
                == Perfil.COORDINACION) {

            numeros =
                    numeroService
                            .obtenerPorCoordinador(
                                    usuario.getId());

        } else {

            numeros =
                    numeroService
                            .obtenerTodos();
        }

        idColumn.setCellValueFactory(
                data -> new SimpleObjectProperty<>(
                        data.getValue().getId()));

        nombreColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombre()));

        espectaculoColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue()
                                .getEspectaculo()
                                .getNombre()));

        numerosTable.setItems(
                FXCollections.observableArrayList(
                        numeros));
    }

    @FXML
    public void borrarNumero() {

        Numero numero =
                numerosTable
                        .getSelectionModel()
                        .getSelectedItem();

        if (numero == null) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR);

            alert.setTitle("Error");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Debes seleccionar un número");

            alert.showAndWait();

            return;
        }

        try {

            numeroService.borrarNumero(
                    numero.getId());

            numerosTable.getItems()
                    .remove(numero);

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION);

            alert.setTitle(
                    "Número eliminado");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Número eliminado correctamente");

            alert.showAndWait();

        } catch (Exception e) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR);

            alert.setTitle("Error");

            alert.setHeaderText(null);

            alert.setContentText(
                    e.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    public void volver() {

        Persona usuario =
                sesion.getUsuario();

        if (usuario != null
                && usuario.getCredenciales() != null) {

            Perfil perfil =
                    usuario.getCredenciales()
                            .getPerfil();

            if (perfil == Perfil.COORDINACION) {

                stageManager.switchScene(
                        FxmlView.COORDINADOR);

            } else if (perfil == Perfil.ARTISTA) {

                stageManager.switchScene(
                        FxmlView.ARTISTA);

            } else {

                stageManager.switchScene(
                        FxmlView.ADMIN);
            }

        } else {

            stageManager.switchScene(
                    FxmlView.ADMIN);
        }
    }
}
