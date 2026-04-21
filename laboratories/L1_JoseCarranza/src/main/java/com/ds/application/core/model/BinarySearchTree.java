package com.ds.application.core.model;

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

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private TreeNode deleteRecursive(TreeNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(deleteRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(deleteRecursive(node.getRight(), value));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            TreeNode minNode = findMin(node.getRight());
            node.setValue(minNode.getValue());
            node.setRight(deleteRecursive(node.getRight(), minNode.getValue()));
        }

        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
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

    public void reset() {
        root = null;
    }
}
