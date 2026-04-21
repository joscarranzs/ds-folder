package com.ds.application.core.model;

import java.util.ArrayList;
import java.util.List;

public final class TreeTraversal {

    private TreeTraversal() {
    }

    public static List<TreeNode> searchWithPath(TreeNode root, int value) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            path.add(current);
            if (value == current.getValue()) {
                return path;
            }
            current = value < current.getValue() ? current.getLeft() : current.getRight();
        }

        return path;
    }

    public static List<TreeNode> nodesAtLevel(TreeNode root, int level) {
        List<TreeNode> nodes = new ArrayList<>();
        collectNodesAtLevel(root, 1, level, nodes);
        return nodes;
    }

    private static void collectNodesAtLevel(TreeNode node, int currentLevel, int targetLevel, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        if (currentLevel == targetLevel) {
            nodes.add(node);
            return;
        }
        collectNodesAtLevel(node.getLeft(), currentLevel + 1, targetLevel, nodes);
        collectNodesAtLevel(node.getRight(), currentLevel + 1, targetLevel, nodes);
    }

    public static int level(TreeNode root, TreeNode target) {
        return findLevel(root, target, 1);
    }

    private static int findLevel(TreeNode current, TreeNode target, int depth) {
        if (current == null) {
            return -1;
        }
        if (current == target) {
            return depth;
        }
        int found = findLevel(current.getLeft(), target, depth + 1);
        return found != -1 ? found : findLevel(current.getRight(), target, depth + 1);
    }
}
