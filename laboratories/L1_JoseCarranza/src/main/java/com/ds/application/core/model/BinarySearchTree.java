package com.ds.application.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary search tree implementation containing the core tree behavior.
 */
public class BinarySearchTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int value) {
        root = insertNode(root, value);
    }

    private TreeNode insertNode(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertNode(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertNode(current.getRight(), value));
        }

        return current;
    }

    public TreeNode search(int value) {
        return searchNode(root, value);
    }

    private TreeNode searchNode(TreeNode current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.getValue()) {
            return current;
        }

        return value < current.getValue()
                ? searchNode(current.getLeft(), value)
                : searchNode(current.getRight(), value);
    }

    public int depth() {
        return height(root);
    }

    private int height(TreeNode current) {
        if (current == null) {
            return -1;
        }

        return 1 + Math.max(
                height(current.getLeft()),
                height(current.getRight())
        );
    }

    public int size() {
        return countNodes(root);
    }

    private int countNodes(TreeNode current) {
        if (current == null) {
            return 0;
        }

        return 1 + countNodes(current.getLeft()) + countNodes(current.getRight());
    }

    public int sumValues() {
        return sumValues(root);
    }

    private int sumValues(TreeNode current) {
        if (current == null) {
            return 0;
        }

        return current.getValue()
                + sumValues(current.getLeft())
                + sumValues(current.getRight());
    }

    public double averageValue() {
        if (root == null) {
            return 0;
        }

        return (double) sumValues() / size();
    }

    public List<Integer> parentValues() {
        List<Integer> values = new ArrayList<>();
        collectParents(root, values);
        return values;
    }

    private void collectParents(TreeNode current, List<Integer> values) {
        if (current == null) {
            return;
        }

        if (current.getLeft() != null || current.getRight() != null) {
            values.add(current.getValue());
        }

        collectParents(current.getLeft(), values);
        collectParents(current.getRight(), values);
    }

    public List<Integer> leafValues() {
        List<Integer> values = new ArrayList<>();
        collectLeaves(root, values);
        return values;
    }

    private void collectLeaves(TreeNode current, List<Integer> values) {
        if (current == null) {
            return;
        }

        if (current.isLeaf()) {
            values.add(current.getValue());
        }

        collectLeaves(current.getLeft(), values);
        collectLeaves(current.getRight(), values);
    }

    public int level(TreeNode node) {
        return findLevel(root, node, 0);
    }

    private int findLevel(TreeNode current, TreeNode target, int depth) {
        if (current == null) {
            return -1;
        }

        if (current == target) {
            return depth;
        }

        int found = findLevel(current.getLeft(), target, depth + 1);
        return found != -1
                ? found
                : findLevel(current.getRight(), target, depth + 1);
    }

    public void reset() {
        root = null;
    }
}
