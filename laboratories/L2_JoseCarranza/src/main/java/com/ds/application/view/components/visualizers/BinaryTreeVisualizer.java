package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.scene.control.Label;

import java.util.function.Consumer;

public class BinaryTreeVisualizer extends GraphPane {

    private Label emptyText;
    private Integer highlightedValue;
    private BinaryTreeNode currentRoot;
    private Consumer<Integer> onNodeSelected;

    public BinaryTreeVisualizer() {
        super();

        emptyText = new Label("Área de visualización del árbol");
        emptyText.setLayoutX(220);
        emptyText.setLayoutY(200);

        getChildren().add(emptyText);
    }

    public void drawTree(BinaryTreeNode root) {
        currentRoot = root;
        clearGraph();

        if (root == null) {
            getChildren().add(emptyText);
            return;
        }

        double centerX = getPrefWidth() / 2;
        drawNode(root, centerX, 60, 170, true);
    }

    public void highlightValue(int value) {
        highlightedValue = value;
        drawTree(currentRoot);
    }

    public void clearHighlight() {
        highlightedValue = null;
        drawTree(currentRoot);
    }

    public void setOnNodeSelected(Consumer<Integer> onNodeSelected) {
        this.onNodeSelected = onNodeSelected;
    }

    public void clear() {
        currentRoot = null;
        highlightedValue = null;
        clearGraph();
        getChildren().add(emptyText);
    }

    private void drawNode(BinaryTreeNode node, double x, double y, double gap, boolean isRoot) {
        if (node == null) return;

        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 80;
            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getLeft(), childX, childY, gap / 1.6, false);
        }

        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 80;
            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getRight(), childX, childY, gap / 1.6, false);
        }

        addShape(createNode(node, x, y, isRoot));
    }

    private NodeElement createNode(BinaryTreeNode node, double x, double y, boolean isRoot) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;
        boolean isHighlighted = highlightedValue != null && highlightedValue.equals(node.getValue());

        String text = String.valueOf(node.getValue());

        if (isHighlighted || isRoot) {
            return createClickableNode(x, y, text, "#0f172a", "#ffffff", "#ffffff", node.getValue());
        }

        if (isLeaf) {
            return createClickableNode(x, y, text, "#ffffff", "#2563eb", "#111827", node.getValue());
        }

        return createClickableNode(x, y, text, "#2563eb", "#ffffff", "#ffffff", node.getValue());
    }

    private NodeElement createClickableNode(
            double x,
            double y,
            String text,
            String fillColor,
            String strokeColor,
            String textColor,
            int value
    ) {
        NodeElement nodeElement = new NodeElement(x, y, text, fillColor, strokeColor, textColor);

        nodeElement.setOnMouseClicked(e -> {
            if (onNodeSelected != null) {
                onNodeSelected.accept(value);
            }
        });

        return nodeElement;
    }
}
