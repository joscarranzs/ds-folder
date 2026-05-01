package com.ds.application.core.structures;

/**
 * Nodo para uso en árboles de Huffman que almacena únicamente números como
 * valores. Cada nodo tiene una frecuencia usada para construir el árbol.
 *
 * Si el campo "value" es null, se considera un nodo interno (no hoja).
 */
public class HuffmanNode implements Comparable<HuffmanNode> {
    private Number value; // null para nodos internos
    private long frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(Number value, long frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    /**
     * Construye un nodo de Huffman vacío (sin valor ni frecuencia).
     */
    public HuffmanNode() {
        this.value = null;
        this.frequency = 0L;
    }

    /**
     * Obtiene el valor numérico almacenado en este nodo.
     * <p>
     * Si el valor es {@code null} se considera que este nodo es un nodo interno
     * en el árbol de Huffman (no una hoja).
     *
     * @return el valor asociado al nodo, o {@code null} si es un nodo interno
     */
    public Number getValue() {
        return value;
    }

    /**
     * Establece el valor numérico de este nodo.
     *
     * @param value el valor a almacenar (puede ser {@code null} para un nodo interno)
     */
    public void setValue(Number value) {
        this.value = value;
    }

    /**
     * Obtiene la frecuencia asociada a este nodo. La frecuencia se utiliza para
     * construir y comparar nodos dentro del algoritmo de Huffman.
     *
     * @return la frecuencia del nodo
     */
    public long getFrequency() {
        return frequency;
    }

    /**
     * Obtiene el identificador entero del símbolo almacenado en este nodo.
     *
     * @return el valor entero del símbolo si existe, o -1 para nodos internos
     */
    public int getCharacter() {
        if (value == null) return -1;
        return value.intValue();
    }

    /**
     * Establece la frecuencia de este nodo.
     *
     * @param frequency la nueva frecuencia
     */
    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    /**
     * Obtiene el hijo izquierdo de este nodo.
     *
     * @return el nodo hijo izquierdo, o {@code null} si no existe
     */
    public HuffmanNode getLeft() {
        return left;
    }

    /**
     * Establece el hijo izquierdo de este nodo.
     *
     * @param left el nodo que será el hijo izquierdo
     */
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    /**
     * Obtiene el hijo derecho de este nodo.
     *
     * @return el nodo hijo derecho, o {@code null} si no existe
     */
    public HuffmanNode getRight() {
        return right;
    }

    /**
     * Establece el hijo derecho de este nodo.
     *
     * @param right el nodo que será el hijo derecho
     */
    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    /**
     * Indica si el nodo es una hoja (no tiene hijos izquierdo ni derecho).
     *
     * @return {@code true} si el nodo no tiene hijos; {@code false} en caso contrario
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Long.compare(this.frequency, other.frequency);
    }

    /**
     * Devuelve una representación en texto del nodo con su valor y frecuencia.
     *
     * @return cadena con información del nodo
     */
    @Override
    public String toString() {
        return "HuffmanNode{" + "value=" + value + ", freq=" + frequency + '}';
    }
}
