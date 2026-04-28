package com.ds.application.view.components.elements.shapes;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NodeLabelElement extends Text {

    public NodeLabelElement(String value, String color) {
        super(value);
        setFill(Color.web(color));
        setStyle("-fx-font-size: 11px; -fx-font-weight: bold;");

        double offset = value.length() * 3.5;
        setX(-offset);
        setY(4);
    }
}