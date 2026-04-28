package com.ds.application.controller.button.binary;

public class DeletionController {

    private final NodeInspectorController nodeController;

    public DeletionController(NodeInspectorController nodeController) {
        this.nodeController = nodeController;
    }

    public void delete(int value) {
        nodeController.deleteSelected(value);
    }
}