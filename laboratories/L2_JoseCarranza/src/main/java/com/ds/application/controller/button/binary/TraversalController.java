package com.ds.application.controller.button.binary;

import com.ds.application.core.trees.operations.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;

public class TraversalController {

    private final NodeInspectorController nodeController;
    private final NodeInspector inspector;

    public TraversalController(NodeInspectorController nodeController, NodeInspector inspector) {
        this.nodeController = nodeController;
        this.inspector = inspector;
    }

    public void preorder() {
        showTraversal("Pre-orden", nodeController.getOperations().preOrderString());
    }

    public void inorder() {
        showTraversal("In-orden", nodeController.getOperations().inOrderString());
    }

    public void postorder() {
        showTraversal("Pos-orden", nodeController.getOperations().postOrderString());
    }

    private void showTraversal(String name, String result) {
        BinaryTreeOperations operations = nodeController.getOperations();

        if (operations.size() == 0) {
            inspector.updateStatus("Arbol vacio.");
            return;
        }

        inspector.updateTraversal(name, result);
    }
}