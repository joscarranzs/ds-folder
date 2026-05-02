package com.ds.application.core.structures;

/**
 * Nodo para uso en árboles de Huffman.
 *
 * Este nodo soporta tanto:
 * - Huffman tradicional (caracteres o números)
 * - Huffman basado en pesos con identificadores (String id)
 *
 * IMPORTANTE:
 * - "value" representa el valor del nodo (puede ser null para nodos internos)
 * - "frequency" y "weight" representan el mismo concepto (SIEMPRE deben ser iguales)
 * - "id" se usa cuando trabajamos con identificadores personalizados (ej: "A", "Nodo1")
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    private Number value; // valor del nodo (null si es nodo interno)
    private long frequency; // frecuencia usada por el algoritmo

    // 🔹 Compatibilidad con tu implementación
    private String id;     // identificador opcional (para Huffman de pesos)
    private long weight;   // peso (SIEMPRE igual a frequency)

    private HuffmanNode left;
    private HuffmanNode right;

    /**
     * Constructor principal (recomendado).
     * Se usa tanto para letras como números.
     */
    public HuffmanNode(Number value, long frequency) {
        this.value = value;
        this.frequency = frequency;
        this.weight = frequency; // mantener coherencia
        this.id = null;
    }

    /**
     * Constructor para trabajar con identificadores (Huffman de pesos).
     */
    public HuffmanNode(String id, long weight) {
        this.id = id;
        this.value = null; // no usamos value aquí
        this.frequency = weight;
        this.weight = weight;
    }

    /**
     * Constructor vacío (nodo interno por defecto).
     */
    public HuffmanNode() {
        this.value = null;
        this.frequency = 0L;
        this.weight = 0L;
        this.id = null;
    }

    // ========================
    // GETTERS Y SETTERS
    // ========================

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }

    public long getFrequency() {
        return frequency;
    }

    /**
     * Mantiene coherencia entre frequency y weight.
     */
    public void setFrequency(long frequency) {
        this.frequency = frequency;
        this.weight = frequency;
    }

    public long getWeight() {
        return weight;
    }

    /**
     * Mantiene coherencia entre weight y frequency.
     */
    public void setWeight(long weight) {
        this.weight = weight;
        this.frequency = weight;
    }

    public String getId() {
        return id;
    }

    /**
     * Retorna el valor como entero (compatibilidad con código anterior).
     * -1 indica nodo interno.
     */
    public int getCharacter() {
        if (value == null) return -1;
        return value.intValue();
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    /**
     * Indica si el nodo es hoja.
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Comparación para PriorityQueue (núcleo del algoritmo de Huffman).
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return Long.compare(this.frequency, other.frequency);
    }

    /**
     * Representación del nodo para debugging.
     */
    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                ", id=" + id +
                ", freq=" + frequency +
                '}';
    }
}