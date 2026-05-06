package com.ds.application.view.components.elements.shapes;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

/**
 * Elemento compuesto que representa un nodo del grafo: circulo + etiqueta.
 * Se posiciona usando layout X/Y para facilitar el dibujo en el GraphPane.
 */
public class NodeElement extends ShapeElement {

    /**
     * Crea un elemento de nodo compuesto por un circulo y su etiqueta.
     * El nodo se posiciona con layoutX/layoutY para facilitar su colocacion
     * dentro del GraphPane.
     *
     * @param x           coordenada X donde se colocara el centro del nodo
     * @param y           coordenada Y donde se colocara el centro del nodo
     * @param text        texto mostrado en la etiqueta del nodo
     * @param fillColor   color de relleno del circulo
     * @param strokeColor color del borde del circulo
     * @param textColor   color del texto
     */
    public NodeElement(
            double x,
            double y,
            String text,
            String fillColor,
            String strokeColor,
            String textColor)
{
        // Calculo un tamano dinamico para que textos largos no se salgan del nodo
        double size = Math.max(48, text.length() * 8 + 20);

        CircleElement circle = new CircleElement(size / 2, fillColor, strokeColor);
        NodeLabelElement label = new NodeLabelElement(text, textColor);

        // Centro la etiqueta dentro del circulo
        StackPane content = new StackPane();
        content.setAlignment(Pos.CENTER);
        content.setMinSize(size, size);
        content.setPrefSize(size, size);
        content.setMaxSize(size, size);
        content.getChildren().addAll(circle, label);

        getChildren().add(content);

        // Ajusto layout para que x/y sigan siendo el centro real del nodo
        setLayoutX(x - size / 2);
        setLayoutY(y - size / 2);
    }
}