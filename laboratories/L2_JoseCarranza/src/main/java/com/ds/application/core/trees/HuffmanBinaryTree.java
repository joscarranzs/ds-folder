package com.ds.application.core.trees;

import com.ds.application.core.structures.HuffmanNode;

/**
 * Representación ligera de un árbol binario de Huffman.
 *
 * Contiene únicamente la referencia a la raíz y utilidades básicas.
 */
public class HuffmanBinaryTree {
    private HuffmanNode root;

    /** Crea un árbol vacío. */
    public HuffmanBinaryTree() {
        this.root = null;
    }

    /** Crea un árbol con la raíz indicada. */
    public HuffmanBinaryTree(HuffmanNode root) {
        this.root = root;
    }

    /** Devuelve la raíz del árbol (puede ser null). */
    public HuffmanNode getRoot() {
        return root;
    }

    /** Establece la raíz del árbol. */
    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    /** Indica si el árbol está vacío. */
    public boolean isEmpty() {
        return root == null;
    }
}
