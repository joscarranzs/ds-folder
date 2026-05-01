package com.ds.application.controller.button.binary;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.binarysearchtree.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;

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

        // Keep visualizer selection wiring. The controller remains the orchestrator between core and UI.
        this.visualizer.setOnNodeSelected(value -> {
            visualizer.highlightValue(value);
            updateInspector(value);
            inspector.updateStatus("Nodo seleccionado: " + value);
        });
    }

    // Insert a value into the tree. The view is responsible for gathering input.
    public void insert(int value) {
        visualizer.clearHighlight();
        tree.insert(value);
        operations.setRoot(tree.getRoot());

        visualizer.drawTree(tree.getRoot());
        updateInspector(value);
        inspector.updateStatus("Insertado: " + value);
    }

    // Search by value and update the UI accordingly
    public void search(int value) {
        operations.setRoot(tree.getRoot());

        if (operations.contains(value)) {
            visualizer.highlightValue(value);
            updateInspector(value);
            inspector.updateStatus("Encontrado: " + value);
        } else {
            inspector.updateStatus("No encontrado: " + value);
        }
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

    // Provide domain-level traversal and info APIs instead of exposing operations directly.
    public String preOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.preOrderString();
    }

    public String inOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.inOrderString();
    }

    public String postOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.postOrderString();
    }

    public int size() {
        operations.setRoot(tree.getRoot());
        return operations.size();
    }

    public int height() {
        operations.setRoot(tree.getRoot());
        return operations.height();
    }

    public int getLevel(int value) {
        operations.setRoot(tree.getRoot());
        return operations.getLevel(value);
    }

    public String getParentNodesText() {
        operations.setRoot(tree.getRoot());
        return operations.formatListAsText(operations.collectParentNodes());
    }

    public String getLeafNodesText() {
        operations.setRoot(tree.getRoot());
        return operations.formatListAsText(operations.collectLeafNodes());
    }

    private void updateInspector(int value) {
        operations.setRoot(tree.getRoot());

        inspector.updateNodeInfo(
                value,
                getLevel(value),
                operations.height(),
                operations.size()
        );
    }
}
