package com.ds.application.view.components.visualizers;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BinaryTreeVisualizer extends Pane {

    private Label emptyText;

    public BinaryTreeVisualizer() {
        setPrefSize(650, 500);
        setStyle("-fx-background-color: #f8fafc;");

        emptyText = new Label("Area de visualizacion del arbol");
        emptyText.setLayoutX(220);
        emptyText.setLayoutY(220);

        getChildren().add(emptyText);
    }

    public void showWaitingMessage() {
        getChildren().clear();
        emptyText.setText("Apuren el core >:v");
        getChildren().add(emptyText);
    }

    public void drawSampleTree() {
        getChildren().clear();

        drawLine(325, 80, 220, 180);
        drawLine(325, 80, 430, 180);

        drawNode(325, 80, "Raiz", true);
        drawNode(220, 180, "Izq", false);
        drawNode(430, 180, "Der", false);
    }

    public void clear() {
        getChildren().clear();
        emptyText.setText("Area de visualizacion del arbol");
        getChildren().add(emptyText);
    }

    private void drawNode(double x, double y, String value, boolean selected) {
        Circle circle = new Circle(x, y, 25);

        if (selected) {
            circle.setFill(Color.web("#dbeafe"));
            circle.setStroke(Color.web("#2563eb"));
        } else {
            circle.setFill(Color.web("#ffffff"));
            circle.setStroke(Color.web("#94a3b8"));
        }

        circle.setStrokeWidth(2);

        Text text = new Text(value);
        text.setX(x - 13);
        text.setY(y + 5);

        getChildren().addAll(circle, text);
    }

    private void drawLine(double startX, double startY, double endX, double endY) {
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.web("#cbd5e1"));
        line.setStrokeWidth(2);

        getChildren().add(line);
    }
}