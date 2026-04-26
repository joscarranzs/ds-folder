package com.ds.application.core.trees.operations;

import com.ds.application.core.structures.BinaryTreeNode;

import com.ds.application.core.structures.aux.SimpleList;

// Operaciones específicas del árbol binario de búsqueda.
public class BinaryTreeOperations {
    private BinaryTreeNode<Integer> root;

    public BinaryTreeOperations(BinaryTreeNode<Integer> root) {
        this.root = root;
    }

    public BinaryTreeNode<Integer> getRoot() {
        return root;
    }

    public void setRoot(BinaryTreeNode<Integer> root) {
        this.root = root;
    }

    public BinaryTreeNode<Integer> search(int value) {
        return searchRec(root, value);
    }

    public boolean contains(int value) {
        return search(value) != null;
    }

    public int height() {
        return heightRec(root);
    }

    public int size() {
        return sizeRec(root);
    }

    public SimpleList<Integer> inOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        inOrderRec(root, result);
        return result;
    }

    public SimpleList<Integer> preOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        preOrderRec(root, result);
        return result;
    }

    public SimpleList<Integer> postOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        postOrderRec(root, result);
        return result;
    }

    public String inOrderString() {
        return joinValues(inOrder());
    }

    public String preOrderString() {
        return joinValues(preOrder());
    }

    public String postOrderString() {
        return joinValues(postOrder());
    }

    private BinaryTreeNode<Integer> searchRec(BinaryTreeNode<Integer> node, int value) {
        if (node == null) {
            return null;
        }
        if (value == node.getValue()) {
            return node;
        }
        if (value < node.getValue()) {
            return searchRec(node.getLeft(), value);
        }
        return searchRec(node.getRight(), value);
    }

    private int heightRec(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightRec(node.getLeft());
        int rightHeight = heightRec(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int sizeRec(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRec(node.getLeft()) + sizeRec(node.getRight());
    }

    private void inOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft(), result);
        result.add(node.getValue());
        inOrderRec(node.getRight(), result);
    }

    private void preOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.getValue());
        preOrderRec(node.getLeft(), result);
        preOrderRec(node.getRight(), result);
    }

    private void postOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        postOrderRec(node.getLeft(), result);
        postOrderRec(node.getRight(), result);
        result.add(node.getValue());
    }

    private String joinValues(SimpleList<Integer> values) {
        if (values.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(value);
        }
        return builder.toString();
    }
}
