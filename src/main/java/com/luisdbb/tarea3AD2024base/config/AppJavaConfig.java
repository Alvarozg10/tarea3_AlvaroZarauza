package com.luisdbb.tarea3AD2024base.config;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppJavaConfig {

    @Autowired
    private SpringFXMLLoader springFXMLLoader;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    public StageManager stageManager() {
        return new StageManager(springFXMLLoader);
    }
}