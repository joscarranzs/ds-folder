package com.ds.application.core.controller;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.view.BinaryTree;
import javafx.scene.control.TextInputDialog;

import java.util.List;

/**
 * Controller for the binary tree application.
 * Separates user interactions from tree data logic.
 */
public class TreeController {

    private final BinaryTree view;
    private final BinarySearchTree model;

    public TreeController(BinaryTree view) {
        this.view = view;
        this.model = new BinarySearchTree();

        view.setInspector("—", "—", "—", "—");
        view.setStats(0, "—", "—", "—", "—");
    }

    public void handleInsertNode() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Insert Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter integer value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int value = Integer.parseInt(input.trim());
                model.insert(value);
                view.drawTree(model.getRoot());
                updateInspector(model.search(value));
                updateStats();
            } catch (NumberFormatException ignored) {
            }
        });
    }

    public void handleSearch() {
        if (model.isEmpty()) {
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Node");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter node value:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int value = Integer.parseInt(input.trim());
                TreeNode node = model.search(value);
                if (node != null) {
                    updateInspector(node);
                } else {
                    view.setInspector("Not found", "—", "—", "—");
                }
            } catch (NumberFormatException ignored) {
            }
        });
    }

    public void handleNewTree() {
        model.reset();
        view.resetView();
    }

    public void handleParentNodes() {
        updateStats();
    }

    public void handleLeafNodes() {
        updateStats();
    }

    public void handleLCI() {
        updateStats();
    }

    public void handleLCIM() {
        updateStats();
    }

    public void handleSequential() {
        view.setRepresentationActive(true);
        updateStats();
    }

    public void handleLinkedList() {
        view.setRepresentationActive(false);
        updateStats();
    }

    public void handleNodeClick(TreeNode node) {
        updateInspector(node);
    }

    private void updateStats() {
        if (model.isEmpty()) {
            view.setStats(0, "—", "—", "—", "—");
            return;
        }

        int depth = model.depth();
        int lci = model.sumValues();
        double lcim = model.averageValue();
        List<Integer> parents = model.parentValues();
        List<Integer> leaves = model.leafValues();

        view.setStats(
                depth,
                String.valueOf(lci),
                String.format("%.2f", lcim),
                parents.toString(),
                leaves.toString()
        );
    }

    private void updateInspector(TreeNode node) {
        if (node == null) {
            view.setInspector("—", "—", "—", "—");
            return;
        }

        int degree = (node.getLeft() != null ? 1 : 0) + (node.getRight() != null ? 1 : 0);
        int level = model.level(node);
        String levelText = level == 0 ? "0 (Root)" : String.valueOf(level);

        String childNodes = modelChildrenText(node);

        view.setInspector(
                String.valueOf(node.getValue()),
                String.valueOf(degree),
                levelText,
                childNodes
        );
    }

    private String modelChildrenText(TreeNode node) {
        StringBuilder builder = new StringBuilder();
        if (node.getLeft() != null) {
            builder.append(node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(node.getRight().getValue());
        }
        return builder.length() == 0 ? "none" : builder.toString();
    }
}
