package com.ds.application.controller.button.binary;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.binarysearchtree.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;

public class NodeInspectorController {

    // Árbol principal
    private BinarySearchTree tree;

    // Operaciones del árbol
    private BinaryTreeOperations operations;

    // Componentes visuales
    private final BinaryTreeVisualizer visualizer;
    private final NodeInspector inspector;

    public NodeInspectorController(BinaryTreeVisualizer visualizer, NodeInspector inspector) {
        this.visualizer = visualizer;
        this.inspector = inspector;

        // Inicializo el árbol vacío
        this.tree = new BinarySearchTree();

        // Inicializo operaciones con la raíz actual
        this.operations = new BinaryTreeOperations(tree.getRoot());

        // Conecto el click del nodo visual con el inspector
        this.visualizer.setOnNodeSelected(value -> {
            visualizer.highlightValue(value);
            updateInspector(value);
            inspector.updateStatus("Nodo seleccionado: " + value);
        });
    }

    // Insert a value into the tree. The view is responsible for gathering input.
    public void insert(int value) {
        visualizer.clearHighlight();

        tree.insert(value);
        operations.setRoot(tree.getRoot());

        visualizer.drawTree(tree.getRoot());
        updateInspector(value);

        inspector.updateStatus("Insertado: " + value);
    }

    // Search by value and update the UI accordingly
    public void search(int value) {
        operations.setRoot(tree.getRoot());

        if (operations.contains(value)) {
            boolean animated = visualizer.animateSearchPath(value);

            if (!animated) {
                visualizer.highlightValue(value);
            }

            updateInspector(value);
            inspector.updateStatus("Encontrado: " + value);
        } else {
            visualizer.clearHighlight();
            inspector.updateStatus("No encontrado: " + value);
        }
    }

    public void deleteSelected(int value) {
        operations.setRoot(tree.getRoot());

        if (!operations.contains(value)) {
            inspector.updateStatus("No encontrado: " + value);
            return;
        }

        tree.delete(value);
        operations.setRoot(tree.getRoot());

        visualizer.clearHighlight();
        visualizer.drawTree(tree.getRoot());

        inspector.clearInfo();
        inspector.updateStatus("Eliminado: " + value);
    }

    public void clear() {
        tree = new BinarySearchTree();
        operations.setRoot(tree.getRoot());

        visualizer.clear();
        inspector.clearInfo();
    }

    // Recorridos usados por TraversalController
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

    // Grado global del árbol
    public int degree() {
        operations.setRoot(tree.getRoot());
        return operations.degree();
    }

    // Actualiza el inspector con los datos del nodo seleccionado
    private void updateInspector(int value) {
        operations.setRoot(tree.getRoot());

        BinaryTreeNode node = findNode(tree.getRoot(), value);

        if (node == null) {
            inspector.clearInfo();
            return;
        }

        inspector.updateNodeInfo(
                value,
                operations.getLevel(value),
                operations.nodeDegree(value),
                getNodeType(value),
                getChildText(node.getLeft()),
                getChildText(node.getRight())
        );
    }

    // Busca el nodo real para leer sus hijos
    private BinaryTreeNode findNode(BinaryTreeNode node, int value) {
        if (node == null) {
            return null;
        }

        int currentValue = ((Number) node.getValue()).intValue();

        if (currentValue == value) {
            return node;
        }

        if (value < currentValue) {
            return findNode(node.getLeft(), value);
        }

        return findNode(node.getRight(), value);
    }

    // Define si el nodo es raíz, padre u hoja
    private String getNodeType(int value) {
        if (operations.isRoot(value)) {
            return "Raiz";
        }

        if (operations.isLeaf(value)) {
            return "Hoja";
        }

        if (operations.isParent(value)) {
            return "Padre";
        }

        return "-";
    }

    // Devuelve el valor de un hijo o guion si no existe
    private String getChildText(BinaryTreeNode child) {
        if (child == null) {
            return "-";
        }

        return String.valueOf(child.getValue());
    }
}