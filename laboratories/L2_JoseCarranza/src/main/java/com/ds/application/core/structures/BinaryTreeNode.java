package com.ds.application.core.structures;

/**
 * Nodo genérico para árboles binarios.
 *
 * @param <T> tipo del valor almacenado en el nodo
 */
public class BinaryTreeNode<T> {
    private T value;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    /**
     * Crea un nodo con el valor indicado y sin hijos.
     *
     * @param value valor a almacenar en el nodo
     */
    public BinaryTreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /** Devuelve el valor almacenado en el nodo. */
    public T getValue() {
        return value;
    }

    /** Reemplaza el valor contenido en el nodo. */
    public void setValue(T value) {
        this.value = value;
    }

    /** Devuelve el hijo izquierdo o null si no existe. */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /** Asigna el hijo izquierdo del nodo. */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /** Devuelve el hijo derecho o null si no existe. */
    public BinaryTreeNode<T> getRight() {
        return right;
    }

    /** Asigna el hijo derecho del nodo. */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
