package com.ds.application.view.components.elements.shapes;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Contenedor base para dibujar grafos: nodos y aristas.
 * Provee utilidades sencillas para agregar o limpiar shapes.
 */
public class GraphPane extends Pane {

    public GraphPane() {
        setPrefSize(680, 430);
        setStyle("-fx-background-color: #f8fafc;");
    }

    /**
     * Agrega un Node (shape) al pane.
     *
     * @param node elemento JavaFX a agregar
     */
    public void addShape(Node node) {
        getChildren().add(node);
    }

    /**
     * Limpia todos los elementos dibujados en el grafo.
     */
    public void clearGraph() {
        getChildren().clear();
    }
}
