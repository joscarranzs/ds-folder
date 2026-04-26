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

        setPadding(new Insets(16));
        setSpacing(12);
        setPrefWidth(240);
        setStyle("-fx-background-color: #ffffff; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("Panel de Control");
        LabelElement subtitle = new LabelElement("Arbol Binario");
        LabelElement operations = new LabelElement("Operaciones");
        LabelElement traversals = new LabelElement("Recorridos");

        ButtonElement insertBtn = new ButtonElement("Insertar");
        ButtonElement searchBtn = new ButtonElement("Buscar");
        ButtonElement clearBtn = new ButtonElement("Limpiar");

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