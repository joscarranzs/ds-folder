package com.ds.application.view.components.elements.shapes;


public class CircleElement extends Circle {

    public CircleElement(double radius, String fillColor, String strokeColor) {
        super(radius);
        setFill(Color.web(fillColor));
        setStroke(Color.web(strokeColor));
        setStrokeWidth(2.5);
    }
}