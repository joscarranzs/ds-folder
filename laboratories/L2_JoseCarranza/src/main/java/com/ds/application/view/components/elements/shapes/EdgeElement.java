package com.ds.application.view.components.elements.shapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class EdgeElement extends Line {

    public EdgeElement(double startX, double startY, double endX, double endY) {
        super(startX, startY, endX, endY);
        setStroke(Color.web("#94a3b8"));
        setStrokeWidth(2);
    }
}