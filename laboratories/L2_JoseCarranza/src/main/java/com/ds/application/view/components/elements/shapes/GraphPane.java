package com.ds.application.view.components.elements.shapes;


public class GraphPane extends Pane {

    public GraphPane() {
        setPrefSize(680, 430);
        setStyle("-fx-background-color: #f8fafc;");
    }

    public void addShape(Node node) {
        getChildren().add(node);
    }

    public void clearGraph() {
        getChildren().clear();
    }
}