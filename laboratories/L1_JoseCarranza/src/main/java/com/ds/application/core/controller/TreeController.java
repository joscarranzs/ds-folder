package com.ds.application.core.controller;

import java.util.List;
import java.util.Optional;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.model.TreeTraversal;
import com.ds.application.core.view.BinaryTree;

public class TreeController {

    private final BinaryTree view;
    private final BinarySearchTree model;
    private TreeNode selectedNode;

    public TreeController(BinaryTree view) {
        this.view = view;
        this.model = new BinarySearchTree();
        this.selectedNode = null;
    }

    public void onDelete(int value) {
        model.delete(value);
        selectedNode = null;
        refreshView();
    }

    public void handleDeleteNode() {
        if (selectedNode == null) {
            return;
        }

        int deletedValue = selectedNode.getValue();
        model.delete(deletedValue);
        selectedNode = null;
        refreshView();
        TreeDialogService.showInformation(
                "Node deleted",
                "Node removed successfully.",
                "The node " + deletedValue + " has been deleted."
        );
    }

    public void handleInsertNode() {
        Optional<Integer> value = TreeDialogService.requestInteger("Insert Node", "Enter integer value:");
        if (value.isEmpty()) {
            return;
        }

        model.insert(value.get());
        refreshView();
        TreeInspectorPresenter.updateInspector(view, model, model.search(value.get()));
    }

    public void handleSearch() {
        if (model.isEmpty()) {
            return;
        }

        Optional<Integer> value = TreeDialogService.requestInteger("Search Node", "Enter node value:");
        if (value.isEmpty()) {
            return;
        }

        TreeNode node = model.search(value.get());
        List<TreeNode> path = TreeTraversal.searchWithPath(model.getRoot(), value.get());
        boolean found = node != null;

        view.animateSearch(path, found);
        if (node != null) {
            TreeInspectorPresenter.updateInspector(view, model, node);
        } else {
            view.setInspector("ERROR...DATA Not found", "—", "—", "—");
        }
    }

    public void handleNewTree() {
        model.reset();
        selectedNode = null;
        view.resetView();
    }

    public void handleNodesByLevel() {
        if (model.isEmpty()) {
            return;
        }

        view.setLevelRepresentationActive();
        view.drawNodesByLevel(model.getRoot());
    }

    public void handleParentNodes() {
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleLeafNodes() {
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleLCI() {
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleLCIM() {
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleSequential() {
        view.setRepresentationActive(true);
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleLinkedList() {
        view.setRepresentationActive(false);
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
    }

    public void handleNodeClick(TreeNode node) {
        this.selectedNode = node;
        TreeInspectorPresenter.updateInspector(view, model, node);
    }

    private void refreshView() {
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
        TreeInspectorPresenter.updateInspector(view, model, selectedNode);
    }
}
