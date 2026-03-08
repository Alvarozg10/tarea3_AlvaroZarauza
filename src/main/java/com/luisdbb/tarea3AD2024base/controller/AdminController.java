package com.luisdbb.tarea3AD2024base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

import javafx.event.ActionEvent;

@Component
public class AdminController {

    @Autowired
    private StageManager stageManager;

    public void registrarPersona(ActionEvent event) {
        stageManager.switchScene(FxmlView.REGISTRAR_PERSONA);
    }
}