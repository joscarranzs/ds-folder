package com.ds.application.view.components.visualizers;

import com.ds.application.view.components.inspector.NodeInspector;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class BinaryTreeVisualizer extends Pane {

    private ArrayList<String> nodes;
    private Label emptyText;
    private NodeInspector inspector;
    private int selectedIndex = -1;

    public BinaryTreeVisualizer(NodeInspector inspector) {
        this.inspector = inspector;
        nodes = new ArrayList<>();

        setPrefSize(650, 500);
        setStyle("-fx-background-color: #f8fafc;");

        emptyText = new Label("Area de visualizacion del arbol");
        emptyText.setLayoutX(220);
        emptyText.setLayoutY(220);

        getChildren().add(emptyText);
    }

    public void addNode(String value) {
        nodes.add(value);

        selectedIndex = nodes.size() - 1;
        inspector.updateInfo(value, selectedIndex, getLevel(selectedIndex));

        drawTree();
    }

    public boolean searchNode(String value) {
        selectedIndex = -1;

        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(value)) {
                selectedIndex = i;
                inspector.updateInfo(value, i, getLevel(i));
                drawTree();
                return true;
            }
        }

        drawTree();
        return false;
    }

    public void showTraversal(String type) {
        if (nodes.isEmpty()) {
            inspector.updateTraversal(type, "arbol vacio");
            return;
        }

        ArrayList<String> result = new ArrayList<>();

        if (type.equals("InOrden")) {
            inorder(0, result);
        } else if (type.equals("PreOrden")) {
            preorder(0, result);
        } else if (type.equals("PostOrden")) {
            postorder(0, result);
        }

        inspector.updateTraversal(type, String.join(" - ", result));
    }

    public void clear() {
        nodes.clear();
        selectedIndex = -1;
        inspector.clearInfo();
        drawTree();
    }

    private void drawTree() {
        getChildren().clear();

        if (nodes.isEmpty()) {
            getChildren().add(emptyText);
            return;
        }

        for (int i = 1; i < nodes.size(); i++) {
            int parentIndex = (i - 1) / 2;

            double[] childPosition = getNodePosition(i);
            double[] parentPosition = getNodePosition(parentIndex);

            Line line = new Line(
                parentPosition[0],
                parentPosition[1],
                childPosition[0],
                childPosition[1]
            );

            line.setStroke(Color.web("#94a3b8"));
            getChildren().add(line);
        }

        for (int i = 0; i < nodes.size(); i++) {
            double[] position = getNodePosition(i);
            drawNode(position[0], position[1], nodes.get(i), i == selectedIndex);
        }
    }

    private void drawNode(double x, double y, String value, boolean selected) {
        Circle circle = new Circle(x, y, 24);

        if (selected) {
            circle.setFill(Color.web("#fde68a"));
            circle.setStroke(Color.web("#d97706"));
        } else {
            circle.setFill(Color.web("#dbeafe"));
            circle.setStroke(Color.web("#2563eb"));
        }

        circle.setStrokeWidth(2);

        Text text = new Text(value);
        text.setX(x - 6);
        text.setY(y + 5);

        getChildren().addAll(circle, text);
    }

    private double[] getNodePosition(int index) {
        int level = getLevel(index);
        int firstIndex = (int) Math.pow(2, level) - 1;
        int positionInLevel = index - firstIndex;
        int nodesInLevel = (int) Math.pow(2, level);

        double width = 650;
        double gap = width / (nodesInLevel + 1);

        double x = gap * (positionInLevel + 1);
        double y = 70 + level * 90;

        return new double[]{x, y};
    }

    private int getLevel(int index) {
        int level = 0;
        int totalNodes = 1;

        while (index >= totalNodes) {
            level++;
            totalNodes += Math.pow(2, level);
        }

        return level;
    }

    private void inorder(int index, ArrayList<String> result) {
        if (index >= nodes.size()) {
            return;
        }

        inorder(index * 2 + 1, result);
        result.add(nodes.get(index));
        inorder(index * 2 + 2, result);
    }

    private void preorder(int index, ArrayList<String> result) {
        if (index >= nodes.size()) {
            return;
        }

        result.add(nodes.get(index));
        preorder(index * 2 + 1, result);
        preorder(index * 2 + 2, result);
    }

    private void postorder(int index, ArrayList<String> result) {
        if (index >= nodes.size()) {
            return;
        }

        postorder(index * 2 + 1, result);
        postorder(index * 2 + 2, result);
        result.add(nodes.get(index));
    }
}