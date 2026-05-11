package com.ds.application.view.components.elements.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ButtonElement {

    private final Button button;
    private final Label iconLabel;
    private final Label textLabel;
    private final HBox content;

    private boolean active = false;

    public ButtonElement(String icon, String text) {

        iconLabel = new Label(icon);
        textLabel = new Label(text);

        content = new HBox(8);
        content.setAlignment(Pos.CENTER);
        content.setFillHeight(true);

        if (icon != null && !icon.isBlank()) {
            content.getChildren().add(iconLabel);
        }

        content.getChildren().add(textLabel);

        button = new Button();
        button.setGraphic(content);
        button.setText(null);
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        button.setPrefHeight(40);
        button.setMinHeight(40);
        button.setMaxHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setFocusTraversable(false);

        setActive(false);

        button.setOnMouseEntered(e -> {
            if (!active) {
                applyHoverStyle();
            }
        });

        button.setOnMouseExited(e -> {
            if (!active) {
                applyNormalStyle();
            }
        });
    }

    public void setActive(boolean active) {
        this.active = active;

        if (active) {
            applyActiveStyle();
        } else {
            applyNormalStyle();
        }
    }

    private void applyActiveStyle() {
        button.setStyle(
                "-fx-background-color: #344C3D;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #344C3D;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        iconLabel.setStyle(iconStyle("#F7FBF6"));
        textLabel.setStyle(textStyle("#F7FBF6"));
    }

    private void applyNormalStyle() {
        button.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #BFCFBB;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        iconLabel.setStyle(iconStyle("#738A6E"));
        textLabel.setStyle(textStyle("#344C3D"));
    }

    private void applyHoverStyle() {
        button.setStyle(
                "-fx-background-color: #BFCFBB;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        iconLabel.setStyle(iconStyle("#344C3D"));
        textLabel.setStyle(textStyle("#344C3D"));
    }

    private String iconStyle(String color) {
        return "-fx-font-size: 14px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: " + color + ";";
    }

    private String textStyle(String color) {
        return "-fx-font-size: 13px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: " + color + ";";
    }

    public Button getNode() {
        return button;
    }
}