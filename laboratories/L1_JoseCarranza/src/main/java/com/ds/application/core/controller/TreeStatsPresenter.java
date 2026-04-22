package com.ds.application.core.controller;

import java.util.List;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeMetrics;
import com.ds.application.core.view.BinaryTree;

/**
 * Proporciona un puente entre las métricas del árbol y el panel de estadísticas en la UI.
 *
 * <p>Este presentador calcula estadísticas resumen del árbol actual y las formatea
 * para mostrarlas en la barra de estadísticas.</p>
 */
public final class TreeStatsPresenter {

    private TreeStatsPresenter() {
    }

    /**
     * Actualiza la barra de estadísticas con la profundidad, la longitud de ruta interna,
     * la media de la longitud, la lista de nodos padres y la lista de nodos hoja.
     *
     * @param view la vista de la interfaz que contiene la barra de estadísticas
     * @param model el modelo de árbol actual para analizar
     */
    public static void updateStats(BinaryTree view, BinarySearchTree model) {
        if (model.isEmpty()) {
            view.setStats(0, "—", "—", "—", "—");
            return;
        }

        int depth = TreeMetrics.depth(model.getRoot());
        int lci = TreeMetrics.internalPathLength(model.getRoot());
        double lcim = TreeMetrics.meanInternalPathLength(model.getRoot());
        List<Integer> parents = TreeMetrics.parentValues(model.getRoot());
        List<Integer> leaves = TreeMetrics.leafValues(model.getRoot());

        view.setStats(
                depth,
                String.valueOf(lci),
                String.format("%.2f", lcim),
                parents.toString(),
                leaves.toString()
        );
    }
}
