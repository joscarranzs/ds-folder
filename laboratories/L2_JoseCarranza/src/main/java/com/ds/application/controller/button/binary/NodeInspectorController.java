package com.ds.application.controller.button.binary;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.binarysearchtree.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;

public class NodeInspectorController {

    // Árbol principal (modelo)
    private BinarySearchTree tree;

    // Operaciones del árbol (lógica)
    private BinaryTreeOperations operations;

    // Componentes visuales (UI)
    private final BinaryTreeVisualizer visualizer;
    private final NodeInspector inspector;

    public NodeInspectorController(BinaryTreeVisualizer visualizer, NodeInspector inspector) {
        this.visualizer = visualizer;
        this.inspector = inspector;

        // Inicializo árbol vacío
        this.tree = new BinarySearchTree();

        // Inicializo operaciones con la raíz actual
        this.operations = new BinaryTreeOperations(tree.getRoot());

        // Keep visualizer selection wiring. The controller remains the orchestrator between core and UI.
        this.visualizer.setOnNodeSelected(value -> {
            visualizer.highlightValue(value); // Resalta el nodo clickeado
            updateInspector(value);           // Actualiza el panel derecho
            inspector.updateStatus("Nodo seleccionado: " + value);
        });
    }

    // Insert a value into the tree. The view is responsible for gathering input.
    public void insert(int value) {

        // Limpio cualquier resaltado anterior
        visualizer.clearHighlight();

        // Inserto el valor en el árbol
        tree.insert(value);

        // Actualizo la raíz en las operaciones
        operations.setRoot(tree.getRoot());

        // Redibujo el árbol completo
        visualizer.drawTree(tree.getRoot());

        // Actualizo el inspector con el nuevo nodo
        updateInspector(value);

        inspector.updateStatus("Insertado: " + value);
    }

    // Search by value and update the UI accordingly
    public void search(int value) {

        // Siempre actualizo la raíz antes de operar
        operations.setRoot(tree.getRoot());

        // Si el valor existe en el árbol
        if (operations.contains(value)) {

            //animación desde la raíz hasta el nodo encontrado
            boolean animated = visualizer.animateSearchPath(value);

            // Si por alguna razón no animó (fallback)
            if (!animated) {
                visualizer.highlightValue(value);
            }

            // Actualizo el panel inspector
            updateInspector(value);

            inspector.updateStatus("Encontrado: " + value);

        } else {

            // Si no existe, limpio selección visual
            visualizer.clearHighlight();

            inspector.updateStatus("No encontrado: " + value);
        }
    }

    public void deleteSelected(int value) {

        operations.setRoot(tree.getRoot());

        // Si no existe, no hago nada
        if (!operations.contains(value)) {
            inspector.updateStatus("No encontrado: " + value);
            return;
        }

        // Elimino el nodo del árbol
        tree.delete(value);

        // Actualizo raíz después de eliminar
        operations.setRoot(tree.getRoot());

        // Limpio selección y redibujo árbol
        visualizer.clearHighlight();
        visualizer.drawTree(tree.getRoot());

        // Limpio info del inspector
        inspector.clearInfo();

        inspector.updateStatus("Eliminado: " + value);
    }

    public void clear() {

        // Reinicio el árbol completamente
        tree = new BinarySearchTree();

        // Reinicio operaciones con nueva raíz
        operations.setRoot(tree.getRoot());

        // Limpio UI
        visualizer.clear();
        inspector.clearInfo();
    }

    // Provide domain-level traversal and info APIs instead of exposing operations directly.
    public String preOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.preOrderString();
    }

    public String inOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.inOrderString();
    }

    public String postOrderString() {
        operations.setRoot(tree.getRoot());
        return operations.postOrderString();
    }

    public int size() {
        operations.setRoot(tree.getRoot());
        return operations.size();
    }

    public int height() {
        operations.setRoot(tree.getRoot());
        return operations.height();
    }

    public int getLevel(int value) {
        operations.setRoot(tree.getRoot());
        return operations.getLevel(value);
    }

    public String getParentNodesText() {
        operations.setRoot(tree.getRoot());
        return operations.formatListAsText(operations.collectParentNodes());
    }

    public String getLeafNodesText() {
        operations.setRoot(tree.getRoot());
        return operations.formatListAsText(operations.collectLeafNodes());
    }

    // Actualiza el panel derecho con info del nodo seleccionado
    private void updateInspector(int value) {

        operations.setRoot(tree.getRoot());

        inspector.updateNodeInfo(
                value,
                getLevel(value),      // Nivel del nodo
                operations.height(),  // Altura del árbol
                operations.size()     // Cantidad de nodos
        );
    }
}