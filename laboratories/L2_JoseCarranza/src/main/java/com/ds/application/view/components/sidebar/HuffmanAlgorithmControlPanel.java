package com.ds.application.view.components.sidebar;

import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class HuffmanAlgorithmControlPanel extends VBox {

    private TextField textField;
    private HuffmanAlgorithmVisualizer visualizer;

    public HuffmanAlgorithmControlPanel(HuffmanAlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;

        setPadding(new Insets(15));
        setSpacing(12);
        setPrefWidth(240);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #e5e7eb;");

        Label title = new Label("Algoritmo Huffman");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        textField = new TextField();
        textField.setPromptText("Ingrese texto");

        Button generateButton = new Button("Generar");
        Button clearButton = new Button("Limpiar");

        generateButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxWidth(Double.MAX_VALUE);

        generateButton.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #d1d5db; -fx-cursor: hand;");
        clearButton.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #d1d5db; -fx-cursor: hand;");

        generateButton.setOnAction(e -> generate());
        clearButton.setOnAction(e -> clear());

        getChildren().addAll(
            title,
            textField,
            generateButton,
            clearButton
        );
    }

    private void generate() {
        String value = textField.getText().trim();

        if (!value.isEmpty()) {
            visualizer.showText(value);
        }
    }

    private void clear() {
        textField.clear();
        visualizer.clear();
    }
}