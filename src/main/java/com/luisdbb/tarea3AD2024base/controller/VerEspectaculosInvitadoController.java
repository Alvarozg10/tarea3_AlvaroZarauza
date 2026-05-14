package com.luisdbb.tarea3AD2024base.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import java.time.LocalDate;
import java.util.List;

@Component
public class VerEspectaculosInvitadoController {

    @FXML private TableView<Espectaculo> tablaEspectaculos;
    @FXML private TableColumn<Espectaculo, Long> colEspId;
    @FXML private TableColumn<Espectaculo, String> colEspNombre;
    @FXML private TableColumn<Espectaculo, LocalDate> colEspInicio;
    @FXML private TableColumn<Espectaculo, LocalDate> colEspFin;

    @FXML private TableView<Numero> tablaNumeros;
    @FXML private TableColumn<Numero, Long> colNumId;
    @FXML private TableColumn<Numero, String> colNumNombre;
    @FXML private TableColumn<Numero, Double> colNumDuracion;

    @FXML private TableView<Artista> tablaArtistas;
    @FXML private TableColumn<Artista, String> colArtNombre;
    @FXML private TableColumn<Artista, String> colArtNac;
    @FXML private TableColumn<Artista, String> colArtApodo;

    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private StageManager stageManager;

    @Autowired
    private Sesion sesion;

    @FXML
    public void initialize() {

        colEspId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEspNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colEspFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));

        tablaEspectaculos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        List<Espectaculo> lista = espectaculoService.obtenerTodos();
        tablaEspectaculos.setItems(FXCollections.observableArrayList(lista));
        
    }
    
    	@FXML
    	public void volver() {

    	    Perfil perfil = sesion.getPerfil();

    	    if (perfil == null) {

    	        stageManager.switchScene(FxmlView.LOGIN);
    	        return;
    	    }
    	}
}


