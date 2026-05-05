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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    @FXML
    private TableView<String[]> datosTable;

    @FXML
    private TableColumn<String[], String> colCampo;

    @FXML
    private TableColumn<String[], String> colValor;

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

        colCampo.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue()[0])
        );

        colValor.setCellValueFactory(data ->
            new SimpleStringProperty(data.getValue()[1])
        );

        Artista artista = artistaService.obtenerArtistaCompleto(
                sesion.getUsuario().getId()
        );

        if (artista == null) return;

        ObservableList<String[]> datos = FXCollections.observableArrayList();

        datos.add(new String[]{"Nombre", artista.getNombre()});
        datos.add(new String[]{"Email", artista.getEmail()});
        datos.add(new String[]{"Nacionalidad", artista.getNacionalidad()});
        datos.add(new String[]{"Apodo", artista.getApodo()});

        String especialidades = artista.getEspecialidades()
                .stream()
                .map(Enum::name)
                .reduce("", (a, b) -> a + " " + b);

        datos.add(new String[]{"Especialidades", especialidades});

        datosTable.setItems(datos);
        datosTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        trayectoriaTable.getItems().setAll(artista.getNumeros());
        trayectoriaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        trayectoriaTable.setPlaceholder(new Label("Sin trayectoria"));
    }

    @FXML
    public void volver() {
        stageManager.switchScene(FxmlView.ARTISTA);
    }
}