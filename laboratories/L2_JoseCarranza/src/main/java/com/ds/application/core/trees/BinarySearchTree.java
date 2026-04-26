package com.ds.application.core.trees;

import com.ds.application.core.structures.BinaryTreeNode;

/**
 * Implementación simple de un árbol binario de búsqueda (BST) que almacena
 * enteros. Proporciona inserción y borrado de valores, y acceso a la raíz.
 */
public class BinarySearchTree {
    private BinaryTreeNode<Integer> root;

    /** Crea un árbol vacío. */
    public BinarySearchTree() {
        this.root = null;
    }

    /** Devuelve la raíz del árbol (puede ser null). */
    public BinaryTreeNode<Integer> getRoot() {
        return root;
    }

    /**
     * Inserta un valor en el árbol. Si el valor ya existe, no realiza
     * inserción duplicada.
     *
     * @param value valor a insertar
     */
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // Inserción recursiva.
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

    /**
     * Elimina un valor del árbol si existe.
     *
     * @param value valor a eliminar
     */
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    // Borrado recursivo de un nodo con el valor especificado.
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

    // Devuelve el valor mínimo en el subárbol dado.
    private int minValue(BinaryTreeNode<Integer> node) {
        int minv = node.getValue();
        while (node.getLeft() != null) {
            minv = node.getLeft().getValue();
            node = node.getLeft();
        }
        return minv;
    }
}
