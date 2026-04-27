package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Button;

public class ButtonElement {

    private Button button;

    public ButtonElement(String text) {
        button = new Button(text);
        setActive(false);
    }

    public void setActive(boolean active) {
        if (active) {
            button.setStyle(
                "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8 14;" +
                "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-text-fill: #1e293b;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #e2e8f0;" +
                "-fx-border-radius: 10;" +
                "-fx-padding: 8 14;" +
                "-fx-cursor: hand;"
            );
        }
    }

    public Button getNode() {
        return button;
    }
}