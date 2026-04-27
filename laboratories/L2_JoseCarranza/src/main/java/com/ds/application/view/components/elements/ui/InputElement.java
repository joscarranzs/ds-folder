package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextField;

public class InputElement {

    private TextField input;

    public InputElement(String placeholder) {
        input = new TextField();
        input.setPromptText(placeholder);
        setActive(false);
    }

    public void setActive(boolean active) {
        if (active) {
            input.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #2563eb;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 6 10;"
            );
        } else {
            input.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #dbe1ea;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 6 10;"
            );
        }
    }

    public String getValue() {
        return input.getText();
    }

    public TextField getNode() {
        return input;
    }
}