package com.ds.application.view.components.sidebar;

import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.InputElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class HuffmanAlgorithmControlPanel extends VBox {

    private InputElement textInput;
    private HuffmanAlgorithmVisualizer visualizer;

    public HuffmanAlgorithmControlPanel(HuffmanAlgorithmVisualizer visualizer) {
        this.visualizer = visualizer;

        setPadding(new Insets(16));
        setSpacing(12);
        setPrefWidth(240);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("Panel de Control");
        LabelElement subtitle = new LabelElement("Algoritmo Huffman");
        LabelElement inputTitle = new LabelElement("Texto de entrada");

        textInput = new InputElement("Ingrese texto");

        ButtonElement generateButton = new ButtonElement("Generar");
        ButtonElement clearButton = new ButtonElement("Limpiar");

        generateButton.getNode().setOnAction(e -> generate());
        clearButton.getNode().setOnAction(e -> clear());

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                inputTitle.getNode(),
                textInput.getNode(),
                generateButton.getNode(),
                clearButton.getNode()
        );
    }

    private void generate() {
        String value = textInput.getValue().trim();

        if (!value.isEmpty()) {
            visualizer.showText(value);
        } else {
            visualizer.showText("Esperando texto desde el input...");
        }
    }

    private void clear() {
        textInput.getNode().clear();
        visualizer.clear();
    }
}