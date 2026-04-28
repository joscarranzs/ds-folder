package com.ds.application.view.components.header;

import com.ds.application.view.components.elements.ui.ButtonElement;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Views extends HBox {

    private ButtonElement binaryButton;
    private ButtonElement huffmanButton;

    public Views(Runnable showBinaryView, Runnable showHuffmanView) {
        setPadding(new Insets(12, 20, 0, 20));
        setSpacing(22);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #dbe1ea;");

        binaryButton = new ButtonElement("ARBOL BINARIO");
        huffmanButton = new ButtonElement("ALGORITMO DE HUFFMAN");

        binaryButton.getNode().setOnAction(e -> {
            showBinaryView.run();
            selectBinary();
        });

        huffmanButton.getNode().setOnAction(e -> {
            showHuffmanView.run();
            selectHuffman();
        });

        selectBinary();

        getChildren().addAll(
                binaryButton.getNode(),
                huffmanButton.getNode()
        );
    }

    private void selectBinary() {
        binaryButton.getNode().setStyle(activeTabStyle());
        huffmanButton.getNode().setStyle(normalTabStyle());
    }

    private void selectHuffman() {
        huffmanButton.getNode().setStyle(activeTabStyle());
        binaryButton.getNode().setStyle(normalTabStyle());
    }

    private String activeTabStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #2563eb;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: transparent transparent #2563eb transparent;" +
                "-fx-border-width: 0 0 2 0;" +
                "-fx-padding: 12 0 12 0;" +
                "-fx-cursor: hand;";
    }

    private String normalTabStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #6b7280;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12 0 12 0;" +
                "-fx-cursor: hand;";
    }
}