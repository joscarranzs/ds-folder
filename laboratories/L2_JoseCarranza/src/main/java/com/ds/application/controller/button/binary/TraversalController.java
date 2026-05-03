package com.ds.application.controller.button.binary;

import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;

public class TraversalController {

    private final NodeInspectorController nodeController;
    private final NodeInspector inspector;
    private final TreeInfoBar infoBar;

    public TraversalController(NodeInspectorController nodeController, NodeInspector inspector, TreeInfoBar infoBar) {
        this.nodeController = nodeController;
        this.inspector = inspector;
        this.infoBar = infoBar;
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

        inspector.updateStatus(name + ": " + result);
        infoBar.updateTraversal(name, result);
    }
}