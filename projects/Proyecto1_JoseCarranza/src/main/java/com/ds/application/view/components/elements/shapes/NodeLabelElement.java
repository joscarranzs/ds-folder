package com.ds.application.view.components.elements.shapes;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Etiqueta usada dentro de un nodo para mostrar su valor.
 * Ajusta su posicion horizontal en base a la longitud del texto para
 * centrar visualmente la etiqueta sobre el circulo que la contiene.
 */
public class NodeLabelElement extends Text {

    /**
     * Crea una etiqueta para el nodo con color y posicion.
     *
     * @param value texto a mostrar.
     * @param color color del texto en formato hexadecimal.
     */
    public NodeLabelElement(String value, String color) {
        super(value);
        setFill(Color.web(color));
        setStyle("-fx-font-size: 11px; -fx-font-weight: bold;");

        double offset = value.length() * 3.5;
        setX(-offset);
        setY(4);
    }
}
