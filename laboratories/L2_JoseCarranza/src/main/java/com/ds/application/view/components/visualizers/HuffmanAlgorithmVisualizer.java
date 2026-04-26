package com.ds.application.view.components.visualizers;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HuffmanAlgorithmVisualizer extends StackPane {

    private Label text;

    public HuffmanAlgorithmVisualizer() {
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f8fafc;");

        text = new Label("Area de visualizacion Huffman");
        text.setStyle("-fx-font-size: 16px;");

        getChildren().add(text);
    }

    public void showText(String value) {
        text.setText("Texto ingresado:\n" + value);
    }

    public void clear() {
        text.setText("Area de visualizacion Huffman");
    }
}