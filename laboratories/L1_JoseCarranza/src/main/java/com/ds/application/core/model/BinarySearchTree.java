package com.ds.application.core.model;

/**
 * Implementa un árbol binario de búsqueda básico que soporta inserción, eliminación,
 * búsqueda y reinicio.
 *
 * <p>El árbol almacena valores enteros y mantiene la propiedad BST: los valores del
 * subárbol izquierdo son menores que el padre y los valores del subárbol derecho son mayores.</p>
 */
public class BinarySearchTree {

    private TreeNode root;

    /**
     * Devuelve el nodo raíz actual del árbol binario de búsqueda.
     *
     * @return la raíz {@link TreeNode}, o null si el árbol está vacío
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Comprueba si el árbol contiene nodos.
     *
     * @return true si el árbol está vacío, false de lo contrario
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserta un nuevo valor en el árbol binario de búsqueda respetando las reglas de orden.
     * Los valores duplicados se ignoran.
     *
     * @param value el valor entero a insertar en el árbol
     */
    public void insert(int value) {
        root = insertNode(root, value);
    }

    /**
     * Inserta recursivamente un valor en el subárbol apropiado.
     *
     * @param current la raíz del subárbol actual durante la recorrida
     * @param value el valor a insertar
     * @return la raíz del subárbol tras la inserción
     */
    private TreeNode insertNode(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insertNode(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertNode(current.getRight(), value));
        }

        return current;
    }

    /**
     * Elimina el valor especificado del árbol, reorganizando los nodos para preservar la propiedad BST.
     *
     * @param value el valor entero a eliminar
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    /**
     * Elimina recursivamente el nodo que contiene el valor y reconstruye las referencias de los hijos.
     *
     * @param node la raíz del subárbol actual durante la recorrida
     * @param value el valor a eliminar
     * @return la raíz del subárbol tras la eliminación
     */
    private TreeNode deleteRecursive(TreeNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue()) {
            node.setLeft(deleteRecursive(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(deleteRecursive(node.getRight(), value));
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }

            TreeNode minNode = findMin(node.getRight());
            node.setValue(minNode.getValue());
            node.setRight(deleteRecursive(node.getRight(), minNode.getValue()));
        }

        return node;
    }

    /**
     * Encuentra el nodo con el valor más pequeño en un subárbol.
     *
     * @param node la raíz del subárbol a buscar
     * @return el nodo con el valor mínimo en el subárbol
     */
    private TreeNode findMin(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Busca un nodo que contenga el valor especificado.
     *
     * @param value el valor entero a buscar
     * @return el {@link TreeNode} coincidente, o null si el valor no está presente
     */
    public TreeNode search(int value) {
        return searchNode(root, value);
    }

    /**
     * Busca recursivamente el valor dado en el subárbol.
     *
     * @param current la raíz del subárbol actual durante la recorrida
     * @param value el valor a localizar
     * @return el nodo coincidente, o null si no existe
     */
    private TreeNode searchNode(TreeNode current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.getValue()) {
            return current;
        }
        return value < current.getValue()
                ? searchNode(current.getLeft(), value)
                : searchNode(current.getRight(), value);
    }

    /**
     * Limpia el árbol, eliminando todos los nodos y restableciendo la raíz a null.
     */
    public void reset() {
        root = null;
    }
}
