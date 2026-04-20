package com.ds.application.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un árbol binario de búsqueda (BST).
 *
 * <p>Esta clase mantiene la raíz del árbol y proporciona operaciones para
 * insertar, buscar, recorrer y calcular métricas del árbol.</p>
 */
public class BinarySearchTree {

    private TreeNode root;

    /**
     * Devuelve la raíz del árbol.
     *
     * @return nodo raíz o {@code null} si el árbol está vacío
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Indica si el árbol está vacío.
     *
     * @return {@code true} cuando no existe raíz, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserta un valor entero en el árbol.
     *
     * <p>La inserción respeta las reglas del árbol binario de búsqueda:
     * valores menores a la raíz van a la izquierda y valores mayores a la derecha.
     * Los valores duplicados no se agregan.</p>
     *
     * @param value valor que se desea insertar
     */
    public void insert(int value) {
        root = insertNode(root, value);
    }

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
     * Busca un nodo por su valor.
     *
     * @param value valor a buscar
     * @return el nodo encontrado o {@code null} si no existe
     */
    public TreeNode search(int value) {
        return searchNode(root, value);
    }

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
     * Calcula la profundidad máxima del árbol.
     *
     * @return número de niveles del árbol, donde un árbol vacío es 0
     */
    public int depth() {
        return root == null ? 0 : height(root) + 1;
    }

    private int height(TreeNode current) {
        if (current == null) {
            return -1;
        }

        return 1 + Math.max(
                height(current.getLeft()),
                height(current.getRight())
        );
    }

    /**
     * Cuenta el número total de nodos en el árbol.
     *
     * @return número de nodos almacenados
     */
    public int size() {
        return countNodes(root);
    }

    private int countNodes(TreeNode current) {
        if (current == null) {
            return 0;
        }

        return 1 + countNodes(current.getLeft()) + countNodes(current.getRight());
    }

    /**
     * Suma los valores de todos los nodos del árbol.
     *
     * @return suma de valores de los nodos
     */
    public int sumValues() {
        return sumValues(root);
    }

    private int sumValues(TreeNode current) {
        if (current == null) {
            return 0;
        }

        return current.getValue()
                + sumValues(current.getLeft())
                + sumValues(current.getRight());
    }

    /**
     * Calcula el promedio de los valores almacenados en el árbol.
     *
     * @return promedio aritmético de los valores, o 0 si el árbol está vacío
     */
    public double averageValue() {
        if (root == null) {
            return 0;
        }

        return (double) sumValues() / size();
    }

    /**
     * Calcula la longitud de camino interno del árbol.
     *
     * <p>Es la suma de los niveles (1-based) de todos los nodos internos
     * (nodos con al menos un hijo).</p>
     *
     * @return suma de los niveles de los nodos internos
     */
    public int internalPathLength() {
        return internalPathLength(root, 1);
    }

    private int internalPathLength(TreeNode current, int level) {
        if (current == null) {
            return 0;
        }

        int total = 0;
        if (current.getLeft() != null || current.getRight() != null) {
            total += level;
        }

        total += internalPathLength(current.getLeft(), level + 1);
        total += internalPathLength(current.getRight(), level + 1);
        return total;
    }

    /**
     * Devuelve el número de nodos internos del árbol.
     *
     * @return cantidad de nodos con al menos un hijo
     */
    public int internalNodeCount() {
        return countInternalNodes(root);
    }

    private int countInternalNodes(TreeNode current) {
        if (current == null) {
            return 0;
        }

        int count = (current.getLeft() != null || current.getRight() != null) ? 1 : 0;
        return count + countInternalNodes(current.getLeft()) + countInternalNodes(current.getRight());
    }

    /**
     * Calcula la longitud de camino interno medio del árbol.
     *
     * <p>Es el promedio de los niveles de los nodos internos.</p>
     *
     * @return promedio de la longitud de camino interno, o 0 si no hay nodos internos
     */
    public double meanInternalPathLength() {
        int internalCount = internalNodeCount();
        if (internalCount == 0) {
            return 0;
        }

        return (double) internalPathLength() / internalCount;
    }

    /**
     * Devuelve los valores de los nodos que tienen al menos un hijo.
     *
     * @return lista de valores de nodos padres
     */
    public List<Integer> parentValues() {
        List<Integer> values = new ArrayList<>();
        collectParents(root, values);
        return values;
    }

    private void collectParents(TreeNode current, List<Integer> values) {
        if (current == null) {
            return;
        }

        if (current.getLeft() != null || current.getRight() != null) {
            values.add(current.getValue());
        }

        collectParents(current.getLeft(), values);
        collectParents(current.getRight(), values);
    }

    /**
     * Devuelve los valores de los nodos hoja.
     *
     * @return lista de valores de nodos sin hijos
     */
    public List<Integer> leafValues() {
        List<Integer> values = new ArrayList<>();
        collectLeaves(root, values);
        return values;
    }

    private void collectLeaves(TreeNode current, List<Integer> values) {
        if (current == null) {
            return;
        }

        if (current.isLeaf()) {
            values.add(current.getValue());
        }

        collectLeaves(current.getLeft(), values);
        collectLeaves(current.getRight(), values);
    }

    /**
     * Obtiene el nivel de un nodo dentro del árbol.
     *
     * @param node nodo del cual se solicita el nivel
     * @return nivel del nodo, donde la raíz es 1; devuelve -1 si no se encuentra
     */
    public int level(TreeNode node) {
        return findLevel(root, node, 1);
    }

    private int findLevel(TreeNode current, TreeNode target, int depth) {
        if (current == null) {
            return -1;
        }

        if (current == target) {
            return depth;
        }

        int found = findLevel(current.getLeft(), target, depth + 1);
        return found != -1
                ? found
                : findLevel(current.getRight(), target, depth + 1);
    }

    /**
     * Elimina todos los nodos del árbol.
     */
    public void reset() {
        root = null;
    }
}
