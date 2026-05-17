package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.*;
import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import java.util.List;

@Component
public class CrearNumeroController {

    @FXML
    private TextField nombreField;

    @FXML
    private TextField duracionField;

    @FXML
    private TextField ordenField;

    @FXML
    private TextField espectaculoField;

    @FXML
    private ListView<Persona> artistasList;

    @Autowired
    private NumeroService numeroService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;
    
    @Autowired
    private EspectaculoService espectaculoService;

    @FXML
    public void initialize() {

        List<Persona> personas = personaService.obtenerTodas();

        for (Persona p : personas) {

            if (p instanceof Artista) {
                artistasList.getItems().add(p);
            }
        }

        artistasList.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        artistasList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Persona item, boolean empty) {

                super.updateItem(item, empty);

                setText(empty || item == null
                        ? null
                        : item.getNombre());
            }
        });

        espectaculoField.setVisible(false);

        espectaculoField.setManaged(false);
    }

    @FXML
    public void crearNumero() {

        try {

            String nombre = nombreField.getText();

            double duracion =
                    Double.parseDouble(duracionField.getText());

            int orden =
                    Integer.parseInt(ordenField.getText());

            Numero numero = new Numero();

            numero.setNombre(nombre);

            numero.setDuracion(duracion);

            numero.setOrden(orden);

            List<Artista> artistas = artistasList
                    .getSelectionModel()
                    .getSelectedItems()
                    .stream()
                    .map(p -> (Artista) p)
                    .toList();

            numero.setArtistas(artistas);

            sesion.getNumerosTemporales()
                    .add(numero);

            mostrarInfo(
                    "Número añadido temporalmente");

            limpiarFormulario();

        } catch (Exception e) {

            mostrarError(e.getMessage());
        }
    }

    private void mostrarError(String msg) {

        new Alert(Alert.AlertType.ERROR, msg)
                .showAndWait();
    }

    private void mostrarInfo(String msg) {

        new Alert(Alert.AlertType.INFORMATION, msg)
                .showAndWait();
    }

    @FXML
    public void volver() {

    	if (sesion.getNumerosTemporales()
    	        .size() < 3) {

    	    mostrarError(
    	            "Debes crear al menos 3 números");

    	    return;
    	}
    	
    	try {

    		espectaculoService.guardarEspectaculoCompleto(

    		        sesion.getEspectaculoTemporal(),

    		        sesion.getNumerosTemporales());

    	    mostrarInfo(
    	            "Espectáculo creado correctamente");

    	    sesion.setEspectaculoTemporal(null);

    	    sesion.getNumerosTemporales()
    	            .clear();

    	} catch (Exception e) {

    	    mostrarError(
    	            e.getMessage());

    	    return;
    	}

        if (sesion.getPerfil() == Perfil.ADMIN) {

            stageManager.switchScene(FxmlView.ADMIN);

        } else {

            stageManager.switchScene(FxmlView.COORDINADOR);
        }
    }
    
    private void limpiarFormulario() {

        nombreField.clear();

        duracionField.clear();

        ordenField.clear();

        artistasList
                .getSelectionModel()
                .clearSelection();
    }
}