package com.ds.application.view.components.inspector;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class NodeInspector extends VBox {

    // Labels visuales del inspector
    private Label valueLabel;
    private Label levelValue;
    private Label degreeValue;
    private Label typeValue;
    private Label leftChildValue;
    private Label rightChildValue;
    private Label statusLabel;

    private Integer selectedValue;
    private Consumer<Integer> onDelete;

    public NodeInspector() {
        setPadding(new Insets(18));
        setSpacing(14);
        setPrefWidth(260);
        setMaxHeight(360);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 16;" +
                "-fx-border-radius: 16;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 18, 0.25, 0, 6);"
        );

        Label title = new Label("Inspector de Nodo");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        valueLabel = new Label("-");
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setMinSize(58, 58);
        valueLabel.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-border-color: #2563eb;" +
                "-fx-border-width: 2;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-text-fill: #111827;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;"
        );

        HBox valueWrap = new HBox(valueLabel);
        valueWrap.setAlignment(Pos.CENTER);

        levelValue = createValueLabel("-");
        degreeValue = createValueLabel("-");
        typeValue = createValueLabel("-");
        leftChildValue = createValueLabel("-");
        rightChildValue = createValueLabel("-");

        VBox infoBox = new VBox(12);
        infoBox.getChildren().addAll(
                createInfoRow("Nivel", levelValue),
                createInfoRow("Grado", degreeValue),
                createInfoRow("Tipo", typeValue),
                createChildrenSection()
        );

        statusLabel = new Label("-");
        statusLabel.setWrapText(true);
        statusLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        Button deleteButton = new Button("Eliminar");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setStyle(
                "-fx-background-color: #dc2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 10 12;" +
                "-fx-cursor: hand;"
        );

        deleteButton.setOnAction(e -> {
            if (selectedValue != null && onDelete != null) {
                onDelete.accept(selectedValue);
            } else {
                updateStatus("Seleccione o busque un nodo primero.");
            }
        });

        getChildren().addAll(
                title,
                valueWrap,
                infoBox,
                statusLabel,
                deleteButton
        );
    }

    // Crea una fila de información tipo: título - valor
    private HBox createInfoRow(String labelText, Label valueLabel) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        row.getChildren().addAll(label, spacer, valueLabel);
        return row;
    }

    // Crea la sección de hijos izquierdo y derecho
    private VBox createChildrenSection() {
        VBox box = new VBox(8);

        Label title = new Label("Hijos");
        title.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");

        HBox childrenRow = new HBox(8);

        HBox leftBox = createChildBox("Izq:", leftChildValue);
        HBox rightBox = createChildBox("Der:", rightChildValue);

        childrenRow.getChildren().addAll(leftBox, rightBox);

        box.getChildren().addAll(title, childrenRow);
        return box;
    }

    // Crea cada cajita de hijo
    private HBox createChildBox(String titleText, Label valueLabel) {
        HBox box = new HBox(6);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(8));
        box.setPrefWidth(105);
        box.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;"
        );

        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        box.getChildren().addAll(title, valueLabel);
        return box;
    }

    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-size: 13px; -fx-text-fill: #111827; -fx-font-weight: bold;");
        return label;
    }

    // Actualiza los datos del nodo seleccionado
    public void updateNodeInfo(int value, int level, int degree, String type, String leftChild, String rightChild) {
        selectedValue = value;

        valueLabel.setText(String.valueOf(value));
        levelValue.setText(String.valueOf(level));
        degreeValue.setText(String.valueOf(degree));
        typeValue.setText(type);
        leftChildValue.setText(leftChild);
        rightChildValue.setText(rightChild);
    }

    // Método de compatibilidad por si alguna clase vieja todavía lo llama
    public void updateNodeInfo(int value, int level, int height, int size) {
        updateNodeInfo(value, level, 0, "-", "-", "-");
    }

    // Actualiza mensajes como insertado, encontrado o eliminado
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void setOnDelete(Consumer<Integer> onDelete) {
        this.onDelete = onDelete;
    }

    // Limpia todo el inspector
    public void clearInfo() {
        selectedValue = null;

        valueLabel.setText("-");
        levelValue.setText("-");
        degreeValue.setText("-");
        typeValue.setText("-");
        leftChildValue.setText("-");
        rightChildValue.setText("-");
        statusLabel.setText("-");
    }
}