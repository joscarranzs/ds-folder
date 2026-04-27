package com.ds.application.view.components.inspector;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NodeInspector extends VBox {

    private Label valueLabel;
    private Label levelLabel;
    private Label heightLabel;
    private Label sizeLabel;
    private Label traversalLabel;
    private Label statusLabel;

    public NodeInspector() {
        setPadding(new Insets(18));
        setSpacing(10);
        setPrefWidth(230);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #e5e7eb;");

        Label title = new Label("Inspector de nodo");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        valueLabel = new Label("Valor: -");
        levelLabel = new Label("Nivel: -");
        heightLabel = new Label("Altura: -");
        sizeLabel = new Label("Nodos: -");

        Label traversalTitle = new Label("Recorrido");
        traversalTitle.setStyle("-fx-font-weight: bold;");

        traversalLabel = new Label("-");
        traversalLabel.setWrapText(true);

        statusLabel = new Label("-");
        statusLabel.setWrapText(true);

        getChildren().addAll(
            title,
            valueLabel,
            levelLabel,
            heightLabel,
            sizeLabel,
            traversalTitle,
            traversalLabel,
            statusLabel
        );
    }

    public void updateNodeInfo(int value, int level, int height, int size) {
        valueLabel.setText("Valor: " + value);
        levelLabel.setText("Nivel: " + level);
        heightLabel.setText("Altura: " + height);
        sizeLabel.setText("Nodos: " + size);
    }

    public void updateTraversal(String type, String result) {
        traversalLabel.setText(type + ": " + result);
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void clearInfo() {
        valueLabel.setText("Valor: -");
        levelLabel.setText("Nivel: -");
        heightLabel.setText("Altura: -");
        sizeLabel.setText("Nodos: -");
        traversalLabel.setText("-");
        statusLabel.setText("-");
    }
}
