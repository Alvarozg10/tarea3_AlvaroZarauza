package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

@Component
public class CoordinadorController {

    @Autowired
    private StageManager stageManager;
    
    @Autowired
    private Sesion sesion;

    @FXML
    public void crearEspectaculo() {
        stageManager.switchScene(FxmlView.CREAR_ESPECTACULO);
    }
    
    @FXML
    public void modificarEspectaculo() {
        stageManager.switchScene(FxmlView.MODIFICAR_ESPECTACULO);
    }
    
    @FXML
    public void crearNumero() {

        stageManager.switchScene(FxmlView.CREAR_NUMERO);
    }
    
    @FXML
    public void abrirCrearNumero() {

    	stageManager.switchScene(FxmlView.CREAR_NUMERO_SIN_ESPECTACULO); 	
    }

    @FXML
    public void modificarNumero() {
        stageManager.switchScene(FxmlView.MODIFICAR_NUMERO);
    }
    
    @FXML
    public void borrarNumero() {
    	stageManager.switchScene(FxmlView.BORRAR_NUMERO);
    }
    
    @FXML
    public void verEspectaculos() {
        stageManager.switchScene(FxmlView.VER_ESPECTACULO);
    }
    
    @FXML
    public void abrirRegistrarIncidencia() {

        stageManager.switchScene(
                FxmlView.REGISTRAR_INCIDENCIA);
    }
    
    @FXML
    public void abrirResolverIncidencia() {

        stageManager.switchScene(
                FxmlView.RESOLVER_INCIDENCIA);
    }
    
    @FXML
    public void abrirConsultarIncidencias() {

        stageManager.switchScene(
                FxmlView.CONSULTAR_INCIDENCIAS);
    }
    
    @FXML public void abrirGestionDossier() { 
    	
    	stageManager.switchScene( 
    			FxmlView.GESTIONAR_DOSSIER); 
    }
    
    @FXML
    public void cerrarSesion() {

        sesion.setUsuario(null); 

        stageManager.switchScene(FxmlView.LOGIN); 
    }

    }