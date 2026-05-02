package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.huffman.EncodingController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.elements.ui.TextAreaElement;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HuffmanAlgorithmControlPanel extends VBox {
    // Campo donde el usuario escribe el texto que quiere codificar
    private final TextAreaElement textInput;
    // Controlador encargado de procesar el texto con el algoritmo Huffman
    private final EncodingController controller;
    //Botones nuevos para modo de huffman txt o weight
    private ButtonElement textModeButton;
    private ButtonElement weightModeButton;

    private boolean weightMode = false;

    public HuffmanAlgorithmControlPanel(HuffmanAlgorithmVisualizer visualizer) {
        this.controller = new EncodingController(visualizer);
    // Configuración visual general del panel lateral
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
        // Título principal del panel
        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();
        // Subtítulo para indicar que esta sección pertenece a Huffman
        LabelElement subtitle = new LabelElement("ALGORITMO HUFFMAN");
        subtitle.setSubtitle();
        //Título de Modos nuevos
        LabelElement modeTitle = new LabelElement("MODO");
        modeTitle.setMuted();
        //botonoes nuevos para seleccionar
        textModeButton = new ButtonElement("Texto");
        weightModeButton = new ButtonElement("Peso");

        HBox modeBox = new HBox(8);
        textModeButton.getNode().setMaxWidth(Double.MAX_VALUE);
        weightModeButton.getNode().setMaxWidth(Double.MAX_VALUE);
        modeBox.getChildren().addAll(textModeButton.getNode(), weightModeButton.getNode());

        //Campo de entrada
        LabelElement inputTitle = new LabelElement("ENTRADA");
        inputTitle.setMuted();
        // Área de texto donde se escribe el mensaje que se va a codificar
        textInput = new TextAreaElement("Ingrese texto para codificar");
        textInput.getNode().setPrefRowCount(6);
        textInput.getNode().setMaxHeight(140);
        // Botones principales del panel
        ButtonElement generateButton = new ButtonElement("Generar Huffman");
        ButtonElement clearButton = new ButtonElement("Limpiar");
        // Se Dejo el botón principal como activo visualmente
        generateButton.setActive(true);
        clearButton.setActive(false);
        // Hago que los botones ocupen todo el ancho disponible
        generateButton.getNode().setMaxWidth(Double.MAX_VALUE);
        clearButton.getNode().setMaxWidth(Double.MAX_VALUE);

        //para seleccionar los modos core ->
        selectTextMode();

        textModeButton.getNode().setOnAction(e -> selectTextMode());
        weightModeButton.getNode().setOnAction(e -> selectWeightMode());
        // Al presionar generar, mando el texto al controlador para aplicar Huffman ya sea txt o weight
        generateButton.getNode().setOnAction(e -> {
            if (weightMode) {
                controller.generateWeight(textInput.getValue());
            } else {
                controller.generateText(textInput.getValue());
            }
        });
        // Limpia el campo de texto y también reinicia el visualizador
        clearButton.getNode().setOnAction(e -> {
            textInput.getNode().clear();
            controller.clear();
        });
        // Agrego todos los elementos al panel en orden
        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                modeTitle.getNode(),
                modeBox,
                inputTitle.getNode(),
                textInput.getNode(),
                generateButton.getNode(),
                clearButton.getNode()
        );
    }
    //Ambos al seleccionar que en el cuadro aparezcan ejmplos guia
    private void selectTextMode() {
        weightMode = false;
        textModeButton.setActive(true);
        weightModeButton.setActive(false);
        textInput.getNode().setPromptText("Ejemplo: banana");
    }

    private void selectWeightMode() {
        weightMode = true;
        textModeButton.setActive(false);
        weightModeButton.setActive(true);
        textInput.getNode().setPromptText("Ejemplo:\nA:5\nB:9\nC:12\nD:13");
    }
}