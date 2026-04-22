package com.ds.application.core.model;


/**
 * Representa un nodo en el árbol binario de búsqueda.
 *
 * <p>Cada nodo almacena un valor entero y referencias opcionales a hijos izquierdo y derecho.</p>
 */
public class TreeNode {

    private int value;
    private TreeNode left;
    private TreeNode right;

    
    /**
     * Crea un nuevo nodo con el valor entero especificado.
     *
     * @param value el valor almacenado en este nodo
     */
    public TreeNode(int value) {
        this.value = value;
    }

    
    /**
     * Devuelve el valor entero almacenado en este nodo.
     *
     * @return el valor del nodo
     */
    public int getValue() {
        return value;
    }

    
    /**
     * Actualiza el valor entero almacenado en este nodo.
     *
     * @param value el nuevo valor para este nodo
     */
    public void setValue(int value) {
        this.value = value;
    }

    
    /**
     * Devuelve el hijo izquierdo.
     *
     * @return el hijo izquierdo, o null si no existe
     */
    public TreeNode getLeft() {
        return left;
    }

    
    /**
     * Establece la referencia al hijo izquierdo de este nodo.
     *
     * @param left el nodo que se adjunta como hijo izquierdo
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    
    /**
     * Devuelve el hijo derecho.
     *
     * @return el hijo derecho, o null si no existe
     */
    public TreeNode getRight() {
        return right;
    }

    
    /**
     * Establece la referencia al hijo derecho de este nodo.
     *
     * @param right el nodo que se adjunta como hijo derecho
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    
    /**
     * Determina si este nodo es una hoja.
     *
     * @return true cuando el nodo no tiene hijos, false en caso contrario
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    
    /**
     * Determina si este nodo tiene al menos un hijo.
     *
     * @return true cuando el nodo es interno, false cuando es hoja
     */
    public boolean isInternal() {
        return !isLeaf();
    }
}
