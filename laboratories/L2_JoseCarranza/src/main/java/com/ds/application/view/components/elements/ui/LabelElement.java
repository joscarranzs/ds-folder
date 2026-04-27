package com.ds.application.view.components.elements.ui;

import javafx.scene.control.Label;

public class LabelElement {

    private Label label;

    public LabelElement(String text) {
        label = new Label(text);
        setDefaultStyle();
    }

    private void setDefaultStyle() {
        label.setStyle(
                "-fx-text-fill: #1e293b;" +
                "-fx-font-size: 13px;"
        );
    }

    public void setTitle() {
        label.setStyle(
                "-fx-text-fill: #0f172a;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;"
        );
    }

    public void setSubtitle() {
        label.setStyle(
                "-fx-text-fill: #64748b;" +
                "-fx-font-size: 12px;"
        );
    }

    public void setMuted() {
        label.setStyle(
                "-fx-text-fill: #94a3b8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );
    }

    public Label getNode() {
        return label;
    }
}