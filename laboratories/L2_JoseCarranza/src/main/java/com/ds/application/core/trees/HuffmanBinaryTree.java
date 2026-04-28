package com.ds.application.core.trees;

import com.ds.application.core.structures.HuffmanNode;

public class HuffmanBinaryTree {
    private HuffmanNode root;

    public HuffmanBinaryTree() {
        this.root = null;
    }

    public HuffmanBinaryTree(HuffmanNode root) {
        this.root = root;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
