package com.ds.application.core.model;

/**
 * Representa un nodo en el árbol binario de búsqueda.
 *
 * <p>Cada nodo almacena un valor entero y referencias a sus hijos izquierdo y derecho.</p>
 */
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    /**
     * Crea un nodo con el valor especificado.
     *
     * @param value valor entero del nodo
     */
    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * Devuelve el valor almacenado en el nodo.
     *
     * @return valor entero del nodo
     */
    public int getValue() {
        return value;
    }

    /**
     * Devuelve el hijo izquierdo del nodo.
     *
     * @return nodo izquierdo o {@code null} si no existe
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Establece el hijo izquierdo del nodo.
     *
     * @param left nuevo hijo izquierdo
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Devuelve el hijo derecho del nodo.
     *
     * @return nodo derecho o {@code null} si no existe
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Establece el hijo derecho del nodo.
     *
     * @param right nuevo hijo derecho
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Indica si el nodo es hoja (sin hijos).
     *
     * @return {@code true} si el nodo no tiene hijos; {@code false} en caso contrario
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Indica si el nodo es interno (tiene al menos un hijo).
     *
     * @return {@code true} si el nodo tiene hijo izquierdo o derecho
     */
    public boolean isInternal() {
        return !isLeaf();
    }
}
