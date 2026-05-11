package com.ds.application.view.components.sidebar;

import com.ds.application.controller.button.binary.DeletionController;
import com.ds.application.controller.button.binary.NodeInspectorController;
import com.ds.application.controller.button.binary.TraversalController;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.notifications.ToastNotification;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryTreeControlPanel extends VBox {

    private final ToastNotification toast;

    private ButtonElement insertBtn;
    private ButtonElement searchBtn;
    private ButtonElement clearBtn;
    private ButtonElement preorderBtn;
    private ButtonElement inorderBtn;
    private ButtonElement postorderBtn;

    private TextField valueInput;

    public BinaryTreeControlPanel(
            BinaryTreeVisualizer visualizer,
            NodeInspector inspector,
            TreeInfoBar infoBar,
            ToastNotification toast
    ) {
        this.toast = toast;

        NodeInspectorController nodeController = new NodeInspectorController(visualizer, inspector);
        TraversalController traversalController = new TraversalController(nodeController, inspector, infoBar);
        DeletionController deletionController = new DeletionController(nodeController);

        inspector.setOnDelete(value -> {
            deletionController.delete(value);
            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();
            toast.showSuccess("Nodo " + value + " eliminado correctamente.");
        });

        setPadding(new Insets(18));
        setSpacing(12);
        setPrefWidth(260);
        setStyle(
                "-fx-background-color: #BFCFBB;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(52,76,61,0.12), 18, 0.25, 0, 6);"
        );

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.setTitle();

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.setSubtitle();

        Label valueLabel = new Label("VALOR");
        valueLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #738A6E;"
        );

        valueInput = new TextField();
        valueInput.setPromptText("Ej: 20 40 60 o (20)(40)(60)");
        valueInput.setPrefHeight(40);
        valueInput.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0 12;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #344C3D;" +
                "-fx-prompt-text-fill: #738A6E;"
        );

        LabelElement operations = new LabelElement("OPERACIONES");
        operations.setMuted();

        insertBtn = new ButtonElement("+", "Insertar");
        searchBtn = new ButtonElement("⌕", "Buscar");
        clearBtn = new ButtonElement("↻", "Eliminar");

        LabelElement traversals = new LabelElement("RECORRIDOS");
        traversals.setMuted();

        preorderBtn = new ButtonElement("▦", "Pre-orden");
        inorderBtn = new ButtonElement("☰", "In-orden");
        postorderBtn = new ButtonElement("☷", "Pos-orden");

        resizeButton(insertBtn);
        resizeButton(searchBtn);
        resizeButton(clearBtn);
        resizeButton(preorderBtn);
        resizeButton(inorderBtn);
        resizeButton(postorderBtn);

        selectButton(insertBtn);

        insertBtn.getNode().setOnAction(e -> {
            selectButton(insertBtn);

            List<Integer> values = readInputValues(inspector);
            if (values.isEmpty()) {
                return;
            }

            for (int value : values) {
                nodeController.insert(value);
            }

            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();
            valueInput.clear();

            if (values.size() == 1) {
                toast.showSuccess("Nodo " + values.get(0) + " insertado correctamente.");
            } else {
                toast.showSuccess(values.size() + " nodos insertados correctamente.");
            }
        });

        searchBtn.getNode().setOnAction(e -> {
            selectButton(searchBtn);

            Integer value = readInputValue(inspector);
            if (value == null) {
                return;
            }

            boolean exists = nodeController.contains(value);

            nodeController.search(value);
            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();

            if (exists) {
                toast.showSuccess("Nodo " + value + " encontrado.");
            } else {
                toast.showWarning("Nodo " + value + " no encontrado.");
            }
        });

        clearBtn.getNode().setOnAction(e -> {
            selectButton(clearBtn);

            Integer value = readInputValue(inspector);
            if (value == null) {
                return;
            }

            deletionController.delete(value);
            updateTreeData(nodeController, infoBar);
            infoBar.clearTraversal();
            valueInput.clear();

            toast.showSuccess("Nodo " + value + " eliminado correctamente.");
        });

        preorderBtn.getNode().setOnAction(e -> {
            selectButton(preorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.preorder();
            toast.showInfo("Recorrido pre-orden ejecutado.");
        });

        inorderBtn.getNode().setOnAction(e -> {
            selectButton(inorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.inorder();
            toast.showInfo("Recorrido in-orden ejecutado.");
        });

        postorderBtn.getNode().setOnAction(e -> {
            selectButton(postorderBtn);

            if (nodeController.size() == 0) {
                toast.showWarning("No hay nodos para recorrer.");
                return;
            }

            traversalController.postorder();
            toast.showInfo("Recorrido pos-orden ejecutado.");
        });

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox legendBox = createLegendBox();

        updateTreeData(nodeController, infoBar);

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                valueLabel,
                valueInput,
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

    private Integer readInputValue(NodeInspector inspector) {
        List<Integer> values = readInputValues(inspector);

        if (values.isEmpty()) {
            return null;
        }

        return values.get(0);
    }

    private List<Integer> readInputValues(NodeInspector inspector) {
        List<Integer> values = new ArrayList<>();
        String text = valueInput.getText().trim();

        if (text.isEmpty()) {
            inspector.updateStatus("Ingrese un valor.");
            toast.showWarning("Ingrese uno o varios valores antes de continuar.");
            return values;
        }

        Matcher matcher = Pattern.compile("-?\\d+").matcher(text);

        while (matcher.find()) {
            try {
                values.add(Integer.parseInt(matcher.group()));
            } catch (NumberFormatException ignored) {
                // Si un número no cabe como entero, se ignora.
            }
        }

        if (values.isEmpty()) {
            inspector.updateStatus("Valor invalido.");
            toast.showError("Ingrese solo números reconocibles.");
        }

        return values;
    }

    private void resizeButton(ButtonElement button) {
        button.getNode().setPrefHeight(39);
        button.getNode().setMaxWidth(Double.MAX_VALUE);
    }

    private VBox createLegendBox() {
        VBox box = new VBox(8);
        box.setPadding(new Insets(6, 0, 0, 0));

        Label title = new Label("TIPOS DE NODO");
        title.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #738A6E;");

        HBox legend = new HBox(10);
        legend.setAlignment(Pos.CENTER_LEFT);
        legend.setPadding(new Insets(8, 10, 8, 10));
        legend.setStyle(
                "-fx-background-color: #DCE8DA;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #8EA58C;" +
                "-fx-border-radius: 18;"
        );

        legend.getChildren().addAll(
                createLegendItem("●", "RAIZ", "#344C3D"),
                createLegendItem("●", "INTERNO", "#738A6E"),
                createLegendItem("○", "HOJA", "#738A6E")
        );

        box.getChildren().addAll(title, legend);
        return box;
    }

    private HBox createLegendItem(String icon, String text, String color) {
        HBox item = new HBox(5);
        item.setAlignment(Pos.CENTER);

        Label dot = new Label(icon);
        dot.setStyle("-fx-text-fill: " + color + "; -fx-font-size: 12px;");

        Label label = new Label(text);
        label.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #344C3D;");

        item.getChildren().addAll(dot, label);
        return item;
    }

    private void updateTreeData(NodeInspectorController nodeController, TreeInfoBar infoBar) {
        String structure = nodeController.parenthesisString();

        infoBar.updateTreeData(
                nodeController.getParentNodesText(),
                nodeController.getLeafNodesText(),
                nodeController.height(),
                nodeController.degree(),
                nodeController.internalPathLength(),
                nodeController.averageInternalPathLength(),
                structure
        );
    }

    private void selectButton(ButtonElement selected) {
        insertBtn.setActive(false);
        searchBtn.setActive(false);
        clearBtn.setActive(false);
        preorderBtn.setActive(false);
        inorderBtn.setActive(false);
        postorderBtn.setActive(false);

        selected.setActive(true);
    }
}