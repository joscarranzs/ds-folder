package com.ds.application.view.components.header;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Views extends HBox {

    private Button binaryButton;
    private Button huffmanButton;

    public Views(Runnable showBinaryView, Runnable showHuffmanView) {
        setPadding(new Insets(12));
        setSpacing(12);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #e5e7eb;");

        Label title = new Label("Visualizador de Arboles");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        binaryButton = new Button("Arbol Binario");
        huffmanButton = new Button("Huffman");

        binaryButton.setOnAction(event -> {
            showBinaryView.run();
            selectBinary();
        });

        huffmanButton.setOnAction(event -> {
            showHuffmanView.run();
            selectHuffman();
        });

        selectBinary();

        getChildren().addAll(title, binaryButton, huffmanButton);
    }

    private void selectBinary() {
        binaryButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-cursor: hand;");
        huffmanButton.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #d1d5db; -fx-cursor: hand;");
    }

    private void selectHuffman() {
        huffmanButton.setStyle("-fx-background-color: #2563eb; -fx-text-fill: white; -fx-cursor: hand;");
        binaryButton.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #d1d5db; -fx-cursor: hand;");
    }
}