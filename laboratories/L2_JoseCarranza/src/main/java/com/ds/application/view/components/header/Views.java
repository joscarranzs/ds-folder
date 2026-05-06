package com.ds.application.view.components.header;

import com.ds.application.view.components.elements.ui.ButtonElement;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class Views extends HBox {

    // Botones principales del header para cambiar de vista
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
        // Configuración visual del header superior
        setPadding(new Insets(12, 20, 0, 20));
        setSpacing(22);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #dbe1ea;"
        );

        // Botones del header con icono y texto separados
        binaryButton = new ButtonElement("●", "ARBOL BINARIO");
        huffmanButton = new ButtonElement("◆", "ALGORITMO DE HUFFMAN");
        minHeapButton = new ButtonElement("▾", "MONTON MINIMO");
        maxHeapButton = new ButtonElement("▴", "MONTON MAXIMO");

        // Evento para mostrar la vista del árbol binario
        binaryButton.getNode().setOnAction(e -> {
            showBinaryView.run();
            selectBinary();
        });

        // Evento para mostrar la vista del algoritmo Huffman
        huffmanButton.getNode().setOnAction(e -> {
            showHuffmanView.run();
            selectHuffman();
        });

        // Evento para mostrar la vista de montón mínimo
        minHeapButton.getNode().setOnAction(e -> {
            showMinHeapView.run();
            selectMinHeap();
        });

        // Evento para mostrar la vista de montón máximo
        maxHeapButton.getNode().setOnAction(e -> {
            showMaxHeapView.run();
            selectMaxHeap();
        });

        // Vista activa inicial
        selectBinary();

        // Agrego los botones al header
        getChildren().addAll(
                binaryButton.getNode(),
                huffmanButton.getNode(),
                minHeapButton.getNode(),
                maxHeapButton.getNode()
        );
    }

    // Marca como activa la pestaña de árbol binario
    private void selectBinary() {
        clearSelection();
        binaryButton.getNode().setStyle(activeTabStyle());
    }

    // Marca como activa la pestaña de Huffman
    private void selectHuffman() {
        clearSelection();
        huffmanButton.getNode().setStyle(activeTabStyle());
    }

    // Marca como activa la pestaña de montón mínimo
    private void selectMinHeap() {
        clearSelection();
        minHeapButton.getNode().setStyle(activeTabStyle());
    }

    // Marca como activa la pestaña de montón máximo
    private void selectMaxHeap() {
        clearSelection();
        maxHeapButton.getNode().setStyle(activeTabStyle());
    }

    // Limpia visualmente todos los botones antes de activar uno
    private void clearSelection() {
        binaryButton.getNode().setStyle(normalTabStyle());
        huffmanButton.getNode().setStyle(normalTabStyle());
        minHeapButton.getNode().setStyle(normalTabStyle());
        maxHeapButton.getNode().setStyle(normalTabStyle());
    }

    // Estilo para la pestaña activa del header
    private String activeTabStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #2563eb;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: transparent transparent #2563eb transparent;" +
                "-fx-border-width: 0 0 2 0;" +
                "-fx-padding: 12 0 12 0;" +
                "-fx-cursor: hand;";
    }

    // Estilo para la pestaña inactiva del header
    private String normalTabStyle() {
        return "-fx-background-color: transparent;" +
                "-fx-text-fill: #6b7280;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 12 0 12 0;" +
                "-fx-cursor: hand;";
    }
}