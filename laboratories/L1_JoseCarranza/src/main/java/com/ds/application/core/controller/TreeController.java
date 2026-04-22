package com.ds.application.core.controller;

import java.util.List;
import java.util.Optional;

import com.ds.application.core.model.BinarySearchTree;
import com.ds.application.core.model.TreeNode;
import com.ds.application.core.model.TreeTraversal;
import com.ds.application.core.view.BinaryTree;

/**
 * Coordina las interacciones del usuario entre la interfaz y el modelo de árbol binario.
 *
 * <p>El controlador responde a las pulsaciones de botones y eventos de selección de nodos,
 * actualiza el {@link com.ds.application.core.model.BinarySearchTree} y refresca la visualización,
 * las estadísticas y el panel inspector.</p>
 */
public class TreeController {

    private final BinaryTree view;
    private final BinarySearchTree model;
    private TreeNode selectedNode;

    public TreeController(BinaryTree view) {
        this.view = view;
        this.model = new BinarySearchTree();
        this.selectedNode = null;
    }

    /**
     * Elimina el valor especificado del modelo de árbol y actualiza el estado de la vista.
     *
     * @param value el valor numérico a eliminar del árbol
     */
    public void onDelete(int value) {
        model.delete(value);
        selectedNode = null;
        refreshView();
    }

    /**
     * Elimina el nodo seleccionado actualmente y notifica al usuario con un diálogo de confirmación.
     */
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

    /**
     * Solicita al usuario un valor entero y lo inserta en el árbol binario de búsqueda.
     */
    public void handleInsertNode() {
        Optional<Integer> value = TreeDialogService.requestInteger("Insert Node", "Enter integer value:");
        if (value.isEmpty()) {
            return;
        }

        model.insert(value.get());
        refreshView();
        TreeInspectorPresenter.updateInspector(view, model, model.search(value.get()));
    }

    /**
     * Solicita al usuario un valor de búsqueda, realiza la búsqueda en el árbol y anima el recorrido encontrado.
     */
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

    /**
     * Restablece el modelo de árbol actual y borra la visualización para comenzar un árbol nuevo.
     */
    public void handleNewTree() {
        model.reset();
        selectedNode = null;
        view.resetView();
    }

    /**
     * Cambia la vista a la representación por niveles del árbol actual.
     */
    public void handleNodesByLevel() {
        if (model.isEmpty()) {
            return;
        }

        view.setLevelRepresentationActive();
        view.drawNodesByLevel(model.getRoot());
    }

    /**
     * Actualiza el panel de estadísticas con información de nodos padre.
     */
    public void handleParentNodes() {
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Actualiza el panel de estadísticas con información de nodos hoja.
     */
    public void handleLeafNodes() {
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Actualiza el panel de estadísticas para mostrar la longitud de camino interno del árbol.
     */
    public void handleLCI() {
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Actualiza el panel de estadísticas para mostrar la longitud interna media del árbol.
     */
    public void handleLCIM() {
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Activa la representación de árbol binario y vuelve a dibujar la estructura actual.
     */
    public void handleSequential() {
        view.setRepresentationActive(true);
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Activa la representación en estilo lista enlazada y vuelve a dibujar la estructura actual.
     */
    public void handleLinkedList() {
        view.setRepresentationActive(false);
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
    }

    /**
     * Registra el nodo seleccionado y actualiza el panel inspector para mostrar sus detalles.
     *
     * @param node el nodo que fue clicado en la visualización
     */
    public void handleNodeClick(TreeNode node) {
        this.selectedNode = node;
        TreeInspectorPresenter.updateInspector(view, model, node);
    }

    /**
     * Repinta la representación actual del árbol y sincroniza la información de estadísticas e inspector.
     */
    private void refreshView() {
        view.drawCurrentRepresentation(model.getRoot());
        TreeStatsPresenter.updateStats(view, model);
        TreeInspectorPresenter.updateInspector(view, model, selectedNode);
    }
}
