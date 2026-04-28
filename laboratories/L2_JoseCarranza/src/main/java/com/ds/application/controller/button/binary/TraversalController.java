package com.ds.application.controller.button.binary;


public class TraversalController {

    private final NodeInspectorController nodeController;
    private final NodeInspector inspector;

    public TraversalController(NodeInspectorController nodeController, NodeInspector inspector) {
        this.nodeController = nodeController;
        this.inspector = inspector;
    }

    public void preorder() {
        showTraversal("Pre-orden", nodeController.preOrderString());
    }

    public void inorder() {
        showTraversal("In-orden", nodeController.inOrderString());
    }

    public void postorder() {
        showTraversal("Pos-orden", nodeController.postOrderString());
    }

    private void showTraversal(String name, String result) {
        if (nodeController.size() == 0) {
            inspector.updateStatus("Arbol vacio.");
            return;
        }

        inspector.updateTraversal(name, result);
    }
}
