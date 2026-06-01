package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.modelo.Perfil;
import com.luisdbb.tarea3AD2024base.modelo.Persona;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;
import com.luisdbb.tarea3AD2024base.services.NumeroService;
import com.luisdbb.tarea3AD2024base.services.PersonaService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

@Component
public class CrearNumeroSinEspectaculoController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField duracionField;

    @FXML
    private TextField ordenField;

    @FXML
    private ComboBox<Espectaculo> espectaculoCombo;

    @FXML
    private ListView<Artista> artistasList;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void initialize() {

        Persona usuario =
                sesion.getUsuario();

        List<Espectaculo> espectaculos;

        if (usuario != null
                && usuario.getCredenciales() != null
                && usuario.getCredenciales().getPerfil()
                == Perfil.COORDINACION) {

            espectaculos =
                    espectaculoService
                            .obtenerPorCoordinador(
                                    usuario.getId());

        } else {

            espectaculos =
                    espectaculoService
                            .obtenerTodos();
        }

        espectaculoCombo.getItems()
                .addAll(espectaculos);

        List<Persona> personas =
                personaService.obtenerTodas();

        for (Persona p : personas) {

            if (p instanceof Artista artista) {

                artistasList.getItems()
                        .add(artista);
            }
        }

        artistasList
                .getSelectionModel()
                .setSelectionMode(
                        SelectionMode.MULTIPLE);
    }

    @FXML
    public void crearNumero() {

        try {

            String nombre =
                    nombreField.getText();

            double duracion =
                    Double.parseDouble(
                            duracionField.getText());

            int orden =
                    Integer.parseInt(
                            ordenField.getText());

            Espectaculo esp =
                    espectaculoCombo.getValue();

            if (esp == null) {

                throw new RuntimeException(
                        "Debes seleccionar un espectáculo");
            }

            List<Long> artistasIds =
                    artistasList
                            .getSelectionModel()
                            .getSelectedItems()
                            .stream()
                            .map(Artista::getId)
                            .toList();

            numeroService.crearNumero(
                    nombre,
                    duracion,
                    orden,
                    esp.getId(),
                    artistasIds);

            		Alert alert =
            		        new Alert(
            		                Alert.AlertType.INFORMATION);

            		alert.setTitle(
            		        "Número creado");

            		alert.setHeaderText(
            		        null);

            		alert.setContentText(
            		        "Número creado correctamente");

            		alert.showAndWait();

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
    		            && usuario.getCredenciales() != null
    		            && usuario.getCredenciales().getPerfil()
    		            == Perfil.COORDINACION) {

    		        stageManager.switchScene(
    		                FxmlView.COORDINADOR);

    		    } else {

    		        stageManager.switchScene(
    		                FxmlView.ADMIN);
    		    }
    		}
}

