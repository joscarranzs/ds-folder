package com.ds.application.view.components.inspector;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NodeInspector extends VBox {

    private Label valueLabel;
    private Label positionLabel;
    private Label levelLabel;
    private Label traversalLabel;

    public NodeInspector() {
        setPadding(new Insets(15));
        setSpacing(12);
        setPrefWidth(240);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #e5e7eb;");

        Label title = new Label("Inspector");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        valueLabel = new Label("Valor: -");
        positionLabel = new Label("Posicion: -");
        levelLabel = new Label("Nivel: -");

        Label traversalTitle = new Label("Recorrido");
        traversalTitle.setStyle("-fx-font-weight: bold;");

        traversalLabel = new Label("-");
        traversalLabel.setWrapText(true);

        getChildren().addAll(
            title,
            valueLabel,
            positionLabel,
            levelLabel,
            traversalTitle,
            traversalLabel
        );
    }

    public void updateInfo(String value, int position, int level) {
        valueLabel.setText("Valor: " + value);
        positionLabel.setText("Posicion: " + position);
        levelLabel.setText("Nivel: " + level);
    }

    public void updateTraversal(String type, String result) {
        traversalLabel.setText(type + ": " + result);
    }

    public void clearInfo() {
        valueLabel.setText("Valor: -");
        positionLabel.setText("Posicion: -");
        levelLabel.setText("Nivel: -");
        traversalLabel.setText("-");
    }
}