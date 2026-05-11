package com.ds.application.core.structures;

/**
 * Nodo para un árbol binario de búsqueda (BST) que almacena exclusivamente
 * valores enteros. Se elimina la parametrización genérica porque la lógica
 * del proyecto trabaja sólo con números.
 */
public class BinaryTreeNode {
    private Integer value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode parent;

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    /**
     * Construye un nodo vacío sin valor (útil para inicializaciones).
     */
    public BinaryTreeNode() {
        this.value = null;
    }

    public BinaryTreeNode(Integer value, BinaryTreeNode left, BinaryTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
        if (left != null) left.parent = this;
        if (right != null) right.parent = this;
    }

    /**
     * Obtiene el valor almacenado en el nodo.
     *
     * @return el valor del nodo, puede ser {@code null} si no tiene valor
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Establece el valor almacenado en este nodo.
     *
     * @param value el valor a almacenar (puede ser {@code null})
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Obtiene el hijo izquierdo de este nodo.
     *
     * @return el nodo hijo izquierdo, o {@code null} si no existe
     */
    public BinaryTreeNode getLeft() {
        return left;
    }

    /**
     * Establece el hijo izquierdo de este nodo y actualiza su padre.
     *
     * @param left el nodo que será el hijo izquierdo (puede ser {@code null})
     */
    public void setLeft(BinaryTreeNode left) {
        this.left = left;
        if (left != null) left.parent = this;
    }

    /**
     * Obtiene el hijo derecho de este nodo.
     *
     * @return el nodo hijo derecho, o {@code null} si no existe
     */
    public BinaryTreeNode getRight() {
        return right;
    }

    /**
     * Establece el hijo derecho de este nodo y actualiza su padre.
     *
     * @param right el nodo que será el hijo derecho (puede ser {@code null})
     */
    public void setRight(BinaryTreeNode right) {
        this.right = right;
        if (right != null) right.parent = this;
    }

    /**
     * Obtiene el padre de este nodo.
     *
     * @return el nodo padre, o {@code null} si no tiene padre
     */
    public BinaryTreeNode getParent() {
        return parent;
    }

    /**
     * Establece el padre de este nodo.
     *
     * @param parent el nodo que será padre de este nodo (puede ser {@code null})
     */
    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    /**
     * Indica si el nodo es una hoja (no tiene hijos).
     *
     * @return {@code true} si no tiene hijos; {@code false} en caso contrario
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Indica si el nodo tiene un hijo izquierdo.
     *
     * @return {@code true} si existe un hijo izquierdo; {@code false} en caso contrario
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Indica si el nodo tiene un hijo derecho.
     *
     * @return {@code true} si existe un hijo derecho; {@code false} en caso contrario
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Devuelve la cantidad de hijos que tiene este nodo (0, 1 o 2).
     *
     * @return número de hijos presentes
     */
    public int childCount() {
        int c = 0;
        if (left != null) c++;
        if (right != null) c++;
        return c;
    }

    /**
     * Devuelve una representación en cadena de este nodo, mostrando su valor.
     *
     * @return una cadena que representa el nodo
     */
    @Override
    public String toString() {
        return "BinaryTreeNode{" + value + "}";
    }
}
