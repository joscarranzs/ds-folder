package com.ds.application.core.model;

import java.util.List;

/**
 * Colección de calculadores de métricas estáticas para el análisis de árboles binarios.
 *
 * <p>Esta clase de utilidad calcula profundidad, tamaño, longitudes de camino, nodos padre,
 * nodos hoja y resúmenes estadísticos usados por la UI.</p>
 */
public final class TreeMetrics {

    private TreeMetrics() {
    }

    /**
     * Calcula la profundidad máxima de un árbol.
     *
     * @param root la raíz del árbol
     * @return el número de niveles desde la raíz hasta la hoja más profunda
     */
    public static int depth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(depth(root.getLeft()), depth(root.getRight()));
    }

    /**
     * Cuenta el número total de nodos en el árbol.
     *
     * @param root la raíz del árbol
     * @return el conteo total de nodos
     */
    public static int size(TreeNode root) {
        return root == null ? 0 : 1 + size(root.getLeft()) + size(root.getRight());
    }

    /**
     * Calcula la suma de todos los valores enteros almacenados en los nodos del árbol.
     *
     * @param root la raíz del árbol
     * @return el total de todos los valores de los nodos
     */
    public static int sumValues(TreeNode root) {
        return root == null ? 0 : root.getValue() + sumValues(root.getLeft()) + sumValues(root.getRight());
    }

    /**
     * Calcula el valor promedio de todos los nodos.
     *
     * @param root la raíz del árbol
     * @return el valor promedio, o 0 cuando el árbol está vacío
     */
    public static double averageValue(TreeNode root) {
        int totalNodes = size(root);
        return totalNodes == 0 ? 0 : (double) sumValues(root) / totalNodes;
    }

    /**
     * Calcula la suma de las profundidades de todos los nodos internos.
     *
     * @param root la raíz del árbol
     * @return la longitud total de la ruta interna
     */
    public static int internalPathLength(TreeNode root) {
        return internalPathLength(root, 1);
    }

    /**
     * Auxiliar para el cálculo de longitud de camino interno que rastrea la profundidad actual.
     *
     * @param node el nodo actual durante la recorrida
     * @param level la profundidad del nodo actual
     * @return la contribución de longitud de camino para el subárbol
     */
    private static int internalPathLength(TreeNode node, int level) {
        if (node == null) {
            return 0;
        }
        int total = (node.getLeft() != null || node.getRight() != null) ? level : 0;
        total += internalPathLength(node.getLeft(), level + 1);
        total += internalPathLength(node.getRight(), level + 1);
        return total;
    }

    /**
     * Cuenta el número de nodos internos en el árbol.
     *
     * @param root la raíz del árbol
     * @return el número de nodos con al menos un hijo
     */
    public static int internalNodeCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = (root.getLeft() != null || root.getRight() != null) ? 1 : 0;
        return count + internalNodeCount(root.getLeft()) + internalNodeCount(root.getRight());
    }

    /**
     * Calcula la longitud interna media de la ruta entre todos los nodos internos.
     *
     * @param root la raíz del árbol
     * @return la longitud interna media, o 0 cuando no hay nodos internos
     */
    public static double meanInternalPathLength(TreeNode root) {
        int internalCount = internalNodeCount(root);
        return internalCount == 0 ? 0 : (double) internalPathLength(root) / internalCount;
    }

    /**
     * Genera una lista de valores almacenados en nodos padres.
     *
     * @param root la raíz del árbol
     * @return una lista con los valores de todos los nodos internos padres
     */
    public static List<Integer> parentValues(TreeNode root) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        collectParents(root, values);
        return values;
    }

    /**
     * Recopila recursivamente valores de los nodos padres internos en la lista proporcionada.
     *
     * @param node el nodo actual durante la recorrida
     * @param values la lista que acumula los valores de los nodos padres
     */
    private static void collectParents(TreeNode node, java.util.List<Integer> values) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null || node.getRight() != null) {
            values.add(node.getValue());
        }
        collectParents(node.getLeft(), values);
        collectParents(node.getRight(), values);
    }

    /**
     * Genera una lista de valores almacenados en nodos hoja.
     *
     * @param root la raíz del árbol
     * @return una lista con los valores de todos los nodos hoja
     */
    public static List<Integer> leafValues(TreeNode root) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        collectLeaves(root, values);
        return values;
    }

    /**
     * Recopila recursivamente valores de los nodos hoja en la lista proporcionada.
     *
     * @param node el nodo actual durante la recorrida
     * @param values la lista que acumula los valores de los nodos hoja
     */
    private static void collectLeaves(TreeNode node, java.util.List<Integer> values) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            values.add(node.getValue());
        }
        collectLeaves(node.getLeft(), values);
        collectLeaves(node.getRight(), values);
    }
}
