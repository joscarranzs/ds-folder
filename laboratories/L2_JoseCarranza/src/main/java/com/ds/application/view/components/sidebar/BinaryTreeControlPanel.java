package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.binary.BinaryButtonActions;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    private BinaryButtonActions actions;

    public BinaryTreeControlPanel(BinaryTreeVisualizer visualizer) {
    actions = new BinaryButtonActions(visualizer);

        setPadding(new Insets(15));
        setSpacing(12);
        setPrefWidth(240);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #e5e7eb;");

        Label title = new Label("Arbol Binario");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button insertButton = new Button("Insertar Nodo");
        Button searchButton = new Button("Buscar Nodo");
        Button inorderButton = new Button("InOrden");
        Button preorderButton = new Button("PreOrden");
        Button postorderButton = new Button("PostOrden");
        Button clearButton = new Button("Limpiar");

        Button[] buttons = {
            insertButton, searchButton,
            inorderButton, preorderButton,
            postorderButton, clearButton
        };

        for (Button button : buttons) {
            button.setMaxWidth(Double.MAX_VALUE);
            button.setStyle(
                "-fx-background-color: #f3f4f6;" +
                "-fx-border-color: #d1d5db;" +
                "-fx-cursor: hand;"
            );
        }

        insertButton.setOnAction(event -> actions.insertNode());
        searchButton.setOnAction(event -> actions.searchNode());
        inorderButton.setOnAction(event -> actions.showTraversal("InOrden"));
        preorderButton.setOnAction(event -> actions.showTraversal("PreOrden"));
        postorderButton.setOnAction(event -> actions.showTraversal("PostOrden"));
        clearButton.setOnAction(event -> actions.clearTree());

        getChildren().addAll(
            title,
            insertButton,
            searchButton,
            inorderButton,
            preorderButton,
            postorderButton,
            clearButton
        );
    }
}