package com.ds.application.view.components.header;

import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class Views extends HBox {

    private ButtonElement binaryButton;
    private ButtonElement huffmanButton;

    public Views(Runnable showBinaryView, Runnable showHuffmanView) {
        setPadding(new Insets(14, 18, 14, 18));
        setSpacing(12);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("Visualizador de Arboles");
        title.getNode().setStyle("-fx-font-size: 19px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        binaryButton = new ButtonElement("Arbol Binario");
        huffmanButton = new ButtonElement("Huffman");

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
                title.getNode(),
                spacer,
                binaryButton.getNode(),
                huffmanButton.getNode()
        );
    }

    private void selectBinary() {
        binaryButton.getNode().setStyle(activeStyle());
        huffmanButton.getNode().setStyle(normalStyle());
    }

    private void selectHuffman() {
        huffmanButton.getNode().setStyle(activeStyle());
        binaryButton.getNode().setStyle(normalStyle());
    }

    private String activeStyle() {
        return "-fx-background-color: #2563eb;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;";
    }

    private String normalStyle() {
        return "-fx-background-color: #f3f4f6;" +
                "-fx-text-fill: #374151;" +
                "-fx-border-color: #d1d5db;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;";
    }
}