package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.huffman.EncodingController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.notifications.ToastNotification;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HuffmanAlgorithmControlPanel extends VBox {

    private final TextArea textInput;
    private final EncodingController controller;
    private final ToastNotification toast;

    private ButtonElement textModeButton;
    private ButtonElement weightModeButton;

    private boolean weightMode = false;

    public HuffmanAlgorithmControlPanel(
            HuffmanAlgorithmVisualizer visualizer,
            ToastNotification toast
    ) {
        this.controller = new EncodingController(visualizer);
        this.toast = toast;

        // Configuración visual general del panel lateral
        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #BFCFBB;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(52,76,61,0.12), 18, 0.25, 0, 6);"
        );

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ALGORITMO DE HUFFMAN");
        subtitle.setSubtitle();

        LabelElement modeTitle = new LabelElement("MODO");
        modeTitle.setMuted();

        textModeButton = new ButtonElement("T", "Texto");
        weightModeButton = new ButtonElement("P", "Peso");

        HBox modeBox = new HBox(8);
        textModeButton.getNode().setPrefWidth(96);
        weightModeButton.getNode().setPrefWidth(96);
        modeBox.getChildren().addAll(
                textModeButton.getNode(),
                weightModeButton.getNode()
        );

        LabelElement inputTitle = new LabelElement("ENTRADA");
        inputTitle.setMuted();

        textInput = new TextArea();
        textInput.setPromptText("Ejemplo: banana");
        textInput.setPrefRowCount(2);
        textInput.setPrefHeight(54);
        textInput.setMinHeight(54);
        textInput.setMaxHeight(76);
        textInput.setWrapText(true);
        textInput.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-control-inner-background: #DCE8DA;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-width: 1;" +
                "-fx-text-fill: #344C3D;" +
                "-fx-prompt-text-fill: #738A6E;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 10;"
        );

        ButtonElement generateButton = new ButtonElement("▶", "Generar Huffman");
        ButtonElement clearButton = new ButtonElement("↻", "Limpiar");

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
            String input = textInput.getText().trim();

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
                toast.showError("Error en el formato de entrada.");
            }
        });

        clearButton.getNode().setOnAction(e -> {
            if (textInput.getText().trim().isEmpty()) {
                toast.showWarning("La vista ya está limpia.");
                return;
            }

            textInput.clear();
            controller.clear();
            toast.showInfo("Vista limpiada.");
        });

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                modeTitle.getNode(),
                modeBox,
                inputTitle.getNode(),
                textInput,
                generateButton.getNode(),
                clearButton.getNode()
        );
    }

    private void selectTextMode() {
        weightMode = false;

        textModeButton.setActive(true);
        weightModeButton.setActive(false);

        textInput.setPromptText("Ejemplo: banana");
        textInput.setPrefRowCount(2);
        textInput.setPrefHeight(54);
        textInput.setMinHeight(54);
        textInput.setMaxHeight(76);
    }

    private void selectWeightMode() {
        weightMode = true;

        textModeButton.setActive(false);
        weightModeButton.setActive(true);

        textInput.setPromptText("Ejemplo:\nA:5\nB:9\nC:12\nD:13");
        textInput.setPrefRowCount(4);
        textInput.setPrefHeight(88);
        textInput.setMinHeight(88);
        textInput.setMaxHeight(105);
    }
}