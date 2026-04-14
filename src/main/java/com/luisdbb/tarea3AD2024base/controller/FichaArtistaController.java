package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.ArtistaService;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.beans.property.SimpleStringProperty;

@Component
public class FichaArtistaController {

    @FXML private Label nombreLabel;
    @FXML private Label emailLabel;
    @FXML private Label nacionalidadLabel;
    @FXML private Label apodoLabel;
    @FXML private Label especialidadesLabel;

    @FXML private TableView<Numero> trayectoriaTable;
    @FXML private TableColumn<Numero, String> colEspectaculo;
    @FXML private TableColumn<Numero, String> colNumero;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @Autowired
    private ArtistaService artistaService;

    @FXML
    public void initialize() {

        colEspectaculo.setCellValueFactory(data ->
            new SimpleStringProperty(
                data.getValue().getEspectaculo().getNombre()
            )
        );

        colNumero.setCellValueFactory(data ->
            new SimpleStringProperty(
                data.getValue().getNombre()
            )
        );

        Artista artista = artistaService.obtenerArtistaCompleto(
                sesion.getUsuario().getId()
        );

        if (artista == null) return;

        nombreLabel.setText("Nombre: " + artista.getNombre());
        emailLabel.setText("Email: " + artista.getEmail());
        nacionalidadLabel.setText("Nacionalidad: " + artista.getNacionalidad());
        apodoLabel.setText("Apodo: " + artista.getApodo());

        String especialidades = artista.getEspecialidades()
                .stream()
                .map(Enum::name)
                .reduce("", (a, b) -> a + " " + b);

        especialidadesLabel.setText("Especialidades: " + especialidades);

        trayectoriaTable.getItems().setAll(artista.getNumeros());
        trayectoriaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        trayectoriaTable.setPlaceholder(new Label("Sin trayectoria"));
    }

    @FXML
    public void volver() {
        stageManager.switchScene(FxmlView.ARTISTA);
    }
}