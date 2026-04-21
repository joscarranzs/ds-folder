package com.ds.application.core.controller;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.model.TreeTraversal;
import com.ds.application.core.view.BinaryTree;

public final class TreeInspectorPresenter {

    private TreeInspectorPresenter() {
    }

    public static void updateInspector(BinaryTree view, BinarySearchTree model, TreeNode node) {
        if (node == null) {
            view.setInspector("—", "—", "—", "—");
            return;
        }

        int degree = (node.getLeft() != null ? 1 : 0) + (node.getRight() != null ? 1 : 0);
        int level = TreeTraversal.level(model.getRoot(), node);
        String levelText = level == -1 ? "—" : String.valueOf(level);
        String childNodes = modelChildrenText(node);

        view.setInspector(
                String.valueOf(node.getValue()),
                String.valueOf(degree),
                levelText,
                childNodes
        );
    }

    private static String modelChildrenText(TreeNode node) {
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
