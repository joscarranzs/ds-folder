package com.ds.application.view.components.sidebar;

import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.BinaryTreeOperations;
import com.ds.application.view.components.elements.ui.ButtonElement;
import com.ds.application.view.components.elements.ui.LabelElement;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class BinaryTreeControlPanel extends VBox {

    private final BinaryTreeVisualizer visualizer;

    private BinarySearchTree tree;
    private BinaryTreeOperations operations;

    private final ButtonElement insertBtn;
    private final ButtonElement searchBtn;
    private final ButtonElement clearBtn;

    private final ButtonElement preorderBtn;
    private final ButtonElement inorderBtn;
    private final ButtonElement postorderBtn;

    public BinaryTreeControlPanel(BinaryTreeVisualizer visualizer) {
        this.visualizer = visualizer;
        this.tree = new BinarySearchTree();
        this.operations = new BinaryTreeOperations(tree.getRoot());

        setSpacing(10);
        setStyle("-fx-padding: 15; -fx-background-color: #f8fafc; -fx-border-color: #dbe1ea;");

        LabelElement title = new LabelElement("PANEL DE CONTROL");
        title.getNode().setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #2563eb;");

        LabelElement subtitle = new LabelElement("ARBOL BINARIO");
        subtitle.getNode().setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        LabelElement operationsTitle = new LabelElement("OPERACIONES");
        operationsTitle.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        LabelElement traversals = new LabelElement("RECORRIDOS");
        traversals.getNode().setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");

        insertBtn = new ButtonElement("Insertar");
        searchBtn = new ButtonElement("Buscar");
        clearBtn = new ButtonElement("Limpiar");

        preorderBtn = new ButtonElement("Pre-orden");
        inorderBtn = new ButtonElement("In-orden");
        postorderBtn = new ButtonElement("Pos-orden");

        insertBtn.setActive(true);

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

        clearBtn.getNode().setOnAction(e -> {
            resetButtons();
            clearBtn.setActive(true);
            clearTree();
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
                clearBtn.getNode(),
                traversals.getNode(),
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
                System.out.println("Insertado: " + value);

            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un numero valido.");
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
                    System.out.println("Encontrado: " + value);
                } else {
                    System.out.println("No encontrado: " + value);
                }

            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un numero valido.");
            }
        });
    }

    private void clearTree() {
        tree = new BinarySearchTree();
        operations.setRoot(tree.getRoot());

        visualizer.clear();
        System.out.println("Arbol limpiado.");
    }

    private void showTraversal(String name, String result) {
        operations.setRoot(tree.getRoot());

        if (result == null || result.isEmpty()) {
            System.out.println(name + ": arbol vacio");
        } else {
            System.out.println(name + ": " + result);
        }
    }

    private void resetButtons() {
        insertBtn.setActive(false);
        searchBtn.setActive(false);
        clearBtn.setActive(false);
        preorderBtn.setActive(false);
        inorderBtn.setActive(false);
        postorderBtn.setActive(false);
    }
}