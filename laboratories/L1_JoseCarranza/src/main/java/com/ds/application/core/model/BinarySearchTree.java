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
     * @return altura del árbol en número de niveles, donde un árbol vacío es -1
     */
    public int depth() {
        return height(root);
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
     * @return nivel del nodo, donde la raíz es 0; devuelve -1 si no se encuentra
     */
    public int level(TreeNode node) {
        return findLevel(root, node, 0);
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
