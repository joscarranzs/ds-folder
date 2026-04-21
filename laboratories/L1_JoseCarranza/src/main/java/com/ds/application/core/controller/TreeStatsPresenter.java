package com.ds.application.core.controller;

import java.util.List;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeMetrics;
import com.ds.application.core.view.BinaryTree;

public final class TreeStatsPresenter {

    private TreeStatsPresenter() {
    }

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
