package com.ds.application.view.components.sidebar;


public class HuffmanAlgorithmControlPanel extends VBox {

    private final TextAreaElement textInput;
    private final EncodingController controller;

    public HuffmanAlgorithmControlPanel(HuffmanAlgorithmVisualizer visualizer) {
        this.controller = new EncodingController(visualizer);

        setPadding(new Insets(18));
        setSpacing(14);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 18, 0.25, 0, 6);"
        );

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ALGORITMO HUFFMAN");
        subtitle.setSubtitle();

        LabelElement inputTitle = new LabelElement("TEXTO DE ENTRADA");
        inputTitle.setMuted();

        textInput = new TextAreaElement("Ingrese texto para codificar");
        textInput.getNode().setPrefRowCount(5);
        textInput.getNode().setMaxHeight(120);

        ButtonElement generateButton = new ButtonElement("Generar Huffman");
        ButtonElement clearButton = new ButtonElement("Limpiar");

        generateButton.setActive(true);
        clearButton.setActive(false);

        generateButton.getNode().setMaxWidth(Double.MAX_VALUE);
        clearButton.getNode().setMaxWidth(Double.MAX_VALUE);

        generateButton.getNode().setOnAction(e -> controller.generate(textInput.getValue()));

        clearButton.getNode().setOnAction(e -> {
            textInput.getNode().clear();
            controller.clear();
        });

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                inputTitle.getNode(),
                textInput.getNode(),
                generateButton.getNode(),
                clearButton.getNode()
        );
    }
}