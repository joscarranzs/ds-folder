package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.BinaryTreeNode;
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

    public void drawTree(BinaryTreeNode<Integer> root) {
        getChildren().clear();

        if (root == null) {
            clear();
            return;
        }

        drawNodeRec(root, 325, 70, 160);
    }

    public void showWaitingMessage() {
        getChildren().clear();
        emptyText.setText("Esperando datos del core...");
        getChildren().add(emptyText);
    }

    public void clear() {
        getChildren().clear();
        emptyText.setText("Area de visualizacion del arbol");
        getChildren().add(emptyText);
    }

    private void drawNodeRec(BinaryTreeNode<Integer> node, double x, double y, double gap) {
        if (node == null) {
            return;
        }

        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 90;

            drawLine(x, y, childX, childY);
            drawNodeRec(node.getLeft(), childX, childY, gap / 2);
        }

        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 90;

            drawLine(x, y, childX, childY);
            drawNodeRec(node.getRight(), childX, childY, gap / 2);
        }

        drawNode(x, y, String.valueOf(node.getValue()));
    }

    private void drawNode(double x, double y, String value) {
        Circle circle = new Circle(x, y, 25);
        circle.setFill(Color.web("#dbeafe"));
        circle.setStroke(Color.web("#2563eb"));
        circle.setStrokeWidth(2);

        Text text = new Text(value);
        text.setX(x - 7);
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