package com.ds.application.view.components.elements.shapes;


public class EdgeElement extends Line {

    public EdgeElement(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        setStroke(Color.web("#94a3b8"));
        setStrokeWidth(2);
    }
}