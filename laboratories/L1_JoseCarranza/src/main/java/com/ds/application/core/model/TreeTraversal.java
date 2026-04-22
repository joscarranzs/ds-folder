package com.ds.application.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Proporciona utilidades de recorrido y auxiliares de navegación de árbol.
 *
 * <p>Esta clase soporta generación de caminos de búsqueda, extracción de niveles y obtención de profundidad de nodos.</p>
 */
public final class TreeTraversal {

    private TreeTraversal() {
    }

    /**
     * Busca un valor en el árbol y devuelve el camino recorrido durante la búsqueda.
     *
     * @param root la raíz del árbol
     * @param value el valor a buscar
     * @return una lista con los nodos visitados en orden durante la búsqueda
     */
    public static List<TreeNode> searchWithPath(TreeNode root, int value) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            path.add(current);
            if (value == current.getValue()) {
                return path;
            }
            current = value < current.getValue() ? current.getLeft() : current.getRight();
        }

        return path;
    }

    /**
     * Recoge los nodos que aparecen en el nivel de profundidad especificado en el árbol.
     *
     * @param root la raíz del árbol
     * @param level el nivel uno-basado a recuperar
     * @return una lista de nodos en el nivel solicitado
     */
    public static List<TreeNode> nodesAtLevel(TreeNode root, int level) {
        List<TreeNode> nodes = new ArrayList<>();
        collectNodesAtLevel(root, 1, level, nodes);
        return nodes;
    }

    private static void collectNodesAtLevel(TreeNode node, int currentLevel, int targetLevel, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        if (currentLevel == targetLevel) {
            nodes.add(node);
            return;
        }
        collectNodesAtLevel(node.getLeft(), currentLevel + 1, targetLevel, nodes);
        collectNodesAtLevel(node.getRight(), currentLevel + 1, targetLevel, nodes);
    }

    /**
     * Calcula la profundidad de un nodo objetivo relativa a la raíz.
     *
     * @param root la raíz del árbol
     * @param target el nodo a localizar
     * @return el nivel uno-basado del nodo objetivo, o -1 si no se encuentra
     */
    public static int level(TreeNode root, TreeNode target) {
        return findLevel(root, target, 1);
    }

    private static int findLevel(TreeNode current, TreeNode target, int depth) {
        if (current == null) {
            return -1;
        }
        if (current == target) {
            return depth;
        }
        int found = findLevel(current.getLeft(), target, depth + 1);
        return found != -1 ? found : findLevel(current.getRight(), target, depth + 1);
    }
}
