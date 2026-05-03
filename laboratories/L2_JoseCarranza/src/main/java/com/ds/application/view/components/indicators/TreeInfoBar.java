package com.ds.application.view.components.indicators;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TreeInfoBar extends HBox {

    private Label traversalValue;
    private Label parentNodesValue;
    private Label leafNodesValue;
    private Label depthValue;
    private Label degreeValue;

    public TreeInfoBar() {
        setSpacing(12);
        setPadding(new Insets(12));
        setAlignment(Pos.CENTER_LEFT);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 14, 0.20, 0, 4);"
        );

        traversalValue = new Label("-");
        parentNodesValue = new Label("-");
        leafNodesValue = new Label("-");
        depthValue = new Label("0");
        degreeValue = new Label("0");

        getChildren().addAll(
                createInfoCard("Recorrido", traversalValue),
                createInfoCard("Nodos padre", parentNodesValue),
                createInfoCard("Nodos hoja", leafNodesValue),
                createInfoCard("Profundidad", depthValue),
                createInfoCard("Grado", degreeValue)
        );
    }

    // Crea cada tarjeta de información inferior
    private VBox createInfoCard(String titleText, Label valueLabel) {
        VBox card = new VBox(4);
        card.setMinWidth(150);
        card.setPadding(new Insets(10, 12, 10, 12));
        card.setStyle(
                "-fx-background-color: #f1f5f9;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;"
        );

        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 11px; -fx-text-fill: #64748b; -fx-font-weight: bold;");

        valueLabel.setWrapText(true);
        valueLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #111827; -fx-font-weight: bold;");

        card.getChildren().addAll(title, valueLabel);
        return card;
    }

    // Actualiza los datos generales del árbol completo
    public void updateTreeData(String parents, String leaves, int depth, int degree) {
        parentNodesValue.setText(parents == null || parents.isEmpty() ? "-" : parents);
        leafNodesValue.setText(leaves == null || leaves.isEmpty() ? "-" : leaves);
        depthValue.setText(String.valueOf(depth));
        degreeValue.setText(String.valueOf(degree));
    }

    // Actualiza el recorrido mostrado abajo
    public void updateTraversal(String name, String result) {
        traversalValue.setText(name + ": " + result);
    }

    // Limpia solo el recorrido cuando hago una operación que no sea recorrido
    public void clearTraversal() {
        traversalValue.setText("-");
    }

    // Limpia la barra inferior
    public void clear() {
        traversalValue.setText("-");
        parentNodesValue.setText("-");
        leafNodesValue.setText("-");
        depthValue.setText("0");
        degreeValue.setText("0");
    }
}