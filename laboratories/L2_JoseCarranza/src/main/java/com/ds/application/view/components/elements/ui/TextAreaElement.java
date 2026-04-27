package com.ds.application.view.components.elements.ui;

import javafx.scene.control.TextArea;

public class TextAreaElement {

    private TextArea textArea;

    public TextAreaElement(String placeholder) {
        textArea = new TextArea();
        textArea.setPromptText(placeholder);
        textArea.setWrapText(true);
        setActive(false);
    }

    public void setActive(boolean active) {
        if (active) {
            textArea.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #2563eb;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 10;"
            );
        } else {
            textArea.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #dbe1ea;" +
                    "-fx-border-radius: 8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 8 10;"
            );
        }
    }

    public String getValue() {
        return textArea.getText();
    }

    public TextArea getNode() {
        return textArea;
    }
}