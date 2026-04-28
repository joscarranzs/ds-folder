package com.ds.application.core.trees;


public class BinarySearchTree {
    private BinaryTreeNode<Integer> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinaryTreeNode<Integer> getRoot() {
        return root;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private BinaryTreeNode<Integer> insertRec(BinaryTreeNode<Integer> node, int value) {
        if (node == null) {
            return new BinaryTreeNode<Integer>(value);
        }
        if (value < node.getValue()) {
            node.setLeft(insertRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertRec(node.getRight(), value));
        }
        return node;
    }

    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private BinaryTreeNode<Integer> deleteRec(BinaryTreeNode<Integer> node, int value) {
        if (node == null) {
            return node;
        }
        if (value < node.getValue()) {
            node.setLeft(deleteRec(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(deleteRec(node.getRight(), value));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setValue(minValue(node.getRight()));
            node.setRight(deleteRec(node.getRight(), node.getValue()));
        }
        return node;
    }

    private int minValue(BinaryTreeNode<Integer> node) {
        int minv = node.getValue();
        while (node.getLeft() != null) {
            minv = node.getLeft().getValue();
            node = node.getLeft();
        }
        return minv;
    }
}
