package com.ds.application.view.components.elements.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Linea que representa la arista entre nodos del grafo.
 * Tiene estilo por defecto apropiado para conectores.
 */
public class EdgeElement extends Line {

    /**
     * Crea una arista (línea) entre dos puntos y aplica estilos por defecto.
     *
     * @param startX coordenada X de inicio
     * @param startY coordenada Y de inicio
     * @param endX   coordenada X de fin
     * @param endY   coordenada Y de fin
     */
    public EdgeElement(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        setStroke(Color.web("#94a3b8"));
        setStrokeWidth(2);
    }
}
