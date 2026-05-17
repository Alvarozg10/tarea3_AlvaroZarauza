package com.luisdbb.tarea3AD2024base;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.luisdbb.tarea3AD2024base.config.StageManager;
import com.luisdbb.tarea3AD2024base.modelo.Artista;
import com.luisdbb.tarea3AD2024base.modelo.Coordinacion;
import com.luisdbb.tarea3AD2024base.modelo.Credenciales;
import com.luisdbb.tarea3AD2024base.modelo.Espectaculo;
import com.luisdbb.tarea3AD2024base.modelo.Numero;
import com.luisdbb.tarea3AD2024base.modelo.Persona;
import com.luisdbb.tarea3AD2024base.view.FxmlView;

@SpringBootApplication

@EntityScan(
        basePackageClasses = {

                Persona.class,

                Credenciales.class,

                Artista.class,

                Coordinacion.class,

                Espectaculo.class,

                Numero.class
        })

public class Tarea3Ad2024baseApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {

        context = new SpringApplicationBuilder(
                Tarea3Ad2024baseApplication.class)

                .run();
    }

    @Override
    public void start(
            Stage primaryStage) {

        StageManager stageManager =
                context.getBean(
                        StageManager.class);

        stageManager.setPrimaryStage(
                primaryStage);

        stageManager.switchScene(
                FxmlView.LOGIN);
    }

    @Override
    public void stop() {

        context.close();
    }

    public static void main(
            String[] args) {

        launch(args);
    }
}