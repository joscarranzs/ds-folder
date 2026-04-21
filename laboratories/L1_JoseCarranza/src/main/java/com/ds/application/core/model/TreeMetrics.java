package com.ds.application.core.model;

import java.util.List;

public final class TreeMetrics {

    private TreeMetrics() {
    }

    public static int depth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(depth(root.getLeft()), depth(root.getRight()));
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : 1 + size(root.getLeft()) + size(root.getRight());
    }

    public static int sumValues(TreeNode root) {
        return root == null ? 0 : root.getValue() + sumValues(root.getLeft()) + sumValues(root.getRight());
    }

    public static double averageValue(TreeNode root) {
        int totalNodes = size(root);
        return totalNodes == 0 ? 0 : (double) sumValues(root) / totalNodes;
    }

    public static int internalPathLength(TreeNode root) {
        return internalPathLength(root, 1);
    }

    private static int internalPathLength(TreeNode node, int level) {
        if (node == null) {
            return 0;
        }
        int total = (node.getLeft() != null || node.getRight() != null) ? level : 0;
        total += internalPathLength(node.getLeft(), level + 1);
        total += internalPathLength(node.getRight(), level + 1);
        return total;
    }

    public static int internalNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = (root.getLeft() != null || root.getRight() != null) ? 1 : 0;
        return count + internalNodeCount(root.getLeft()) + internalNodeCount(root.getRight());
    }

    public static double meanInternalPathLength(TreeNode root) {
        int internalCount = internalNodeCount(root);
        return internalCount == 0 ? 0 : (double) internalPathLength(root) / internalCount;
    }

    public static List<Integer> parentValues(TreeNode root) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        collectParents(root, values);
        return values;
    }

    private static void collectParents(TreeNode node, java.util.List<Integer> values) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null || node.getRight() != null) {
            values.add(node.getValue());
        }
        collectParents(node.getLeft(), values);
        collectParents(node.getRight(), values);
    }

    public static List<Integer> leafValues(TreeNode root) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        collectLeaves(root, values);
        return values;
    }

    private static void collectLeaves(TreeNode node, java.util.List<Integer> values) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            values.add(node.getValue());
        }
        collectLeaves(node.getLeft(), values);
        collectLeaves(node.getRight(), values);
    }
}
