package com.luisdbb.tarea3AD2024base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ExportarEspectaculosController {

    @Autowired
    private EspectaculoService espectaculoService;

    @Autowired
    private StageManager stageManager;

    @FXML
    public void exportar() {

        List<Espectaculo> lista = espectaculoService.obtenerTodos();

        try (FileWriter writer = new FileWriter("espectaculos.txt")) {

            for (Espectaculo e : lista) {
                writer.write("ID: " + e.getId() + "\n");
                writer.write("Nombre: " + e.getNombre() + "\n");
                writer.write("Inicio: " + e.getFechaInicio() + "\n");
                writer.write("Fin: " + e.getFechaFin() + "\n");
                writer.write("--------------------------\n");
            }

            mostrarInfo("Exportado correctamente a espectaculos.txt");

        } catch (IOException e) {
            mostrarError("Error al exportar");
        }
    }

    @FXML
    public void volver() {
        stageManager.switchScene(FxmlView.ADMIN);
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.showAndWait();
    }

    private void mostrarInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.showAndWait();
    }
}