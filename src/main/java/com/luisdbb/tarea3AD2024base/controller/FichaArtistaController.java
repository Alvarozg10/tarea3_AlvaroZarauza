package com.luisdbb.tarea3AD2024base.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.*;
import com.luisdbb.tarea3AD2024base.services.ArtistaService;
import com.luisdbb.tarea3AD2024base.modelo.Sesion;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

@Component
public class FichaArtistaController {

    @FXML
    private Label nombreLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label nacionalidadLabel;

    @FXML
    private Label apodoLabel;

    @FXML
    private Label especialidadesLabel;

    @FXML
    private TextArea trayectoriaArea;

    @Autowired
    private Sesion sesion;

    @Autowired
    private StageManager stageManager;

    @Autowired
    private ArtistaService artistaService;

    @FXML
    public void initialize() {

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

        StringBuilder trayectoria = new StringBuilder();

        for (Numero n : artista.getNumeros()) {
            trayectoria.append("Espectáculo: ")
                    .append(n.getEspectaculo().getNombre())
                    .append(" | Número: ")
                    .append(n.getNombre())
                    .append("\n");
        }

        trayectoriaArea.setText(trayectoria.toString());
    }

    @FXML
    public void volver() {
        stageManager.switchScene(FxmlView.ARTISTA);
    }
}