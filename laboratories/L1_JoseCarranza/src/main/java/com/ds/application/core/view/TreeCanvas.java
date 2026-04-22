package com.ds.application.core.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Consumer;

import com.ds.application.core.model.TreeNode;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Responsable de renderizar el árbol binario en múltiples representaciones visuales.
 *
 * <p>El canvas soporta vista de árbol jerárquica, vista lineal en estilo lista enlazada
 * y una representación por niveles, mientras gestiona las animaciones de selección de nodo.</p>
 */
public class TreeCanvas {

    private final Pane treeCanvas = new Pane();
    private final Label emptyLabel = new Label(DEFAULT_EMPTY_TEXT);
    private final Map<TreeNode, StackPane> nodeMap = new HashMap<>();
    private boolean treeViewActive = true;
    private Consumer<TreeNode> nodeClickHandler;

    public static final String DEFAULT_EMPTY_TEXT = "Empty tree · Insert a root node to begin";

    public TreeCanvas() {
        treeCanvas.getStyleClass().add("tree-canvas");
        emptyLabel.getStyleClass().add("empty-label");
        bindCenterLabel();
        treeCanvas.getChildren().add(emptyLabel);
    }

    /**
     * Establece el callback que se invoca cuando se hace clic en un nodo del canvas.
     *
     * @param nodeClickHandler el callback que recibe el nodo clicado
     */
    public void setNodeClickHandler(java.util.function.Consumer<TreeNode> nodeClickHandler) {
        this.nodeClickHandler = nodeClickHandler;
    }

    /**
     * Devuelve el contenedor de vista JavaFX que aloja el canvas de dibujo del árbol.
     *
     * @return el nodo padre que contiene la vista de canvas desplazable
     */
    public Parent getView() {
        ScrollPane scroll = new ScrollPane(treeCanvas);
        scroll.getStyleClass().add("tree-scroll");
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(false);

        StackPane center = new StackPane(scroll);
        center.getStyleClass().add("center-area");
        return center;
    }

    /**
     * Borra el canvas actual y muestra el marcador de árbol vacío.
     */
    public void resetView() {
        clearCanvas();
        showEmptyState();
        setRepresentationActive(true);
    }

    /**
     * Elimina todos los nodos y líneas dibujables del canvas.
     */
    public void clearCanvas() {
        treeCanvas.getChildren().clear();
        nodeMap.clear();
    }

    /**
     * Muestra el mensaje predeterminado de árbol vacío cuando no hay nodos.
     */
    public void showEmptyState() {
        showEmptyState(DEFAULT_EMPTY_TEXT);
    }

    /**
     * Muestra un mensaje de marcador de posición personalizado cuando el árbol está vacío.
     *
     * @param message el mensaje mostrado en el estado vacío
     */
    public void showEmptyState(String message) {
        emptyLabel.setText(message);
        emptyLabel.setVisible(true);
        treeCanvas.getChildren().setAll(emptyLabel);
    }

    /**
     * Oculta el marcador de estado vacío para que el canvas muestre contenido real del árbol.
     */
    public void hideEmptyState() {
        emptyLabel.setVisible(false);
    }

    /**
     * Ajusta el modo activo de representación entre árbol y lista enlazada.
     *
     * @param treeView true para representación de árbol, false para lista enlazada
     */
    public void setRepresentationActive(boolean treeView) {
        treeViewActive = treeView;
    }

    /**
     * Activa el modo de vista por niveles.
     */
    public void setLevelRepresentationActive() {
        treeViewActive = true;
    }

    /**
     * Draws the tree using the currently active representation mode.
     *
     * @param root the root node of the tree to render
     */
    public void drawCurrentRepresentation(TreeNode root) {
        if (treeViewActive) {
            drawTree(root);
        } else {
            drawLinkedList(root);
        }
    }

