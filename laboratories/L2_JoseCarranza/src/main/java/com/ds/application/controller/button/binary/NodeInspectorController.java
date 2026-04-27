package com.ds.application.controller.button.binary;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import javafx.scene.control.TextInputDialog;

import java.util.ArrayList;
import java.util.List;

public class NodeInspectorController {

    private BinarySearchTree tree;
    private BinaryTreeOperations operations;

    private final BinaryTreeVisualizer visualizer;
    private final NodeInspector inspector;

    public NodeInspectorController(BinaryTreeVisualizer visualizer, NodeInspector inspector) {
        this.visualizer = visualizer;
        this.inspector = inspector;
        this.tree = new BinarySearchTree();
        this.operations = new BinaryTreeOperations(tree.getRoot());

        this.visualizer.setOnNodeSelected(value -> {
            visualizer.highlightValue(value);
            updateInspector(value);
            inspector.updateStatus("Nodo seleccionado: " + value);
        });
    }

    public void insert() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insertar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Valor:");

        dialog.showAndWait().ifPresent(text -> {
            try {
                int value = Integer.parseInt(text.trim());

                visualizer.clearHighlight();
                tree.insert(value);
                operations.setRoot(tree.getRoot());

                visualizer.drawTree(tree.getRoot());
                updateInspector(value);
                inspector.updateStatus("Insertado: " + value);

            } catch (NumberFormatException e) {
                inspector.updateStatus("Valor invalido.");
            }
        });
    }

    public void search() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Buscar nodo");
        dialog.setHeaderText(null);
        dialog.setContentText("Valor:");

        dialog.showAndWait().ifPresent(text -> {
            try {
                int value = Integer.parseInt(text.trim());

                operations.setRoot(tree.getRoot());

                if (operations.contains(value)) {
                    visualizer.highlightValue(value);
                    updateInspector(value);
                    inspector.updateStatus("Encontrado: " + value);
                } else {
                    inspector.updateStatus("No encontrado: " + value);
                }

            } catch (NumberFormatException e) {
                inspector.updateStatus("Valor invalido.");
            }
        });
    }

    public void deleteSelected(int value) {
        operations.setRoot(tree.getRoot());

        if (!operations.contains(value)) {
            inspector.updateStatus("No encontrado: " + value);
            return;
        }

        tree.delete(value);
        operations.setRoot(tree.getRoot());

        visualizer.clearHighlight();
        visualizer.drawTree(tree.getRoot());
        inspector.clearInfo();
        inspector.updateStatus("Eliminado: " + value);
    }

    public void clear() {
        tree = new BinarySearchTree();
        operations.setRoot(tree.getRoot());

        visualizer.clear();
        inspector.clearInfo();
    }

    public BinaryTreeOperations getOperations() {
        operations.setRoot(tree.getRoot());
        return operations;
    }

    public String getParentNodesText() {
        List<Integer> parents = new ArrayList<>();
        collectParentNodes(tree.getRoot(), parents);
        return formatList(parents);
    }

    public String getLeafNodesText() {
        List<Integer> leaves = new ArrayList<>();
        collectLeafNodes(tree.getRoot(), leaves);
        return formatList(leaves);
    }

    public String getDepthText() {
        operations.setRoot(tree.getRoot());
        return String.valueOf(operations.height());
    }

    private void updateInspector(int value) {
        operations.setRoot(tree.getRoot());

        inspector.updateNodeInfo(
                value,
                getLevel(tree.getRoot(), value, 0),
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

    private void collectParentNodes(BinaryTreeNode<Integer> node, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null || node.getRight() != null) {
            result.add(node.getValue());
        }

        collectParentNodes(node.getLeft(), result);
        collectParentNodes(node.getRight(), result);
    }

    private void collectLeafNodes(BinaryTreeNode<Integer> node, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            result.add(node.getValue());
            return;
        }

        collectLeafNodes(node.getLeft(), result);
        collectLeafNodes(node.getRight(), result);
    }

    private String formatList(List<Integer> values) {
        if (values.isEmpty()) {
            return "-";
        }

        StringBuilder builder = new StringBuilder();

        for (int value : values) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(value);
        }

        return builder.toString();
    }
}