package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.scene.control.Label;

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

    public BinaryTreeVisualizer() {
        super();

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

        // Centro el árbol tomando el ancho del panel
        double centerX = getPrefWidth() / 2;

        // Empiezo a dibujar desde la raíz
        drawNode(root, centerX, 60, 170, true);
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

    // Permite que otro componente reciba el valor del nodo seleccionado
    public void setOnNodeSelected(Consumer<Integer> onNodeSelected) {
        this.onNodeSelected = onNodeSelected;
    }

    // Limpia completamente el visualizador
    public void clear() {
        currentRoot = null;
        highlightedValue = null;
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

        // La raíz o el nodo seleccionado se muestran oscuros
        if (isHighlighted || isRoot) {
            return createClickableNode(x, y, text, "#0f172a", "#ffffff", "#ffffff", node.getValue());
        }

        // Los nodos hoja se muestran blancos con borde azul
        if (isLeaf) {
            return createClickableNode(x, y, text, "#ffffff", "#2563eb", "#111827", node.getValue());
        }

        // Los nodos internos se muestran azules
        return createClickableNode(x, y, text, "#2563eb", "#ffffff", "#ffffff", node.getValue());
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