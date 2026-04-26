package com.ds.application.view.components.sidebar;

import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    private BinaryTreeVisualizer visualizer;

    public BinaryTreeControlPanel(BinaryTreeVisualizer visualizer) {
        this.visualizer = visualizer;

        setPadding(new Insets(18));
        setSpacing(14);
        setPrefWidth(250);
        setStyle("-fx-background-color: #f8fafc; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.getNode().setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #2563eb;");

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.getNode().setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        LabelElement operations = new LabelElement("OPERACIONES");
        operations.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        ButtonElement insertBtn = new ButtonElement("Insertar");
        ButtonElement searchBtn = new ButtonElement("Buscar");
        ButtonElement clearBtn = new ButtonElement("Limpiar");

        LabelElement traversals = new LabelElement("RECORRIDOS");
        traversals.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        ButtonElement preorderBtn = new ButtonElement("Pre-orden");
        ButtonElement inorderBtn = new ButtonElement("In-orden");
        ButtonElement postorderBtn = new ButtonElement("Pos-orden");

        insertBtn.getNode().setOnAction(e -> pending("Insertar nodo"));
        searchBtn.getNode().setOnAction(e -> pending("Buscar nodo"));
        clearBtn.getNode().setOnAction(e -> visualizer.clear());

        preorderBtn.getNode().setOnAction(e -> pending("Pre-orden"));
        inorderBtn.getNode().setOnAction(e -> pending("In-orden"));
        postorderBtn.getNode().setOnAction(e -> pending("Pos-orden"));

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                operations.getNode(),
                insertBtn.getNode(),
                searchBtn.getNode(),
                clearBtn.getNode(),
                traversals.getNode(),
                preorderBtn.getNode(),
                inorderBtn.getNode(),
                postorderBtn.getNode()
        );
    }

    private void pending(String action) {
        visualizer.showWaitingMessage();
        System.out.println(action + " conectado a core despues");
    }
}