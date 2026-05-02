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

        // Si el árbol está vacío, muestro el mensaje inicial
        if (root == null) {
            getChildren().add(emptyText);
            return;
        }

        // Uso el ancho real del panel para centrar mejor el árbol
        double paneWidth = getWidth() > 0 ? getWidth() : getPrefWidth();

        // Centro el árbol tomando el ancho disponible
        double centerX = paneWidth / 2;

        // Ajusto el espacio inicial para que no se desborde hacia el panel izquierdo
        double initialGap = Math.min(150, paneWidth / 4);

        // Empiezo a dibujar desde la raíz
        drawNode(root, centerX, 70, initialGap, true);
    }

    // Marca un nodo como seleccionado y vuelve a dibujar el árbol
    public void highlightValue(int value) {
        highlightedValue = value;
        drawTree(currentRoot);
    }

    // Quita el resaltado del nodo seleccionado
    public void clearHighlight() {
        highlightedValue = null;
        drawTree(currentRoot);
    }

    // Anima un recorrido resaltando los nodos uno por uno
    public void animateTraversal(List<Integer> values) {
        animateValues(values, 0.7);
    }

    // Anima la búsqueda desde la raíz hasta el nodo encontrado
    public boolean animateSearchPath(int targetValue) {
        List<Integer> path = new ArrayList<>();

        boolean found = findPath(currentRoot, targetValue, path);

        if (!found) {
            return false;
        }

        animateValues(path, 0.5);
        return true;
    }

    // Busca el camino desde la raíz hasta el valor indicado
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

    // Ejecuta la animación de una lista de valores
    private void animateValues(List<Integer> values, double seconds) {
        if (values == null || values.isEmpty()) {
            return;
        }

        // Si ya hay una animación corriendo, la detengo antes de iniciar otra
        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        traversalTimeline = new Timeline();

        // Creo un frame por cada valor del recorrido o búsqueda
        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);

            KeyFrame frame = new KeyFrame(
                    Duration.seconds(i * seconds),
                    e -> highlightValue(value)
            );

            traversalTimeline.getKeyFrames().add(frame);
        }

        // Al terminar, queda marcado el último nodo visitado
        traversalTimeline.play();
    }

    // Permite que otro componente reciba el valor del nodo seleccionado
    public void setOnNodeSelected(Consumer<Integer> onNodeSelected) {
        this.onNodeSelected = onNodeSelected;
    }

    // Limpia completamente el visualizador
    public void clear() {
        currentRoot = null;
        highlightedValue = null;

        // También detengo la animación si se limpia el árbol
        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        clearGraph();
        getChildren().add(emptyText);
    }

    // Dibuja cada nodo de forma recursiva junto con sus conexiones
    private void drawNode(BinaryTreeNode node, double x, double y, double gap, boolean isRoot) {
        if (node == null) return;

        // Si existe hijo izquierdo, dibujo la línea y luego el nodo hijo
        if (node.getLeft() != null) {
            double childX = x - gap;
            double childY = y + 80;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getLeft(), childX, childY, gap / 1.6, false);
        }

        // Si existe hijo derecho, dibujo la línea y luego el nodo hijo
        if (node.getRight() != null) {
            double childX = x + gap;
            double childY = y + 80;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(node.getRight(), childX, childY, gap / 1.6, false);
        }

        // Al final dibujo el nodo actual encima de las líneas
        addShape(createNode(node, x, y, isRoot));
    }

    // Define cómo se debe ver cada nodo dependiendo de su tipo
    private NodeElement createNode(BinaryTreeNode node, double x, double y, boolean isRoot) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;
        boolean isHighlighted = highlightedValue != null && highlightedValue.equals(node.getValue());

        String text = String.valueOf(node.getValue());
        int value = ((Number) node.getValue()).intValue();

        // La raíz o el nodo seleccionado se muestran oscuros
        if (isHighlighted || isRoot) {
            return createClickableNode(x, y, text, "#0f172a", "#ffffff", "#ffffff", value);
        }

        // Los nodos hoja se muestran blancos con borde azul
        if (isLeaf) {
            return createClickableNode(x, y, text, "#ffffff", "#2563eb", "#111827", value);
        }

        // Los nodos internos se muestran azules
        return createClickableNode(x, y, text, "#2563eb", "#ffffff", "#ffffff", value);
    }

    // Crea un nodo visual y le agrega el evento de click
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

        // Cuando el usuario hace click, aviso qué valor fue seleccionado
        nodeElement.setOnMouseClicked(e -> {
            if (onNodeSelected != null) {
                onNodeSelected.accept(value);
            }
        });

        return nodeElement;
    }
}