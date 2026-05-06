package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.huffman.EncodingController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.elements.ui.TextAreaElement;
import com.ds.application.view.components.notifications.ToastNotification;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HuffmanAlgorithmControlPanel extends VBox {

    // Campo donde el usuario escribe el texto o pesos que quiere procesar
    private final TextAreaElement textInput;

    // Controlador encargado de aplicar el algoritmo Huffman
    private final EncodingController controller;

    // Notificación flotante
    private final ToastNotification toast;

    // Botones para cambiar entre modo texto y modo peso
    private ButtonElement textModeButton;
    private ButtonElement weightModeButton;

    // Indica si el usuario está usando Huffman por peso
    private boolean weightMode = false;

    public HuffmanAlgorithmControlPanel(
            HuffmanAlgorithmVisualizer visualizer,
            ToastNotification toast
    ) {
        this.controller = new EncodingController(visualizer);
        this.toast = toast;

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

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ALGORITMO HUFFMAN");
        subtitle.setSubtitle();

        LabelElement modeTitle = new LabelElement("MODO");
        modeTitle.setMuted();

        textModeButton = new ButtonElement("T", "Texto");
        weightModeButton = new ButtonElement("P", "Peso");

        HBox modeBox = new HBox(8);
        textModeButton.getNode().setMaxWidth(Double.MAX_VALUE);
        weightModeButton.getNode().setMaxWidth(Double.MAX_VALUE);
        modeBox.getChildren().addAll(
                textModeButton.getNode(),
                weightModeButton.getNode()
        );

        LabelElement inputTitle = new LabelElement("ENTRADA");
        inputTitle.setMuted();

        textInput = new TextAreaElement("Ingrese texto para codificar");
        textInput.getNode().setPrefRowCount(6);
        textInput.getNode().setMaxHeight(140);

        ButtonElement generateButton = new ButtonElement("▶", "Generar Huffman");
        ButtonElement clearButton = new ButtonElement("↻", "Limpiar");

        generateButton.setActive(true);
        clearButton.setActive(false);

        generateButton.getNode().setMaxWidth(Double.MAX_VALUE);
        clearButton.getNode().setMaxWidth(Double.MAX_VALUE);

        selectTextMode();

        textModeButton.getNode().setOnAction(e -> {
            selectTextMode();
            toast.showInfo("Modo texto seleccionado.");
        });

        weightModeButton.getNode().setOnAction(e -> {
            selectWeightMode();
            toast.showInfo("Modo peso seleccionado.");
        });

        generateButton.getNode().setOnAction(e -> {
            String input = textInput.getValue().trim();

            if (input.isEmpty()) {
                toast.showWarning("La entrada está vacía.");
                return;
            }

            try {
                boolean ok;
                if (weightMode) {
                    if (!input.contains(":") && !input.contains("=")) {
                        toast.showWarning("Formato incompleto. Use algo como A:5");
                        return;
                    }
                    ok = controller.generateWeight(input);
                    if (ok) {
                        toast.showSuccess("Huffman por peso generado.");
                    } else {
                        toast.showError("Formato inválido. No se generó el árbol.");
                    }
                } else {
                    if (input.length() == 1) {
                        toast.showWarning("El texto tiene un solo carácter. El árbol será muy simple.");
                    }

                    ok = controller.generateText(input);
                    if (ok) {
                        toast.showSuccess("Huffman generado correctamente.");
                    } else {
                        toast.showError("Formato inválido. No se generó el árbol.");
                    }
                }
            } catch (Exception ex) {
                // The controller reports validation messages to the visualizer; this
                // catch ensures unexpected errors still show a user-facing message.
                toast.showError("Error en el formato de entrada.");
            }
        });

        clearButton.getNode().setOnAction(e -> {
            if (textInput.getValue().trim().isEmpty()) {
                toast.showWarning("La vista ya está limpia.");
                return;
            }

            textInput.getNode().clear();
            controller.clear();
            toast.showInfo("Vista limpiada.");
        });

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

    // Modo texto
    private void selectTextMode() {
        weightMode = false;

        textModeButton.setActive(true);
        weightModeButton.setActive(false);

        textInput.getNode().setPromptText("Ejemplo: banana");
    }

    // Modo peso
    private void selectWeightMode() {
        weightMode = true;

        textModeButton.setActive(false);
        weightModeButton.setActive(true);

        textInput.getNode().setPromptText("Ejemplo:\nA:5\nB:9\nC:12\nD:13");
    }
}
