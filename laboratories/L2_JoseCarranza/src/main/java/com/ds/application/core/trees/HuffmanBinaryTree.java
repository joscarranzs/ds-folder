package com.ds.application.core.trees;

import com.ds.application.core.structures.HuffmanNode;

/**
 * Representación simple de un árbol de Huffman. Esta clase actúa como contenedor
 * del nodo raíz construido por el algoritmo de Huffman. No implementa operaciones
 * de eliminación o búsqueda: su responsabilidad principal es mantener y exponer
 * la raíz del árbol.
 */
public class HuffmanBinaryTree {

    private HuffmanNode root;

    /**
     * Construye un árbol vacío.
     */
    public HuffmanBinaryTree() {
        this.root = null;
    }

    /**
     * Construye un árbol con la raíz dada.
     *
     * @param root el nodo raíz del árbol de Huffman
     */
    public HuffmanBinaryTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Obtiene la raíz del árbol.
     *
     * @return el nodo raíz, o {@code null} si el árbol está vacío
     */
    public HuffmanNode getRoot() {
        return root;
    }

    /**
     * Establece la raíz del árbol.
     *
     * @param root el nuevo nodo raíz
     */
    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    /**
     * Indica si el árbol está vacío.
     *
     * @return {@code true} cuando no existe raíz
     */
    public boolean isEmpty() {
        return root == null;
    }
}
