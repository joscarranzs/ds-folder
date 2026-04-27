package com.ds.application.core.trees.operations;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.structures.aux.SimpleList;

/**
 * Operaciones utilitarias para árboles binarios de búsqueda.
 * Proporciona búsquedas, recorridos (in-order, pre-order, post-order),
 * y utilidades para tamaño y altura del árbol.
 */
public class BinaryTreeOperations {
    private BinaryTreeNode<Integer> root;

    /**
     * Crea un objeto de operaciones para el árbol cuyo nodo raíz se pasa.
     *
     * @param root nodo raíz del árbol (puede ser null)
     */
    public BinaryTreeOperations(BinaryTreeNode<Integer> root) {
        this.root = root;
    }

    /**
     * Devuelve la raíz actual del árbol.
     *
     * @return nodo raíz, o null si el árbol está vacío
     */
    public BinaryTreeNode<Integer> getRoot() {
        return root;
    }

    /**
     * Establece una nueva raíz para el árbol.
     *
     * @param root nuevo nodo raíz
     */
    public void setRoot(BinaryTreeNode<Integer> root) {
        this.root = root;
    }

    /**
     * Busca un valor en el árbol.
     *
     * @param value valor a buscar
     * @return el nodo que contiene el valor o null si no se encuentra
     */
    public BinaryTreeNode<Integer> search(int value) {
        return searchRec(root, value);
    }

    /**
     * Indica si el árbol contiene el valor dado.
     *
     * @param value valor a comprobar
     * @return true si existe en el árbol, false en caso contrario
     */
    public boolean contains(int value) {
        return search(value) != null;
    }

    /**
     * Calcula la altura del árbol.
     *
     * @return altura del árbol (número de niveles), 0 si está vacío
     */
    public int height() {
        return heightRec(root);
    }

    /**
     * Devuelve el número de nodos del árbol.
     *
     * @return tamaño del árbol
     */
    public int size() {
        return sizeRec(root);
    }

    /**
     * Realiza un recorrido in-order y devuelve los valores encontrados en orden.
     *
     * @return lista con los valores en recorrido in-order
     */
    public SimpleList<Integer> inOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        inOrderRec(root, result);
        return result;
    }

    /**
     * Realiza un recorrido pre-order y devuelve los valores encontrados.
     *
     * @return lista con los valores en recorrido pre-order
     */
    public SimpleList<Integer> preOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        preOrderRec(root, result);
        return result;
    }

    /**
     * Realiza un recorrido post-order y devuelve los valores encontrados.
     *
     * @return lista con los valores en recorrido post-order
     */
    public SimpleList<Integer> postOrder() {
        SimpleList<Integer> result = new SimpleList<>();
        postOrderRec(root, result);
        return result;
    }

    /**
     * Devuelve una representación en cadena del recorrido in-order.
     *
     * @return cadena con valores separados por espacios
     */
    public String inOrderString() {
        return joinValues(inOrder());
    }

    /**
     * Devuelve una representación en cadena del recorrido pre-order.
     *
     * @return cadena con valores separados por espacios
     */
    public String preOrderString() {
        return joinValues(preOrder());
    }

    /**
     * Devuelve una representación en cadena del recorrido post-order.
     *
     * @return cadena con valores separados por espacios
     */
    public String postOrderString() {
        return joinValues(postOrder());
    }

    private BinaryTreeNode<Integer> searchRec(BinaryTreeNode<Integer> node, int value) {
        if (node == null) {
            return null;
        }
        if (value == node.getValue()) {
            return node;
        }
        if (value < node.getValue()) {
            return searchRec(node.getLeft(), value);
        }
        return searchRec(node.getRight(), value);
    }

    private int heightRec(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightRec(node.getLeft());
        int rightHeight = heightRec(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private int sizeRec(BinaryTreeNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRec(node.getLeft()) + sizeRec(node.getRight());
    }

    private void inOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        inOrderRec(node.getLeft(), result);
        result.add(node.getValue());
        inOrderRec(node.getRight(), result);
    }

    private void preOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.getValue());
        preOrderRec(node.getLeft(), result);
        preOrderRec(node.getRight(), result);
    }

    private void postOrderRec(BinaryTreeNode<Integer> node, SimpleList<Integer> result) {
        if (node == null) {
            return;
        }
        postOrderRec(node.getLeft(), result);
        postOrderRec(node.getRight(), result);
        result.add(node.getValue());
    }

    private String joinValues(SimpleList<Integer> values) {
        if (values.size() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(value);
        }
        return builder.toString();
    }
}
