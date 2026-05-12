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

    private static final String BG = "#BFCFBB";
    private static final String CARD = "#DCE8DA";
    private static final String BORDER = "#8EA58C";
    private static final String MUTED = "#738A6E";
    private static final String TEXT = "#344C3D";
    private static final String SOFT = "#F7FBF6";
    private static final String DANGER = "#df7979ff";
    private static final String DANGER_HOVER = "#df7979ff";

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
        setPrefWidth(240);
        setMinWidth(180);
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(400);
        setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-effect: dropshadow(gaussian, rgba(52,76,61,0.12), 18, 0.25, 0, 6);"
        );

        Label title = new Label("Inspector de Nodo");
        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        valueLabel = new Label("-");
        valueLabel.setAlignment(Pos.CENTER);
        valueLabel.setMinSize(58, 58);
        valueLabel.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + MUTED + ";" +
                "-fx-border-width: 2;" +
                "-fx-background-radius: 16;" +
                "-fx-border-radius: 16;" +
                "-fx-text-fill: " + TEXT + ";" +
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
        infoBox.setPadding(new Insets(2, 0, 0, 0));
        infoBox.getChildren().addAll(
                createInfoRow("Nivel", levelValue),
                createInfoRow("Grado", degreeValue),
                createInfoRow("Tipo", typeValue),
                createChildrenSection()
        );

        statusLabel = new Label("-");
        statusLabel.setWrapText(true);
        statusLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Button deleteButton = new Button("Eliminar");
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setPrefHeight(40);
        deleteButton.setFocusTraversable(false);
        deleteButton.setStyle(deleteButtonStyle(DANGER));

        deleteButton.setOnMouseEntered(e -> deleteButton.setStyle(deleteButtonStyle(DANGER_HOVER)));
        deleteButton.setOnMouseExited(e -> deleteButton.setStyle(deleteButtonStyle(DANGER)));

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

    private HBox createInfoRow(String labelText, Label valueLabel) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(labelText);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        row.getChildren().addAll(label, spacer, valueLabel);
        return row;
    }

    private VBox createChildrenSection() {
        VBox box = new VBox(8);

        Label title = new Label("Hijos");
        title.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox childrenRow = new HBox(8);

        HBox leftBox = createChildBox("Izq:", leftChildValue);
        HBox rightBox = createChildBox("Der:", rightChildValue);

        childrenRow.getChildren().addAll(leftBox, rightBox);

        box.getChildren().addAll(title, childrenRow);
        return box;
    }

    private HBox createChildBox(String titleText, Label valueLabel) {
        HBox box = new HBox(6);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(8));
        box.setPrefWidth(105);
        box.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );

        Label title = new Label(titleText);
        title.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        box.getChildren().addAll(title, valueLabel);
        return box;
    }

    private Label createValueLabel(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-weight: bold;"
        );
        return label;
    }

    private String deleteButtonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
                "-fx-text-fill: " + SOFT + ";" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 0 12;" +
                "-fx-cursor: hand;";
    }

    public void updateNodeInfo(int value, int level, int degree, String type, String leftChild, String rightChild) {
        selectedValue = value;

        valueLabel.setText(String.valueOf(value));
        levelValue.setText(String.valueOf(level));
        degreeValue.setText(String.valueOf(degree));
        typeValue.setText(type);
        leftChildValue.setText(leftChild);
        rightChildValue.setText(rightChild);
    }

    public void updateNodeInfo(int value, int level, int height, int size) {
        updateNodeInfo(value, level, 0, "-", "-", "-");
    }

    public void updateStatus(String message) {
        statusLabel.setText(message);
    }

    public void setOnDelete(Consumer<Integer> onDelete) {
        this.onDelete = onDelete;
    }

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
