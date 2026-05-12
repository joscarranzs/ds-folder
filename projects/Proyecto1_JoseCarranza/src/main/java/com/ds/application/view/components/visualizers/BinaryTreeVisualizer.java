package com.ds.application.view.components.visualizers;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.view.components.elements.shapes.EdgeElement;
import com.ds.application.view.components.elements.shapes.GraphPane;
import com.ds.application.view.components.elements.shapes.NodeElement;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BinaryTreeVisualizer extends GraphPane {

    // Texto que aparece cuando todavía no hay árbol dibujado
    private Label emptyText;

    // Nodo seleccionado final, queda pintado en verde
    private Integer selectedValue;

    // Nodo que se está animando durante recorrido o búsqueda, queda amarillo
    private Integer animatedValue;

    // Indica que la animación activa es un recorrido (pre/in/post). Cuando es true
    // usamos color morado para la animación y el estado final. Se limpia al terminar.
    private boolean traversalAnimationMode = false;

    // Indica que la selección actual fue hecha por el usuario (click). Si es true,
    // el nodo seleccionado se muestra en morado en lugar de verde.
    private boolean selectedIsUser = false;

    // Guardo la raíz actual para poder redibujar el árbol cuando sea necesario
    private BinaryTreeNode currentRoot;

    // Evento que se ejecuta cuando el usuario hace click sobre un nodo
    private Consumer<Integer> onNodeSelected;

    // Timeline que uso para animar recorridos o búsquedas
    private Timeline traversalTimeline;

    // Pause que limpia la selección después de mostrar el nodo encontrado
    private PauseTransition selectionClearPause;

    // Valor de zoom actual del visualizador
    private double zoomLevel = 1.0;

    public BinaryTreeVisualizer() {
        super();
        
        //BORDE DE EL CUADRO CENTRAL DE VISUALIZACIÓN
        setStyle(
        "-fx-background-color: #F7FBF6;" +
        "-fx-border-color: #bfcfbb;" +
        "-fx-border-width: 2;" +
        "-fx-border-radius: 12;" +
        "-fx-background-radius: 12;"
        );

        // Esto evita que los nodos o líneas se dibujen encima de otros paneles
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(widthProperty());
        clip.heightProperty().bind(heightProperty());
        setClip(clip);

        widthProperty().addListener((obs, oldVal, newVal) -> requestLayoutUpdate());
        heightProperty().addListener((obs, oldVal, newVal) -> requestLayoutUpdate());

        // Mensaje inicial del área de visualización
        emptyText = new Label("Área de visualización del árbol");

        // Estilo visual mejorado
        emptyText.setStyle(
        "-fx-font-size: 16px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: #94a3b8;");

        // Centrado automático (CLAVE)
        emptyText.layoutXProperty().bind(widthProperty().divide(2).subtract(emptyText.widthProperty().divide(2)));
        emptyText.layoutYProperty().bind(heightProperty().divide(2).subtract(emptyText.heightProperty().divide(2)));
        getChildren().add(emptyText);
    }

    private void requestLayoutUpdate() {
        if (currentRoot == null) {
            return;
        }

        drawTree(currentRoot);
    }

    // Dibuja el árbol completo desde la raíz
    public void drawTree(BinaryTreeNode root) {
        currentRoot = root;
        clearGraph();

        if (root == null) {
            getChildren().add(emptyText);
            return;
        }

        Map<BinaryTreeNode, Point2D> positions = calculatePositions(root);

        drawEdges(root, positions);
        drawNodes(root, positions, true);
    }

    // Calcula posiciones usando recorrido in-orden para evitar cruces visuales
    private Map<BinaryTreeNode, Point2D> calculatePositions(BinaryTreeNode root) {
        Map<BinaryTreeNode, Point2D> positions = new IdentityHashMap<>();
        List<BinaryTreeNode> orderedNodes = new ArrayList<>();

        collectInOrder(root, orderedNodes);

        double paneWidth = getWidth() > 0 ? getWidth() : getPrefWidth();
        double paneHeight = getHeight() > 0 ? getHeight() : getPrefHeight();

        // Separación horizontal controlada por zoom, sin escalar el panel completo
        double horizontalSpacing = 90 * zoomLevel;
        double verticalSpacing = 90 * zoomLevel;

        double totalWidth = (orderedNodes.size() - 1) * horizontalSpacing;
        double startX = (paneWidth / 2) - (totalWidth / 2);

        for (int i = 0; i < orderedNodes.size(); i++) {
            BinaryTreeNode node = orderedNodes.get(i);
            int level = getNodeLevel(root, node.getValue(), 1);

            double x = startX + (i * horizontalSpacing);
            double y = 70 + ((level - 1) * verticalSpacing);

            positions.put(node, new Point2D(x, y));
        }

        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = 0;

        for (Point2D point : positions.values()) {
            minX = Math.min(minX, point.getX());
            maxX = Math.max(maxX, point.getX());
            maxY = Math.max(maxY, point.getY());
        }

        if (!positions.isEmpty()) {
            double leftMargin = 45;
            double rightMargin = 45;
            double topMargin = 50;
            double bottomMargin = 60;

            double offsetX = 0;

            if (minX < leftMargin) {
                offsetX += leftMargin - minX;
            }

            if (maxX + offsetX > paneWidth - rightMargin) {
                offsetX -= (maxX + offsetX) - (paneWidth - rightMargin);
            }

            double availableHeight = paneHeight - topMargin - bottomMargin;
            double heightOffset = 0;

            if (maxY > 0 && maxY > availableHeight) {
                heightOffset = topMargin - 70;
            }

            if (offsetX != 0 || heightOffset != 0) {
                for (Map.Entry<BinaryTreeNode, Point2D> entry : positions.entrySet()) {
                    Point2D point = entry.getValue();
                    entry.setValue(new Point2D(point.getX() + offsetX, point.getY() + heightOffset));
                }
            }
        }

        return positions;
    }

    // Recolecta los nodos en in-orden para que las ramas no se crucen
    private void collectInOrder(BinaryTreeNode node, List<BinaryTreeNode> nodes) {
        if (node == null) {
            return;
        }

        collectInOrder(node.getLeft(), nodes);
        nodes.add(node);
        collectInOrder(node.getRight(), nodes);
    }

    // Calcula el nivel de un nodo dentro del árbol
    private int getNodeLevel(BinaryTreeNode node, int value, int level) {
        if (node == null) {
            return level;
        }

        int currentValue = ((Number) node.getValue()).intValue();

        if (currentValue == value) {
            return level;
        }

        if (value < currentValue) {
            return getNodeLevel(node.getLeft(), value, level + 1);
        }

        return getNodeLevel(node.getRight(), value, level + 1);
    }

    // Dibuja primero las líneas para que queden detrás de los nodos
    private void drawEdges(BinaryTreeNode node, Map<BinaryTreeNode, Point2D> positions) {
        if (node == null) {
            return;
        }

        Point2D current = positions.get(node);

        if (node.getLeft() != null) {
            Point2D left = positions.get(node.getLeft());
            addShape(new EdgeElement(current.getX(), current.getY(), left.getX(), left.getY()));
            drawEdges(node.getLeft(), positions);
        }

        if (node.getRight() != null) {
            Point2D right = positions.get(node.getRight());
            addShape(new EdgeElement(current.getX(), current.getY(), right.getX(), right.getY()));
            drawEdges(node.getRight(), positions);
        }
    }

    // Dibuja los nodos después de las líneas
    private void drawNodes(BinaryTreeNode node, Map<BinaryTreeNode, Point2D> positions, boolean isRoot) {
        if (node == null) {
            return;
        }

        drawNodes(node.getLeft(), positions, false);
        drawNodes(node.getRight(), positions, false);

        Point2D point = positions.get(node);
        addShape(createNode(node, point.getX(), point.getY(), isRoot));
    }

    // Hace zoom in redibujando el árbol sin escalar el panel completo
    public void zoomIn() {
        zoomLevel += 0.1;

        if (zoomLevel > 1.6) {
            zoomLevel = 1.6;
        }

        drawTree(currentRoot);
    }

    // Hace zoom out redibujando el árbol sin escalar el panel completo
    public void zoomOut() {
        zoomLevel -= 0.1;

        if (zoomLevel < 0.6) {
            zoomLevel = 0.6;
        }

        drawTree(currentRoot);
    }

    // Centra la vista y reinicia el zoom
    public void centerView() {
        zoomLevel = 1.0;
        drawTree(currentRoot);
    }

    // Marca un nodo seleccionado y lo deja en verde
    public void highlightValue(int value) {
        // Cuando el usuario selecciona un nodo, marcamos que la selección es de usuario
        selectedValue = value;
        animatedValue = null;
        selectedIsUser = true;

        // Si había una pausa programada para limpiar una selección anterior, cancelarla
        if (selectionClearPause != null) {
            selectionClearPause.stop();
            selectionClearPause = null;
        }

        drawTree(currentRoot);
    }

    // Limpia cualquier selección o animación
    public void clearHighlight() {
        selectedValue = null;
        animatedValue = null;
        selectedIsUser = false;

        if (selectionClearPause != null) {
            selectionClearPause.stop();
            selectionClearPause = null;
        }

        drawTree(currentRoot);
    }

    // Anima un recorrido resaltando los nodos uno por uno
    public void animateTraversal(List<Integer> values) {
        // Activar modo recorrido para que la animación use color morado
        traversalAnimationMode = true;
        animateValues(values, 0.7);
    }

    // Anima la búsqueda desde la raíz hasta el nodo encontrado
    public boolean animateSearchPath(int targetValue) {
        List<Integer> path = new ArrayList<>();

        boolean found = findPath(currentRoot, targetValue, path);

        if (!found) {
            return false;
        }

        // Asegurarse que el modo recorrido esté desactivado para búsquedas
        traversalAnimationMode = false;
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

        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        // Si había una pausa pendiente para limpiar selección, cancelarla porque
        // vamos a iniciar una nueva animación
        if (selectionClearPause != null) {
            selectionClearPause.stop();
            selectionClearPause = null;
        }

        selectedValue = null;
        animatedValue = null;

        traversalTimeline = new Timeline();

        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);

            // Si es el último elemento y estamos en modo de búsqueda (no traversal),
            // marcarlo directamente como seleccionado (verde) en lugar de animarlo amarillo.
            if (i == values.size() - 1 && !traversalAnimationMode) {
                KeyFrame frame = new KeyFrame(
                        Duration.seconds(i * seconds),
                        e -> {
                            animatedValue = null;
                            selectedValue = value;
                            drawTree(currentRoot);

                            // Programar limpieza automática después de 5 segundos
                            selectionClearPause = new PauseTransition(Duration.seconds(5));
                            selectionClearPause.setOnFinished(ev -> {
                                selectedValue = null;
                                drawTree(currentRoot);
                                selectionClearPause = null;
                            });
                            selectionClearPause.play();
                        }
                );

                traversalTimeline.getKeyFrames().add(frame);
                continue;
            }

            KeyFrame frame = new KeyFrame(
                    Duration.seconds(i * seconds),
                    e -> {
                        animatedValue = value;
                        drawTree(currentRoot);
                    }
            );

            traversalTimeline.getKeyFrames().add(frame);
        }

        int lastValue = values.get(values.size() - 1);

        KeyFrame finalFrame = new KeyFrame(
                Duration.seconds(values.size() * seconds),
                e -> {
                    animatedValue = null;
                    // Mantener traversalAnimationMode activo hasta dibujar el estado final
                    selectedValue = lastValue;
                    drawTree(currentRoot);
                    // Luego limpiar el modo de recorrido para futuras animaciones
                    traversalAnimationMode = false;
                    // Programar limpieza automática después de 5 segundos para
                    // que el último nodo vuelva a su color original (igual que en
                    // la animación de búsqueda).
                    selectionClearPause = new PauseTransition(Duration.seconds(5));
                    selectionClearPause.setOnFinished(ev -> {
                        selectedValue = null;
                        drawTree(currentRoot);
                        selectionClearPause = null;
                    });
                    selectionClearPause.play();
                }
        );

        traversalTimeline.getKeyFrames().add(finalFrame);
        traversalTimeline.play();
    }

    // Permite que otro componente reciba el valor del nodo seleccionado
    public void setOnNodeSelected(Consumer<Integer> onNodeSelected) {
        this.onNodeSelected = onNodeSelected;
    }

    // Limpia completamente el visualizador
    public void clear() {
        currentRoot = null;
        selectedValue = null;
        animatedValue = null;
        zoomLevel = 1.0;

        if (traversalTimeline != null) {
            traversalTimeline.stop();
        }

        clearGraph();
        getChildren().add(emptyText);
    }

    // Define cómo se ve cada nodo según su estado
    private NodeElement createNode(BinaryTreeNode node, double x, double y, boolean isRoot) {
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;
        boolean isAnimated = animatedValue != null && animatedValue.equals(node.getValue());
        boolean isSelected = selectedValue != null && selectedValue.equals(node.getValue());

        String text = String.valueOf(node.getValue());
        int value = ((Number) node.getValue()).intValue();

        // Si estamos en modo recorrido (pre/in/post), usar morado para la animación
        if (isAnimated) {
            if (traversalAnimationMode) {
                // Relleno morado, borde más oscuro, texto claro
                return createClickableNode(x, y, text, "#8b5cf6", "#6d28d9", "#ffffff", value);
            }

            // Animación estándar (búsqueda): amarillo
            return createClickableNode(x, y, text, "#facc15", "#eab308", "#111827", value);
        }

        // Si el nodo es el seleccionado final
        if (isSelected) {
            // Si estamos en modo recorrido, usar morado también para el seleccionado
            if (traversalAnimationMode) {
                return createClickableNode(x, y, text, "#7c3aed", "#5b21b6", "#ffffff", value);
            }

            // Si la selección fue hecha por el usuario, mostrar morado para evitar confundir
            // con el color de 'encontrado'. De lo contrario, mostrar verde para resultado.
            if (selectedIsUser) {
                return createClickableNode(x, y, text, "#7c3aed", "#5b21b6", "#ffffff", value);
            }

            // Selección estándar (resultado de búsqueda): verde
            return createClickableNode(x, y, text, "#22c55e", "#16a34a", "#ffffff", value);
        }

        if (isRoot) {
            return createClickableNode(x, y, text, "#0f172a", "#ffffff", "#ffffff", value);
        }

        if (isLeaf) {
            return createClickableNode(x, y, text, "#ffffff", "#2563eb", "#111827", value);
        }

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

        nodeElement.setOnMouseClicked(e -> {
            if (onNodeSelected != null) {
                onNodeSelected.accept(value);
            }
        });

        return nodeElement;
    }
}
