package com.ds.application.view.components.header;

import com.ds.application.view.components.elements.ui.ButtonElement;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Views extends HBox {

    private ButtonElement binaryButton;
    private ButtonElement huffmanButton;
    private ButtonElement minHeapButton;
    private ButtonElement maxHeapButton;

    public Views(
            Runnable showBinaryView,
            Runnable showHuffmanView,
            Runnable showMinHeapView,
            Runnable showMaxHeapView
    ) {

        // Header superior con tabs de navegación
        setPadding(new Insets(12, 20, 12, 20));
        setSpacing(12);

        setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-border-color: #BFCFBB;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Tabs principales
        binaryButton = new ButtonElement("", "ARBOL BINARIO");
        huffmanButton = new ButtonElement("", "ALGORITMO DE HUFFMAN");
        minHeapButton = new ButtonElement("", "MONTON MINIMO");
        maxHeapButton = new ButtonElement("", "MONTON MAXIMO");

        // Tamaños individuales para que el texto quede cómodo
        resizeTab(binaryButton, 210);
        resizeTab(huffmanButton, 290);
        resizeTab(minHeapButton, 240);
        resizeTab(maxHeapButton, 240);

        // Eventos de navegación
        binaryButton.getNode().setOnAction(e -> {
            showBinaryView.run();
            selectBinary();
        });

        huffmanButton.getNode().setOnAction(e -> {
            showHuffmanView.run();
            selectHuffman();
        });

        minHeapButton.getNode().setOnAction(e -> {
            showMinHeapView.run();
            selectMinHeap();
        });

        maxHeapButton.getNode().setOnAction(e -> {
            showMaxHeapView.run();
            selectMaxHeap();
        });

        // Agrego tabs al header
        getChildren().addAll(
                binaryButton.getNode(),
                huffmanButton.getNode(),
                minHeapButton.getNode(),
                maxHeapButton.getNode()
        );

        // Vista inicial
        selectBinary();
    }

    // Ajusta tamaño visual uniforme de cada tab
    private void resizeTab(ButtonElement button, double width) {

        button.getNode().setPrefHeight(40);
        button.getNode().setMinHeight(40);
        button.getNode().setMaxHeight(40);

        button.getNode().setPrefWidth(width);
        button.getNode().setMinWidth(width);
        button.getNode().setMaxWidth(width);
    }

    // Selecciona árbol binario
    private void selectBinary() {
        clearSelection();
        binaryButton.setActive(true);
    }

    // Selecciona Huffman
    private void selectHuffman() {
        clearSelection();
        huffmanButton.setActive(true);
    }

    // Selecciona montón mínimo
    private void selectMinHeap() {
        clearSelection();
        minHeapButton.setActive(true);
    }

    // Selecciona montón máximo
    private void selectMaxHeap() {
        clearSelection();
        maxHeapButton.setActive(true);
    }

    // Limpia selección visual actual
    private void clearSelection() {

        binaryButton.setActive(false);
        huffmanButton.setActive(false);
        minHeapButton.setActive(false);
        maxHeapButton.setActive(false);
    }
}