    /**
     * Dibuja la disposición jerárquica del árbol binario en el canvas.
     *
     * @param root la raíz del árbol a renderizar
     */
    public void drawTree(TreeNode root) {
        clearCanvas();

        if (root == null) {
            showEmptyState();
            return;
        }

        hideEmptyState();
        double width = Math.max(treeCanvas.getWidth(), 900);
        int depth = treeDepth(root);
        double canvasHeight = 70 + (depth - 1) * 100 + 58 + 40;

        treeCanvas.setPrefWidth(width);
        treeCanvas.setPrefHeight(Math.max(canvasHeight, 620));

        drawNode(root, width / 2, 70, width / 4, 100, true);
    }

    private int treeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(node.getLeft()), treeDepth(node.getRight()));
    }

    /**
     * Dibuja los nodos del árbol en una representación lineal en estilo lista enlazada.
     *
     * @param root la raíz del árbol a renderizar
     */
    public void drawLinkedList(TreeNode root) {
        clearCanvas();

        if (root == null) {
            showEmptyState();
            return;
        }

        hideEmptyState();
        List<TreeNode> nodes = new ArrayList<>();
        collectPreOrder(root, nodes);

        double canvasWidth = Math.max(treeCanvas.getWidth(), 920);
        double rowWidth = Math.min(820, canvasWidth - 80);
        double rowHeight = 78;
        double startX = 40;
        double startY = 40;

        for (int i = 0; i < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            HBox row = createLinkedListRow(node, nodes, rowWidth, rowHeight, i + 1);
            row.setLayoutX(startX);
            row.setLayoutY(startY + i * (rowHeight + 18));
            treeCanvas.getChildren().add(row);
        }

        treeCanvas.setPrefWidth(Math.max(rowWidth + 80, treeCanvas.getWidth()));
        treeCanvas.setPrefHeight(startY + nodes.size() * (rowHeight + 18) + 40);
    }

    /**
     * Dibuja los nodos del árbol agrupados por nivel en filas horizontales.
     *
     * @param root la raíz del árbol a renderizar
     */
    public void drawNodesByLevel(TreeNode root) {
        clearCanvas();

        if (root == null) {
            showEmptyState();
            return;
        }

        List<List<TreeNode>> levels = collectNodesByLevel(root);
        if (levels.isEmpty()) {
            showEmptyState("No nodes found in the tree");
            return;
        }

        hideEmptyState();
        double startX = 40;
        double startY = 40;
        double rowSpacing = 90;
        double nodeDiameter = 52;

        for (int i = 0; i < levels.size(); i++) {
            List<TreeNode> levelNodes = levels.get(i);
            HBox row = new HBox(20);
            row.getStyleClass().add("level-row");
            row.setAlignment(Pos.CENTER_LEFT);

            Label levelLabel = new Label("Level " + (i + 1));
            levelLabel.getStyleClass().add("level-row-label");
            row.getChildren().add(levelLabel);

            HBox nodesBox = new HBox(10);
            nodesBox.setAlignment(Pos.CENTER_LEFT);

            for (TreeNode node : levelNodes) {
                nodesBox.getChildren().add(createLevelNode(node, nodeDiameter));
            }

            row.getChildren().add(nodesBox);
            row.setLayoutX(startX);
            row.setLayoutY(startY + i * rowSpacing);
            treeCanvas.getChildren().add(row);
        }

        treeCanvas.setPrefWidth(Math.max(900, startX + 760));
        treeCanvas.setPrefHeight(startY + levels.size() * rowSpacing + 40);
    }

    private List<List<TreeNode>> collectNodesByLevel(TreeNode root) {
        List<List<TreeNode>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                currentLevel.add(node);
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            levels.add(currentLevel);
        }

        return levels;
    }

    private StackPane createLevelNode(TreeNode node, double size) {
        boolean leaf = node.isLeaf();
        Circle circle = new Circle(size / 2);
        circle.getStyleClass().add("level-node-circle");
        circle.setFill(leaf ? Color.web("#e2e8f0") : Color.WHITE);
        circle.setStroke(Color.web("#cbd5e1"));
        circle.setStrokeWidth(1.8);

        Text text = new Text(String.valueOf(node.getValue()));
        text.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        text.getStyleClass().add("level-node-text");

        StackPane nodeView = new StackPane(circle, text);
        nodeView.setPrefSize(size, size);
        nodeView.setMinSize(size, size);
        nodeView.setMaxSize(size, size);
        nodeView.setOnMouseClicked(e -> {
            if (nodeClickHandler != null) {
                nodeClickHandler.accept(node);
            }
        });
        nodeView.setStyle("-fx-cursor: hand;");
        return nodeView;
    }

    private void collectPreOrder(TreeNode node, List<TreeNode> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node);
        collectPreOrder(node.getLeft(), nodes);
        collectPreOrder(node.getRight(), nodes);
    }

    private HBox createLinkedListRow(TreeNode node, List<TreeNode> allNodes, double width, double height, int index) {
        HBox row = new HBox();
        row.setSpacing(0);
        row.setPrefSize(width, height);
        row.getStyleClass().add("linked-row");
        row.setCursor(javafx.scene.Cursor.HAND);
        row.setOnMouseClicked(e -> {
            if (nodeClickHandler != null) {
                nodeClickHandler.accept(node);
            }
        });

        double indexWidth = 56;
        double cellWidth = (width - indexWidth) / 3;

        VBox indexCell = createIndexCell(index, true);
        VBox leftCell = createLinkedListCell("LEFT", node.getLeft(), true, allNodes);
        VBox infoCell = createLinkedListCell("INFO", node, true, allNodes);
        VBox rightCell = createLinkedListCell("RIGHT", node.getRight(), false, allNodes);

        HBox.setHgrow(indexCell, Priority.NEVER);
        HBox.setHgrow(leftCell, Priority.ALWAYS);
        HBox.setHgrow(infoCell, Priority.ALWAYS);
        HBox.setHgrow(rightCell, Priority.ALWAYS);

        indexCell.setPrefWidth(indexWidth);
        leftCell.setPrefWidth(cellWidth);
        infoCell.setPrefWidth(cellWidth);
        rightCell.setPrefWidth(cellWidth);

        row.getChildren().addAll(indexCell, leftCell, infoCell, rightCell);
        return row;
    }

    private VBox createIndexCell(int index, boolean withDivider) {
        VBox cell = new VBox(6);
        cell.getStyleClass().add("linked-index-cell");
        cell.setPadding(new Insets(10, 10, 10, 10));
        if (withDivider) {
            cell.setStyle("-fx-border-color: transparent #e2e8f0 transparent transparent; -fx-border-width: 0 1 0 0;");
        }

        Label label = new Label("#");
        label.getStyleClass().add("linked-index-label");
        Label value = new Label(String.valueOf(index));
        value.getStyleClass().add("linked-index-value");

        cell.getChildren().addAll(label, value);
        return cell;
    }

    private VBox createLinkedListCell(String labelText, TreeNode node, boolean withDivider, List<TreeNode> allNodes) {
        VBox cell = new VBox(6);
        cell.getStyleClass().add("linked-cell");
        cell.setPadding(new Insets(10, 14, 10, 14));
        if (withDivider) {
            cell.setStyle("-fx-border-color: transparent #e2e8f0 transparent transparent; -fx-border-width: 0 1 0 0;");
        }

        Label label = new Label(labelText);
        label.getStyleClass().add("linked-cell-label");

        Label content;
        if ("INFO".equals(labelText)) {
            content = new Label(String.valueOf(node.getValue()));
            content.getStyleClass().add("linked-cell-info");
        } else {
            String pointer = node != null
                    ? String.valueOf(allNodes.indexOf(node) + 1)
                    : "null";
            content = new Label(pointer);
            content.getStyleClass().add("linked-cell-pointer");
        }

        cell.getChildren().addAll(label, content);
        return cell;
    }

    private void drawNode(TreeNode node, double x, double y, double offset, double gapY, boolean rootNode) {
        if (node == null) {
            return;
        }

        boolean leaf = node.isLeaf();
        double size = 58;

        if (node.getLeft() != null) {
            treeCanvas.getChildren().add(drawLine(x, y + size / 2, x - offset, y + gapY - size / 2));
            drawNode(node.getLeft(), x - offset, y + gapY, offset / 2, gapY, false);
        }

        if (node.getRight() != null) {
            treeCanvas.getChildren().add(drawLine(x, y + size / 2, x + offset, y + gapY - size / 2));
            drawNode(node.getRight(), x + offset, y + gapY, offset / 2, gapY, false);
        }

        if (rootNode) {
            Label rootTag = new Label("ROOT");
            rootTag.getStyleClass().add("node-tag-root");
            rootTag.setLayoutX(x + size / 2 + 8);
            rootTag.setLayoutY(y - 10);
            rootTag.setStyle("-fx-background-color: rgba(255,255,255,0.9); -fx-padding: 2 6 2 6; -fx-background-radius: 8;");
            treeCanvas.getChildren().add(rootTag);
        }

        if (leaf && !rootNode) {
            Label leafTag = new Label("LEAF");
            leafTag.getStyleClass().add("node-tag-leaf");
            leafTag.setLayoutX(x - size / 2);
            leafTag.setLayoutY(y + size + 4);
            treeCanvas.getChildren().add(leafTag);
        }

        StackPane nodeView = new StackPane();
        nodeMap.put(node, nodeView);
        nodeView.setPrefSize(size, size);
        nodeView.setLayoutX(x - size / 2);
        nodeView.setLayoutY(y - size / 2);
        nodeView.getStyleClass().add(rootNode ? "node-root" : leaf ? "node-leaf" : "node-internal");

        Text text = new Text(String.valueOf(node.getValue()));
        text.setFont(Font.font("Segoe UI", FontWeight.BOLD, rootNode ? 18 : 16));
        text.setFill(rootNode ? Color.WHITE : leaf ? Color.web("#475569") : Color.web("#0f172a"));

        nodeView.getChildren().add(text);
        nodeView.setOnMouseClicked(e -> {
            if (nodeClickHandler != null) {
                nodeClickHandler.accept(node);
            }
        });
        nodeView.setStyle("-fx-cursor: hand;");

        treeCanvas.getChildren().add(nodeView);
    }

    /**
     * Anima los nodos visitados durante una operación de búsqueda, marcando el resultado final.
     *
     * @param path el camino de búsqueda a animar
     * @param found si el valor buscado fue encontrado
     */
    public void animateSearch(List<TreeNode> path, boolean found) {
        if (path == null || path.isEmpty()) {
            return;
        }

        String missClass = "search-miss";
        String hitClass = "search-hit";

        new Thread(() -> {
            try {
                for (int i = 0; i < path.size(); i++) {
                    TreeNode node = path.get(i);
                    Node visualNode = nodeMap.get(node);
                    boolean isLast = i == path.size() - 1;
                    boolean isHit = found && isLast;

                    if (visualNode != null) {
                        javafx.application.Platform.runLater(() -> {
                            visualNode.getStyleClass().removeAll(hitClass, missClass);
                            visualNode.getStyleClass().add(isHit ? hitClass : missClass);
                        });
                    }

                    Thread.sleep(500);

                    if (visualNode != null && !isHit) {
                        javafx.application.Platform.runLater(() -> {
                            visualNode.getStyleClass().remove(missClass);
                        });
                    }
                }

                if (!path.isEmpty()) {
                    Thread.sleep(1000);
                    javafx.application.Platform.runLater(() -> {
                        for (TreeNode node : path) {
                            Node visualNode = nodeMap.get(node);
                            if (visualNode != null) {
                                visualNode.getStyleClass().removeAll(hitClass, missClass);
                            }
                        }
                    });
                }
            } catch (InterruptedException ignored) {
            }
        }).start();
    }

    private Line drawLine(double x1, double y1, double x2, double y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.web("#cbd5e1"));
        line.setStrokeWidth(1.8);
        return line;
    }

    private void bindCenterLabel() {
        emptyLabel.layoutXProperty().bind(
            treeCanvas.widthProperty().subtract(emptyLabel.widthProperty()).divide(2)
        );
        emptyLabel.layoutYProperty().bind(
            treeCanvas.heightProperty().subtract(emptyLabel.heightProperty()).divide(2)
        );
    }
}
