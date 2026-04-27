package com.ds.application.view.components.sidebar;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.BinaryTreeOperations;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.scene.control.TextInputDialog;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    private final BinaryTreeVisualizer visualizer;
    private final NodeInspector inspector;

    private BinarySearchTree tree;
    private BinaryTreeOperations operations;

    private final ButtonElement insertBtn;
    private final ButtonElement searchBtn;
    private final ButtonElement preorderBtn;
    private final ButtonElement inorderBtn;
    private final ButtonElement postorderBtn;

    public BinaryTreeControlPanel(BinaryTreeVisualizer visualizer, NodeInspector inspector) {
        this.visualizer = visualizer;
        this.inspector = inspector;

        tree = new BinarySearchTree();
        operations = new BinaryTreeOperations(tree.getRoot());

        setSpacing(10);
        setStyle("-fx-padding: 15; -fx-background-color: #f8fafc; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.getNode().setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2563eb;");

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.getNode().setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        LabelElement operationsTitle = new LabelElement("OPERACIONES");
        operationsTitle.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        LabelElement traversalsTitle = new LabelElement("RECORRIDOS");
        traversalsTitle.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        insertBtn = new ButtonElement("Insertar");
        searchBtn = new ButtonElement("Buscar");

        preorderBtn = new ButtonElement("Pre-orden");
        inorderBtn = new ButtonElement("In-orden");
        postorderBtn = new ButtonElement("Pos-orden");

        insertBtn.setActive(true);

        inspector.setOnDelete(value -> deleteValue(value));

        insertBtn.getNode().setOnAction(e -> {
            resetButtons();
            insertBtn.setActive(true);
            insertValue();
        });

        searchBtn.getNode().setOnAction(e -> {
            resetButtons();
            searchBtn.setActive(true);
            searchValue();
        });

        preorderBtn.getNode().setOnAction(e -> {
            resetButtons();
            preorderBtn.setActive(true);
            showTraversal("Pre-orden", operations.preOrderString());
        });

        inorderBtn.getNode().setOnAction(e -> {
            resetButtons();
            inorderBtn.setActive(true);
            showTraversal("In-orden", operations.inOrderString());
        });

        postorderBtn.getNode().setOnAction(e -> {
            resetButtons();
            postorderBtn.setActive(true);
            showTraversal("Pos-orden", operations.postOrderString());
        });

        getChildren().addAll(
                title.getNode(),
                subtitle.getNode(),
                operationsTitle.getNode(),
                insertBtn.getNode(),
                searchBtn.getNode(),
                traversalsTitle.getNode(),
                preorderBtn.getNode(),
                inorderBtn.getNode(),
                postorderBtn.getNode()
        );
    }

    private void insertValue() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insertar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese valor:");

        dialog.showAndWait().ifPresent(valueText -> {
            try {
                int value = Integer.parseInt(valueText.trim());

                tree.insert(value);
                operations.setRoot(tree.getRoot());

                visualizer.drawTree(tree.getRoot());
                updateInspector(value);
                inspector.updateStatus("Nodo insertado.");

            } catch (NumberFormatException ex) {
                inspector.updateStatus("Ingrese un numero valido.");
            }
        });
    }

    private void searchValue() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese valor:");

        dialog.showAndWait().ifPresent(valueText -> {
            try {
                int value = Integer.parseInt(valueText.trim());

                operations.setRoot(tree.getRoot());

                if (operations.contains(value)) {
                    visualizer.highlightValue(value);
                    visualizer.drawTree(tree.getRoot());
                    updateInspector(value);
                    inspector.updateStatus("Nodo encontrado.");
                } else {
                    inspector.updateStatus("Nodo no encontrado.");
                }

            } catch (NumberFormatException ex) {
                inspector.updateStatus("Ingrese un numero valido.");
            }
        });
    }

    private void deleteValue(int value) {
        operations.setRoot(tree.getRoot());

        if (!operations.contains(value)) {
            inspector.updateStatus("No se puede eliminar. Nodo no encontrado.");
            return;
        }

        tree.delete(value);
        operations.setRoot(tree.getRoot());

        visualizer.drawTree(tree.getRoot());
        inspector.clearInfo();
        inspector.updateStatus("Nodo eliminado.");
    }

    private void showTraversal(String name, String result) {
        operations.setRoot(tree.getRoot());

        if (result == null || result.isEmpty()) {
            inspector.updateTraversal(name, "arbol vacio");
        } else {
            inspector.updateTraversal(name, result);
        }
    }

    private void updateInspector(int value) {
        operations.setRoot(tree.getRoot());

        int level = getLevel(tree.getRoot(), value, 0);
        inspector.updateNodeInfo(
                value,
                level,
                operations.height(),
                operations.size()
        );
    }

    private int getLevel(BinaryTreeNode<Integer> node, int value, int level) {
        if (node == null) {
            return -1;
        }

        if (node.getValue() == value) {
            return level;
        }

        if (value < node.getValue()) {
            return getLevel(node.getLeft(), value, level + 1);
        }

        return getLevel(node.getRight(), value, level + 1);
    }

    private void resetButtons() {
        insertBtn.setActive(false);
        searchBtn.setActive(false);
        preorderBtn.setActive(false);
        inorderBtn.setActive(false);
        postorderBtn.setActive(false);
    }
}
