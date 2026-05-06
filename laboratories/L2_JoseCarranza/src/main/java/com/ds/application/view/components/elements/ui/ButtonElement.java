package com.ds.application.view.components.elements.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class ButtonElement {

    private Button button;

    public ButtonElement(String icon, String text) {

        // Icono (más grande)
        Label iconLabel = new Label(icon);
        iconLabel.setStyle(
                "-fx-font-size: 20px;" +  
                "-fx-text-fill: #2563eb;"
        );

        // Texto
        Label textLabel = new Label(text);
        textLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        // Contenedor horizontal (icono + texto)
        HBox content = new HBox(10); // más espacio entre icono y texto
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(iconLabel, textLabel);

        // Botón
        button = new Button();
        button.setGraphic(content); // usamos el HBox como contenido
        button.setAlignment(Pos.CENTER_LEFT);

        setActive(false);
    }

    // Cambia el estilo según si está activo o no
    public void setActive(boolean active) {
        if (active) {
            button.setStyle(
                    "-fx-background-color: #e8f0ff;" +
                    "-fx-text-fill: #2563eb;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-color: transparent transparent transparent #2563eb;" +
                    "-fx-border-width: 0 0 0 3;" +
                    "-fx-padding: 10 12;" +
                    "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #374151;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 10 12;" +
                    "-fx-cursor: hand;"
            );
        }
    }

    public Button getNode() {
        return button;
    }
}