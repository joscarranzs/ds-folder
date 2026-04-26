package com.ds.application.core.structures;

/**
 * Nodo específico para la construcción del árbol de Huffman.
 * Contiene el carácter asociado (o '\0' para nodos internos), su frecuencia
 * y referencias a hijos izquierdo/derecho.
 */
public class HuffmanNode {
    private char character;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    /**
     * Crea un nodo de Huffman con carácter y frecuencia.
     *
     * @param character carácter representado (use '\0' para nodos internos)
     * @param frequency frecuencia o peso asociado
     */
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    /** Devuelve el carácter asociado a este nodo. */
    public char getCharacter() {
        return character;
    }

    /** Establece el carácter del nodo. */
    public void setCharacter(char character) {
        this.character = character;
    }

    /** Devuelve la frecuencia o peso del nodo. */
    public int getFrequency() {
        return frequency;
    }

    /** Establece la frecuencia o peso del nodo. */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    /** Devuelve el hijo izquierdo. */
    public HuffmanNode getLeft() {
        return left;
    }

    /** Establece el hijo izquierdo. */
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    /** Devuelve el hijo derecho. */
    public HuffmanNode getRight() {
        return right;
    }

    /** Establece el hijo derecho. */
    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
