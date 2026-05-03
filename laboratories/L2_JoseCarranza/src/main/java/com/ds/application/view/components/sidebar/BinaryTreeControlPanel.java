package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.binary.DeletionController;
import com.ds.application.controller.button.binary.NodeInspectorController;
import com.ds.application.controller.button.binary.TraversalController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    // Botones principales del panel
    private ButtonElement insertBtn;
    private ButtonElement searchBtn;
    private ButtonElement clearBtn;
    private ButtonElement preorderBtn;
    private ButtonElement inorderBtn;
    private ButtonElement postorderBtn;

    public BinaryTreeControlPanel(BinaryTreeVisualizer visualizer, NodeInspector inspector, TreeInfoBar infoBar) {

        // Creo los controladores que se encargan de manejar la lógica del árbol
        NodeInspectorController nodeController = new NodeInspectorController(visualizer, inspector);
        TraversalController traversalController = new TraversalController(nodeController, inspector, infoBar);
        DeletionController deletionController = new DeletionController(nodeController);

        // Conecto el botón de eliminar del inspector con el controlador de eliminación
        inspector.setOnDelete(value -> {
            deletionController.delete(value);
            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();
        });

        // Configuración visual general del panel lateral
        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 18, 0.25, 0, 6);"
        );

        // Títulos del panel
        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.setSubtitle();

        LabelElement operations = new LabelElement("OPERACIONES");
        operations.setMuted();

        // Botones de operaciones básicas
        insertBtn = new ButtonElement("+  Insertar");
        searchBtn = new ButtonElement("⌕  Buscar");
        clearBtn = new ButtonElement("↻  Limpiar");

        LabelElement traversals = new LabelElement("RECORRIDOS");
        traversals.setMuted();

        // Botones para los recorridos del árbol
        preorderBtn = new ButtonElement("▦  Pre-orden");
        inorderBtn = new ButtonElement("☰  In-orden");
        postorderBtn = new ButtonElement("☷  Pos-orden");

        // Dejo insertar activo al inicio y los demás normales
        styleActiveButton(insertBtn);
        styleNormalButton(searchBtn);
        styleNormalButton(clearBtn);
        styleNormalButton(preorderBtn);
        styleNormalButton(inorderBtn);
        styleNormalButton(postorderBtn);

        // Evento para insertar un nodo
        insertBtn.getNode().setOnAction(e -> {
            selectButton(insertBtn);

            javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog();
            dialog.setTitle("Insertar nodo");
            dialog.setHeaderText(null);
            dialog.setContentText("Valor:");

            dialog.showAndWait().ifPresent(text -> {
                try {
                    int value = Integer.parseInt(text.trim());
                    nodeController.insert(value);
                    updateTreeData(nodeController, infoBar);
                    infoBar.clearTraversal();
                } catch (NumberFormatException ex) {
                    inspector.updateStatus("Valor invalido.");
                }
            });
        });

        // Evento para buscar un nodo
        searchBtn.getNode().setOnAction(e -> {
            selectButton(searchBtn);

            javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog();
            dialog.setTitle("Buscar nodo");
            dialog.setHeaderText(null);
            dialog.setContentText("Valor:");

            dialog.showAndWait().ifPresent(text -> {
                try {
                    int value = Integer.parseInt(text.trim());
                    nodeController.search(value);
                    updateTreeData(nodeController, infoBar);
                    infoBar.clearTraversal();
                } catch (NumberFormatException ex) {
                    inspector.updateStatus("Valor invalido.");
                }
            });
        });

        // Limpia todo el árbol y reinicia la información visual
        clearBtn.getNode().setOnAction(e -> {
            selectButton(clearBtn);
            nodeController.clear();
            infoBar.clear();
        });

        // Eventos para mostrar los recorridos
        preorderBtn.getNode().setOnAction(e -> {
            selectButton(preorderBtn);
            traversalController.preorder();
        });

        inorderBtn.getNode().setOnAction(e -> {
            selectButton(inorderBtn);
            traversalController.inorder();
        });

        postorderBtn.getNode().setOnAction(e -> {
            selectButton(postorderBtn);
            traversalController.postorder();
        });

        // Spacer para mantener la leyenda abajo sin traer de vuelta los datos del árbol
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Pill inferior para identificar los tipos de nodo en pantalla
        VBox legendBox = createLegendBox();

        // Inicializo los datos de la barra inferior
        updateTreeData(nodeController, infoBar);

        // Agrego todos los elementos al panel en orden
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
                postorderBtn.getNode(),
                spacer,
                legendBox
        );
    }

    // Crea la pill de colores para entender los tipos de nodos
    private VBox createLegendBox() {
        VBox box = new VBox(8);
        box.setPadding(new Insets(6, 0, 0, 0));

        Label title = new Label("TIPOS DE NODO");
        title.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        HBox legend = new HBox(10);
        legend.setAlignment(Pos.CENTER_LEFT);
        legend.setPadding(new Insets(8, 10, 8, 10));
        legend.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #e2e8f0;" +
                "-fx-border-radius: 18;"
        );

        legend.getChildren().addAll(
                createLegendItem("●", "RAIZ", "#0f172a"),
                createLegendItem("●", "INTERNO", "#2563eb"),
                createLegendItem("○", "HOJA", "#2563eb")
        );

        box.getChildren().addAll(title, legend);
        return box;
    }

    // Crea cada item de la leyenda
    private HBox createLegendItem(String icon, String text, String color) {
        HBox item = new HBox(5);
        item.setAlignment(Pos.CENTER);

        Label dot = new Label(icon);
        dot.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 12px;");

        Label label = new Label(text);
        label.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #334155;");

        item.getChildren().addAll(dot, label);
        return item;
    }

    // Actualiza los datos generales del árbol en la barra inferior
    private void updateTreeData(NodeInspectorController nodeController, TreeInfoBar infoBar) {
        infoBar.updateTreeData(
                nodeController.getParentNodesText(),
                nodeController.getLeafNodesText(),
                nodeController.height(),
                nodeController.degree()
        );
    }

    // Cambia visualmente cuál botón está activo
    private void selectButton(ButtonElement selected) {
        styleNormalButton(insertBtn);
        styleNormalButton(searchBtn);
        styleNormalButton(clearBtn);
        styleNormalButton(preorderBtn);
        styleNormalButton(inorderBtn);
        styleNormalButton(postorderBtn);

        styleActiveButton(selected);
    }

    // Estilo para el botón seleccionado
    private void styleActiveButton(ButtonElement button) {
        button.getNode().setMaxWidth(Double.MAX_VALUE);
        button.getNode().setStyle(
                "-fx-background-color: #e8f0ff;" +
                "-fx-text-fill: #2563eb;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: transparent transparent transparent #2563eb;" +
                "-fx-border-width: 0 0 0 3;" +
                "-fx-padding: 10 12;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );
    }

    // Estilo para los botones que no están seleccionados
    private void styleNormalButton(ButtonElement button) {
        button.getNode().setMaxWidth(Double.MAX_VALUE);
        button.getNode().setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #374151;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 10 12;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );
    }
}