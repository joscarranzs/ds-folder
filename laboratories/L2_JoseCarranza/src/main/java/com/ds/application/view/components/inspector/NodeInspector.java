package com.ds.application.view.components.inspector;

import com.ds.application.view.components.elements.ui.ButtonElement;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class NodeInspector extends VBox {

    private Label valueLabel;
    private Label levelLabel;
    private Label heightLabel;
    private Label sizeLabel;
    private Label traversalLabel;
    private Label statusLabel;

    private ButtonElement deleteButton;
    private Integer selectedValue;
    private Consumer<Integer> onDelete;

    public NodeInspector() {
        setPadding(new Insets(22));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-effect: dropshadow(gaussian, rgba(15,23,42,0.10), 18, 0.2, 0, 6);"
        );

        Label title = new Label("Inspector de nodo");
        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        valueLabel = createInfoLabel("Valor: -");
        levelLabel = createInfoLabel("Nivel: -");
        heightLabel = createInfoLabel("Altura: -");
        sizeLabel = createInfoLabel("Nodos: -");

        Label traversalTitle = new Label("Recorrido");
        traversalTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #374151;"
        );

        traversalLabel = createInfoLabel("-");
        traversalLabel.setWrapText(true);

        statusLabel = createInfoLabel("-");
        statusLabel.setWrapText(true);

        deleteButton = new ButtonElement("Eliminar nodo");
        deleteButton.getNode().setMaxWidth(Double.MAX_VALUE);
        deleteButton.getNode().setStyle(
                "-fx-background-color: #dc2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 9 12;" +
                "-fx-cursor: hand;"
        );

        deleteButton.getNode().setOnAction(e -> {
            if (selectedValue != null && onDelete != null) {
                onDelete.accept(selectedValue);
            } else {
                updateStatus("Seleccione o busque un nodo primero.");
            }
        });

        getChildren().addAll(
                title,
                valueLabel,
                levelLabel,
                heightLabel,
                sizeLabel,
                traversalTitle,
                traversalLabel,
                statusLabel,
                deleteButton.getNode()
        );
    }

    private Label createInfoLabel(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #4b5563;"
        );
        return label;
    }

    public void updateNodeInfo(int value, int level, int height, int size) {
        selectedValue = value;

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

    public void setOnDelete(Consumer<Integer> onDelete) {
        this.onDelete = onDelete;
    }

    public void clearInfo() {
        selectedValue = null;

        valueLabel.setText("Valor: -");
        levelLabel.setText("Nivel: -");
        heightLabel.setText("Altura: -");
        sizeLabel.setText("Nodos: -");
        traversalLabel.setText("-");
        statusLabel.setText("-");
    }
}
    }

    public void updateTraversal(String type, String result) {
        traversalLabel.setText(type + ": " + result);
    }

<<<<<<< HEAD
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void setOnDelete(Consumer<Integer> onDelete) {
        this.onDelete = onDelete;
    }

    public void clearInfo() {
        selectedValue = null;

        valueLabel.setText("Valor: -");
        levelLabel.setText("Nivel: -");
        heightLabel.setText("Altura: -");
        sizeLabel.setText("Nodos: -");
        traversalLabel.setText("-");
        statusLabel.setText("-");
=======
    public void clearInfo() {
        valueLabel.setText("Valor: -");
        positionLabel.setText("Posicion: -");
        levelLabel.setText("Nivel: -");
        traversalLabel.setText("-");
>>>>>>> origin/main
    }
}
