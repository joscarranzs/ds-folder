package com.ds.application.core.controller;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.model.TreeTraversal;
import com.ds.application.core.view.BinaryTree;

/**
 * Responsable de actualizar el panel inspector según el nodo seleccionado.
 *
 * <p>Cuando se selecciona un nodo, el presentador calcula propiedades como el grado,
 * el nivel y los valores de los hijos, y luego escribe esa información en la vista.</p>
 */
public final class TreeInspectorPresenter {

    private TreeInspectorPresenter() {
    }

    /**
     * Actualiza el panel inspector para mostrar metadatos del nodo seleccionado.
     *
     * @param view la vista de la interfaz que contiene el panel inspector
     * @param model el modelo de árbol binario de búsqueda usado para calcular la información
     * @param node el nodo seleccionado a inspeccionar, o null cuando no hay nodo seleccionado
     */
    public static void updateInspector(BinaryTree view, BinarySearchTree model, TreeNode node) {
        if (node == null) {
            view.setInspector("—", "—", "—", "—");
            return;
        }

        int degree = (node.getLeft() != null ? 1 : 0) + (node.getRight() != null ? 1 : 0);
        int level = TreeTraversal.level(model.getRoot(), node);
        String levelText = level == -1 ? "—" : String.valueOf(level);
        String childNodes = modelChildrenText(node);

        view.setInspector(
                String.valueOf(node.getValue()),
                String.valueOf(degree),
                levelText,
                childNodes
        );
    }

    /**
     * Genera una cadena separada por comas con los valores de los hijos del nodo seleccionado,
     * o devuelve "ninguno" si el nodo no tiene hijos.
     *
     * @param node el nodo seleccionado
     * @return una cadena que describe los valores de los hijos izquierdo y derecho
     */
    private static String modelChildrenText(TreeNode node) {
        StringBuilder builder = new StringBuilder();
        if (node.getLeft() != null) {
            builder.append(node.getLeft().getValue());
        }
        if (node.getRight() != null) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(node.getRight().getValue());
        }
        return builder.length() == 0 ? "none" : builder.toString();
    }
}
