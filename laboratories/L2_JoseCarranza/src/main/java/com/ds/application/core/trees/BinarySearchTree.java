package com.ds.application.core.trees;

import com.ds.application.core.structures.BinaryTreeNode;

/**
 * Árbol binario de búsqueda (BST) que almacena únicamente valores enteros.
 * Proporciona operaciones básicas: insertar, eliminar y buscar. Se usan
 * métodos privados recursivos de ayuda para mantener el código claro.
 */
public class BinarySearchTree {

    private BinaryTreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Obtiene la raíz del árbol.
     *
     * @return el nodo raíz, o {@code null} si el árbol está vacío
     */
    public BinaryTreeNode getRoot() {
        return root;
    }

    /**
     * Inserta un valor en el árbol. No inserta valores duplicados.
     *
     * @param value valor a insertar
     */
    public void insert(int value) {
        root = insertRec(root, value);
        if (root != null) root.setParent(null);
    }

    // helper recursivo para insertar
    private BinaryTreeNode insertRec(BinaryTreeNode node, int value) {
        if (node == null) {
            return new BinaryTreeNode(value);
        }

        Integer nodeVal = node.getValue();
        if (value < nodeVal) {
            BinaryTreeNode left = insertRec(node.getLeft(), value);
            node.setLeft(left);
        } else if (value > nodeVal) {
            BinaryTreeNode right = insertRec(node.getRight(), value);
            node.setRight(right);
        }
        // si value == nodeVal no hacemos nada (sin duplicados)
        return node;
    }

    /**
     * Elimina un valor del árbol si existe.
     *
     * @param value valor a eliminar
     */
    public void delete(int value) {
        root = deleteRec(root, value);
        if (root != null) root.setParent(null);
    }

    // helper recursivo para eliminar
    private BinaryTreeNode deleteRec(BinaryTreeNode node, int key) {
        if (node == null) return null;

        Integer nodeVal = node.getValue();
        if (key < nodeVal) {
            BinaryTreeNode newLeft = deleteRec(node.getLeft(), key);
            node.setLeft(newLeft);
        } else if (key > nodeVal) {
            BinaryTreeNode newRight = deleteRec(node.getRight(), key);
            node.setRight(newRight);
        } else {
            // encontrado
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                // dos hijos: reemplazar por sucesor (mínimo del subárbol derecho)
                BinaryTreeNode succ = findMin(node.getRight());
                node.setValue(succ.getValue());
                BinaryTreeNode newRight = deleteRec(node.getRight(), succ.getValue());
                node.setRight(newRight);
            }
        }

        return node;
    }

    // busca el mínimo en el subárbol dado
    private BinaryTreeNode findMin(BinaryTreeNode node) {
        BinaryTreeNode current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    /**
     * Indica si un valor está presente en el árbol.
     *
     * @param value valor a buscar
     * @return {@code true} si existe en el árbol; {@code false} en caso contrario
     */
    public boolean contains(int value) {
        return containsRec(root, value);
    }

    private boolean containsRec(BinaryTreeNode node, int value) {
        if (node == null) return false;
        Integer nodeVal = node.getValue();
        if (value == nodeVal) return true;
        if (value < nodeVal) return containsRec(node.getLeft(), value);
        return containsRec(node.getRight(), value);
    }
}
