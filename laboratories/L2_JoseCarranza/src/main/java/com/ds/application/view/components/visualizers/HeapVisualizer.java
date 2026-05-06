package com.ds.application.view.components.visualizers;

import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class HeapVisualizer extends GraphPane {

    // Texto que aparece cuando no hay elementos
    private Label emptyText;

    // Lista actual del heap (representación en array)
    private List<Integer> heapData;

    public HeapVisualizer() {
        super();

        // Evita que el dibujo se salga del área
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);

        // Mensaje inicial
        emptyText = new Label("Área de visualización del Montón");
        emptyText.setStyle(
        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: #94a3b8;");
        emptyText.layoutXProperty().bind(widthProperty().divide(2).subtract(emptyText.widthProperty().divide(2)));
        emptyText.layoutYProperty().bind(heightProperty().divide(2).subtract(emptyText.heightProperty().divide(2)));

        getChildren().add(emptyText);
    }

    // Dibuja el heap completo desde la lista
    public void drawHeap(List<Integer> data) {
        heapData = data;
        clearGraph();

        // Si no hay datos, muestro mensaje
        if (data == null || data.isEmpty()) {
            getChildren().add(emptyText);
            return;
        }

        double paneWidth = getWidth() > 0 ? getWidth() : getPrefWidth();

        // Centro inicial
        double centerX = paneWidth / 2;
        double initialGap = Math.min(160, paneWidth / 4);

        // Empiezo desde índice 0 (raíz)
        drawNode(0, centerX, 70, initialGap);
    }

    // Dibuja cada nodo usando índices del array (heap)
    private void drawNode(int index, double x, double y, double gap) {
        if (heapData == null || index >= heapData.size()) return;

        int value = heapData.get(index);

        // Calculo índices de hijos
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        double nextGap = Math.max(gap / 1.4, 55);

        // Hijo izquierdo
        if (leftIndex < heapData.size()) {
            double childX = x - gap;
            double childY = y + 85;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(leftIndex, childX, childY, nextGap);
        }

        // Hijo derecho
        if (rightIndex < heapData.size()) {
            double childX = x + gap;
            double childY = y + 85;

            addShape(new EdgeElement(x, y, childX, childY));
            drawNode(rightIndex, childX, childY, nextGap);
        }

        // Dibujo nodo actual encima de las líneas
        addShape(createNode(x, y, value, index == 0));
    }

    // Define estilo visual del nodo
    private NodeElement createNode(double x, double y, int value, boolean isRoot) {

        String text = String.valueOf(value);

        // Raíz (máximo o mínimo dependiendo heap)
        if (isRoot) {
            return new NodeElement(
                    x, y, text,
                    "#0f172a",   // fondo oscuro
                    "#ffffff",
                    "#ffffff"
            );
        }

        // Nodos normales
        return new NodeElement(
                x, y, text,
                "#2563eb",   // azul
                "#ffffff",
                "#ffffff"
        );
    }

    // Limpia completamente el visualizador
    public void clear() {
        heapData = null;
        clearGraph();
        getChildren().add(emptyText);
    }
}