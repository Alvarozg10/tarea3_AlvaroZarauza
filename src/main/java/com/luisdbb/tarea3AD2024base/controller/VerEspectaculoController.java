package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.EspectaculoService;

import java.util.List;

@Component
public class VerEspectaculoController {

    @FXML
    private ComboBox<Espectaculo> espectaculoCombo;

    @FXML
    private TextArea resultadoArea;

    @Autowired
    private EspectaculoService espectaculoService;

    @FXML
    public void initialize() {

        List<Espectaculo> lista = espectaculoService.obtenerTodos();

        espectaculoCombo.getItems().addAll(lista);

        espectaculoCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Espectaculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });

        espectaculoCombo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Espectaculo item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getNombre());
            }
        });
    }

    @FXML
    public void verDetalle() {

        Espectaculo seleccionado = espectaculoCombo.getValue();

        if (seleccionado == null) {
            mostrarError("Selecciona un espectáculo");
            return;
        }

        Espectaculo esp = espectaculoService.obtenerEspectaculoCompleto(seleccionado.getId());

        StringBuilder texto = new StringBuilder();

        texto.append("=== ESPECTÁCULO ===\n");
        texto.append("ID: ").append(esp.getId()).append("\n");
        texto.append("Nombre: ").append(esp.getNombre()).append("\n");
        texto.append("Fechas: ")
                .append(esp.getFechaInicio())
                .append(" - ")
                .append(esp.getFechaFin())
                .append("\n\n");

        Coordinacion c = esp.getCoordinador();

        texto.append("=== COORDINADOR ===\n");
        texto.append("Nombre: ").append(c.getNombre()).append("\n");
        texto.append("Email: ").append(c.getEmail()).append("\n");
        texto.append("Senior: ").append(c.isSenior() ? "Sí" : "No").append("\n\n");

        texto.append("=== NÚMEROS ===\n");

        for (Numero n : esp.getNumeros()) {

            texto.append("\n--- Número ---\n");
            texto.append("ID: ").append(n.getId()).append("\n");
            texto.append("Nombre: ").append(n.getNombre()).append("\n");
            texto.append("Duración: ").append(n.getDuracion()).append("\n");

            texto.append("Artistas:\n");

            for (Artista a : n.getArtistas()) {

                texto.append(" - ").append(a.getNombre())
                        .append(" (").append(a.getNacionalidad()).append(")")
                        .append(" | Apodo: ").append(a.getApodo())
                        .append(" | Especialidades: ");

                String espStr = a.getEspecialidades()
                        .stream()
                        .map(Enum::name)
                        .reduce("", (x, y) -> x + " " + y);

                texto.append(espStr).append("\n");
            }
        }

        resultadoArea.setText(texto.toString());
    }

    private void mostrarError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.showAndWait();
    }
}