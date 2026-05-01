package com.ds.application.view.components.elements.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleElement extends Circle {

    public CircleElement(double radius, String fillColor, String strokeColor) {
        super(radius);
        setFill(Color.web(fillColor));
        setStroke(Color.web(strokeColor));
        setStrokeWidth(2.5);
    }
}