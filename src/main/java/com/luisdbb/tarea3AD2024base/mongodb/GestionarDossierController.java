package com.luisdbb.tarea3AD2024base.mongodb;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.modelo.Perfil;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.services.ArtistaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class GestionarDossierController {

    @FXML
    private ComboBox<Artista> artistaCombo;

    @FXML
    private ComboBox<String> nivelCombo;

    @FXML
    private TextArea comentarioEvaluacionArea;

    @FXML
    private TextArea observacionArea;

    @FXML
    private TableView<EvaluacionDossier>
            tablaEvaluaciones;

    @FXML
    private TableColumn<EvaluacionDossier,
            LocalDate> colFechaEval;

    @FXML
    private TableColumn<EvaluacionDossier,
            String> colNivelEval;

    @FXML
    private TableColumn<EvaluacionDossier,
            String> colComentarioEval;

    @FXML
    private TableColumn<EvaluacionDossier,
            String> colRolEval;

    @FXML
    private TableView<ObservacionDossier>
            tablaObservaciones;

    @FXML
    private TableColumn<ObservacionDossier,
            LocalDate> colFechaObs;

    @FXML
    private TableColumn<ObservacionDossier,
            String> colAutorObs;

    @FXML
    private TableColumn<ObservacionDossier,
            String> colTextoObs;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private DossierArtisticoService
            dossierArtisticoService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        artistaCombo.setItems(

                FXCollections.observableArrayList(

                        artistaService.obtenerTodos()));

        artistaCombo.setOnAction(
                e -> cargarDossier());

        nivelCombo.getItems().addAll(
                "ALTO",
                "MEDIO",
                "BAJO");

        colFechaEval.setCellValueFactory(
                new PropertyValueFactory<>("fecha"));

        colNivelEval.setCellValueFactory(
                new PropertyValueFactory<>("nivel"));

        colComentarioEval.setCellValueFactory(
                new PropertyValueFactory<>("comentario"));

        colRolEval.setCellValueFactory(
                new PropertyValueFactory<>("rol"));

        colFechaObs.setCellValueFactory(
                new PropertyValueFactory<>("fecha"));

        colAutorObs.setCellValueFactory(
                new PropertyValueFactory<>("autor"));

        colTextoObs.setCellValueFactory(
                new PropertyValueFactory<>("texto"));

        tablaEvaluaciones.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);

        tablaObservaciones.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void cargarDossier() {

        Artista artista =
                artistaCombo.getValue();

        if (artista == null) {
            return;
        }

        DossierArtistico dossier =

                dossierArtisticoService
                        .buscarPorArtista(
                                artista.getId());

        if (dossier == null) {

            tablaEvaluaciones.getItems().clear();

            tablaObservaciones.getItems().clear();

            return;
        }

        tablaEvaluaciones.setItems(

                FXCollections.observableArrayList(

                        dossier.getEvaluaciones()));

        tablaObservaciones.setItems(

                FXCollections.observableArrayList(

                        dossier.getObservaciones()));
    }

    @FXML
    public void agregarEvaluacion() {

        Artista artista =
                artistaCombo.getValue();

        if (artista == null) {

            mostrarError(
                    "Debes seleccionar un artista");

            return;
        }

        if (nivelCombo.getValue() == null) {

            mostrarError(
                    "Debes seleccionar un nivel");

            return;
        }

        EvaluacionDossier evaluacion =
                new EvaluacionDossier();

        evaluacion.setFecha(
                LocalDate.now());

        evaluacion.setNivel(
                nivelCombo.getValue());

        evaluacion.setComentario(
                comentarioEvaluacionArea.getText());

        evaluacion.setIdPersona(

                sesion.getUsuario()
                        .getId());

        evaluacion.setRol(

                sesion.getPerfil()
                        .name());

        dossierArtisticoService
                .agregarEvaluacion(
                        artista.getId(),
                        evaluacion);

        cargarDossier();

        nivelCombo.getSelectionModel()
                .clearSelection();

        comentarioEvaluacionArea.clear();
    }

    @FXML
    public void agregarObservacion() {

        Artista artista =
                artistaCombo.getValue();

        if (artista == null) {

            mostrarError(
                    "Debes seleccionar un artista");

            return;
        }

        ObservacionDossier observacion =
                new ObservacionDossier();

        observacion.setFecha(
                LocalDate.now());

        observacion.setTexto(
                observacionArea.getText());

        observacion.setAutor(

                sesion.getUsuario()
                        .getNombre());

        dossierArtisticoService
                .agregarObservacion(
                        artista.getId(),
                        observacion);

        cargarDossier();

        observacionArea.clear();
    }

    private void mostrarError(
            String mensaje) {

        Alert alert =

                new Alert(
                        Alert.AlertType.ERROR);

        alert.setTitle(
                "Error");

        alert.setHeaderText(
                null);

        alert.setContentText(
                mensaje);

        alert.showAndWait();
    }

    @FXML
    public void volver() {

        Perfil perfil =
                sesion.getPerfil();

        if (perfil == Perfil.ADMIN) {

            stageManager.switchScene(
                    FxmlView.ADMIN);

        } else {

            stageManager.switchScene(
                    FxmlView.COORDINADOR);
        }
    }
}
