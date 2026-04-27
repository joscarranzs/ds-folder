package com.ds.application.view.components.elements.shapes;

public class NodeElement extends ShapeElement {

    public NodeElement(
            double x,
            double y,
            String text,
            String fillColor,
            String strokeColor,
            String textColor
    ) {
        CircleElement circle = new CircleElement(24, fillColor, strokeColor);
        NodeLabelElement label = new NodeLabelElement(text, textColor);

        getChildren().addAll(circle, label);

        setLayoutX(x);
        setLayoutY(y);
    }
}