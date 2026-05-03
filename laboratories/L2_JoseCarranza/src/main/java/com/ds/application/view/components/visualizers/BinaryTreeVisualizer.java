package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTreeVisualizer extends GraphPane {

    // Texto que aparece cuando todavía no hay árbol dibujado
    private Label emptyText;

    // Guarda el valor del nodo que quiero resaltar visualmente
    private Integer highlightedValue;

    // Guardo la raíz actual para poder redibujar el árbol cuando sea necesario
    private BinaryTreeNode currentRoot;

    // Evento que se ejecuta cuando el usuario hace click sobre un nodo
    private Consumer<Integer> onNodeSelected;

    // Timeline que uso para animar recorridos o búsquedas
    private Timeline traversalTimeline;

    // Valor de zoom actual del visualizador
    private double zoomLevel = 1.0;

    public BinaryTreeVisualizer() {
        super();

        // Esto evita que los nodos o líneas se dibujen encima del panel lateral
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);

        // Mensaje inicial del área de visualización
        emptyText = new Label("Área de visualización del árbol");
        emptyText.setLayoutX(220);
        emptyText.setLayoutY(200);

        getChildren().add(emptyText);
    }

    // Dibuja el árbol completo desde la raíz
    public void drawTree(BinaryTreeNode root) {
        currentRoot = root;
        clearGraph();

        if (root == null) {
            getChildren().add(emptyText);
            return;
        }

        double paneWidth = getWidth() > 0 ? getWidth() : getPrefWidth();

        // Muevo un poco el centro hacia la derecha para que ramas izquierdas no se corten
        double centerX = paneWidth / 2 + 80;

        // Espacio más controlado para evitar que el árbol se esconda detrás del panel
        double initialGap = Math.min(170, paneWidth / 4);

        drawNode(root, centerX, 70, initialGap, true);
    }

    // Hace zoom in sobre el árbol
    public void zoomIn() {
        zoomLevel += 0.1;

        if (zoomLevel > 1.6) {
            zoomLevel = 1.6;
        }

        applyZoom();
    }

    // Hace zoom out sobre el árbol
    public void zoomOut() {
        zoomLevel -= 0.1;

        if (zoomLevel < 0.6) {
            zoomLevel = 0.6;
        }

        applyZoom();
    }

    // Centra la vista y reinicia el zoom
    public void centerView() {
        zoomLevel = 1.0;
        setScaleX(1.0);
        setScaleY(1.0);
        setTranslateX(0);
        setTranslateY(0);
        drawTree(currentRoot);
    }

    // Aplica el zoom actual al visualizador
    private void applyZoom() {
        setScaleX(zoomLevel);
        setScaleY(zoomLevel);
    }

    public void highlightValue(int value) {
        highlightedValue = value;
        drawTree(currentRoot);
    }

    public void clearHighlight() {
        highlightedValue = null;
        drawTree(currentRoot);
    }

    public void animateTraversal(List<Integer> values) {
        animateValues(values, 0.7);
    }

    public boolean animateSearchPath(int targetValue) {
        List<Integer> path = new ArrayList<>();

        boolean found = findPath(currentRoot, targetValue, path);

        if (!found) {
            return false;
        }

        animateValues(path, 0.5);
        return true;
    }

    private boolean findPath(BinaryTreeNode node, int targetValue, List<Integer> path) {
        if (node == null) {
            return false;
        }

        int currentValue = ((Number) node.getValue()).intValue();
        path.add(currentValue);

        if (currentValue == targetValue) {
            return true;
        }

        if (findPath(node.getLeft(), targetValue, path)) {
            return true;
        }

        if (findPath(node.getRight(), targetValue, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    private void animateValues(List<Integer> values, double seconds) {
        if (values == null || values.isEmpty()) {
            return;
        }

        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        traversalTimeline = new Timeline();

        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);

            KeyFrame frame = new KeyFrame(
                    Duration.seconds(i * seconds),
                    e -> highlightValue(value)
            );

            traversalTimeline.getKeyFrames().add(frame);
        }

        traversalTimeline.play();
    }

    public void setOnNodeSelected(Consumer<Integer> onNodeSelected) {
        this.onNodeSelected = onNodeSelected;
    }

    public void clear() {
        currentRoot = null;
        highlightedValue = null;

        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        centerView();
        clearGraph();
        getChildren().add(emptyText);
    }

    private void drawNode(BinaryTreeNode node, double x, double y, double gap, boolean isRoot) {
        if (node == null) return;

        // Espacio mínimo más pequeño para que el árbol no se abra demasiado
        double nextGap = Math.max(gap / 1.45, 55);

        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 85;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getLeft(), childX, childY, nextGap, false);
        }

        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 85;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getRight(), childX, childY, nextGap, false);
        }

        addShape(createNode(node, x, y, isRoot));
    }

    private NodeElement createNode(BinaryTreeNode node, double x, double y, boolean isRoot) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;
        boolean isHighlighted = highlightedValue != null && highlightedValue.equals(node.getValue());

        String text = String.valueOf(node.getValue());
        int value = ((Number) node.getValue()).intValue();

        if (isHighlighted || isRoot) {
            return createClickableNode(x, y, text, "#0f172a", "#ffffff", "#ffffff", value);
        }

        if (isLeaf) {
            return createClickableNode(x, y, text, "#ffffff", "#2563eb", "#111827", value);
        }

        return createClickableNode(x, y, text, "#2563eb", "#ffffff", "#ffffff", value);
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