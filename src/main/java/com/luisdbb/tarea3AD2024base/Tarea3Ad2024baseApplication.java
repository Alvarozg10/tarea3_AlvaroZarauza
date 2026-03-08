package com.luisdbb.tarea3AD2024base;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.luisdbb.tarea3AD2024base.view.FxmlView;
import com.luisdbb.tarea3AD2024base.config.StageManager;

@SpringBootApplication
public class Tarea3Ad2024baseApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {

        context = new SpringApplicationBuilder(Tarea3Ad2024baseApplication.class)
                .run();

    }

    @Override
    public void start(Stage primaryStage) {

        StageManager stageManager = context.getBean(StageManager.class);

        stageManager.setPrimaryStage(primaryStage);

        stageManager.switchScene(FxmlView.LOGIN);

    }

    @Override
    public void stop() {

        context.close();

    }

    public static void main(String[] args) {
        launch(args);
    }
}