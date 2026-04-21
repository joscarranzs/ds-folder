package com.ds.application.core.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.ds.application.core.controller.TreeController;
import com.ds.application.core.model.TreeNode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Interfaz gráfica principal del visualizador de árbol binario.
 *
 * <p>Esta clase construye la ventana JavaFX, organiza los paneles de acción,
 * muestra el árbol en un lienzo y actualiza el panel de inspección y las métricas.
 * La interacción de usuario se delega al {@link TreeController}.</p>
 */
public class BinaryTree extends Application {

    private TreeController controller;

    private Label lblKeyValue;
    private Label lblDegree;
    private Label lblLevel;
    private Label lblChildNodes;

    private Pane treeCanvas;
    private Label emptyLabel;

    private Label lblDepthBar;
    private Label lblLCIBar;
    private Label lblLCIMBar;
    private Label lblParentsBar;
    private Label lblLeavesBar;

    private Button btnTabTree;
    private Button btnTabList;
    private Button btnTabLevel;
    private boolean treeViewActive = true;

    private static final String DEFAULT_EMPTY_TEXT = "Empty tree · Insert a root node to begin";

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");

        root.setTop(buildTopBar());
        root.setLeft(buildSidebar());
        root.setCenter(buildCenter());
        root.setRight(buildInspectorPanel());
        root.setBottom(buildBottomBar());

        Scene scene = new Scene(root, 1280, 820);
        scene.getStylesheets().add(
                getClass()
                        .getResource("/com/ds/application/core/view/Styles.css")
                        .toExternalForm()
        );

        stage.setTitle("Binary Tree Visualizer");
        stage.setMinWidth(1000);
        stage.setMinHeight(680);
        stage.setScene(scene);

        controller = new TreeController(this);
        stage.show();
    }

    /**
     * Construye la barra superior de la ventana con el título principal.
     *
     * @return contenedor horizontal con el encabezado de la aplicación
     */
    private HBox buildTopBar() {
        HBox bar = new HBox();
        bar.getStyleClass().add("top-bar");
        bar.setAlignment(Pos.CENTER);
        bar.setPadding(new Insets(16, 32, 16, 32));

        Label title = new Label("Binary Tree Visualizer");
        title.getStyleClass().add("top-title");

        bar.getChildren().add(title);
        return bar;
    }
    /*
    Animacion de busqueda: El controlador debe implementar un método handleSearch() que 
    solicite un valor al usuario, busque el nodo en el árbol, 
    y llame a view.animateSearch(path, found) 
    donde path es la lista de nodos recorridos y found es un booleano que indica si se encontró el nodo. 
    La vista debe resaltar cada nodo en path secuencialmente (por ejemplo, cambiando su color) y 
    luego mostrar un mensaje si el nodo no se encuentra.
    */
   private Map<TreeNode, StackPane>  nodeMap = new HashMap<>();
public void animateSearch(List<TreeNode> path, boolean found) {
    if (path == null || path.isEmpty()) return;

    Color highlightColor = found ? Color.GREEN : Color.RED;

    new Thread(() -> {
        try {
            // resaltar recorrido
            for (TreeNode node : path) {
                Node visualNode = nodeMap.get(node);

                if (visualNode != null) {
                    javafx.application.Platform.runLater(() -> {
                        visualNode.setStyle(
                            "-fx-background-color: " + toRgbString(highlightColor) + ";" +
                            "-fx-background-radius: 50;"
                        );
                    });
                }

                Thread.sleep(400); // velocidad del recorrido
            }

            // mantener color 5 segundos
            Thread.sleep(5000);

            // quitar color (reset visual)
            for (TreeNode node : path) {
                Node visualNode = nodeMap.get(node);

                if (visualNode != null) {
                    javafx.application.Platform.runLater(() -> {
                        visualNode.setStyle(""); 
                    });
                }
            }

        } catch (InterruptedException ignored) {}
    }).start();
}
/*
Se agrego colores para la animacion del recorrido */
private String toRgbString(Color color) {
    return String.format("rgb(%d,%d,%d)", 
        (int)(color.getRed()*255), 
        (int)(color.getGreen()*255), 
        (int)(color.getBlue()*255));
}
    /**
     * Construye el panel lateral izquierdo con acciones, operaciones y opciones de vista.
     *
     * @return contenedor vertical que agrupa botones y secciones de navegación
     */
    private VBox buildSidebar() {
        VBox sidebar = new VBox(0);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(260);

        VBox logo = new VBox(2);
        logo.setPadding(new Insets(20, 20, 16, 20));

        Label logoTitle = new Label("TREE VISUALIZER");
        logoTitle.getStyleClass().add("logo-title");

        logo.getChildren().add(logoTitle);
        sidebar.getChildren().add(logo);
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("ACTIONS"));
        sidebar.getChildren().add(primaryButton("Insert", "+", e -> controller.handleInsertNode()));
        // TODO [Controller]: Implementar handleSearchNode() en TreeController
        // Debe pedir un valor al usuario (similar a handleInsertNode), buscar el nodo en el árbol,
        // resaltar el recorrido hasta encontrarlo, y llamar showNodeNotFound(value) si no existe
        Button btnSearch = secondaryButton("Search", "⌕", null);
        //btnSearch.setOnAction(e -> controller.handleSearchNode());
        btnSearch.setOnAction(e -> controller.handleSearch());
        sidebar.getChildren().add(btnSearch);
        sidebar.getChildren().add(secondaryButton("Reset", "↺", e -> controller.handleNewTree()));
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("REPRESENTATIONS"));
        btnTabTree = activeButton("Binary Tree", "⛶", e -> controller.handleSequential());
        btnTabList = secondaryButton("Linked List Table", "▦", e -> controller.handleLinkedList());
        btnTabLevel = secondaryButton("Nodes by Level", "⌕", e -> controller.handleNodesByLevel());

        sidebar.getChildren().addAll(btnTabTree, btnTabList, btnTabLevel);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        sidebar.getChildren().add(filler);

        return sidebar;
    }

    /**
     * Construye el área central donde se dibuja el árbol binario.
     *
     * @return contenedor que incluye el lienzo y el scroll vertical/horizontal
     */
    private StackPane buildCenter() {
        treeCanvas = new Pane();
        treeCanvas.getStyleClass().add("tree-canvas");

        emptyLabel = new Label("Empty tree · Insert a root node to begin");
        emptyLabel.getStyleClass().add("empty-label");
        bindCenterLabel();

        treeCanvas.getChildren().add(emptyLabel);
        ScrollPane scroll = new ScrollPane(treeCanvas);
        scroll.getStyleClass().add("tree-scroll");
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(false);

        StackPane center = new StackPane(scroll);
        center.getStyleClass().add("center-area");
        return center;
    }

    /**
     * Construye el panel inspector derecho con detalles del nodo seleccionado.
     *
     * @return contenedor vertical con indicadores de valor, grado, nivel y hijos
     */
    private VBox buildInspectorPanel() {
        VBox panel = new VBox(0);
        panel.getStyleClass().add("right-panel");
        panel.setPrefWidth(300);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(20, 20, 16, 20));

        Label title = new Label("NODE INSPECTOR");
        title.getStyleClass().add("inspector-title");

        Label info = new Label("ℹ");
        info.getStyleClass().add("info-icon");
        header.getChildren().addAll(title, info);

        panel.getChildren().addAll(header, createDivider());

        lblKeyValue = inspectorValueLabel("—");
        lblDegree = inspectorValueLabel("—");
        lblLevel = inspectorValueLabel("—");
        lblChildNodes = inspectorChipLabel("—");

        panel.getChildren().add(inspectorRow("KEY VALUE", lblKeyValue));
        panel.getChildren().add(createDivider());
        panel.getChildren().add(inspectorRow("DEGREE", lblDegree));
        panel.getChildren().add(createDivider());
        panel.getChildren().add(inspectorRow("LEVEL", lblLevel));
        panel.getChildren().add(createDivider());
        panel.getChildren().add(inspectorRow("CHILD NODES", lblChildNodes));
        panel.getChildren().add(createDivider());

        // TODO [Controller]: Conectar este botón a controller.handleDeleteNode()
        // El controller debe eliminar el nodo actualmente seleccionado y llamar drawTree() + setStats() para actualizar la vista
        Button btnDelete = new Button("✕   Delete Node");
        btnDelete.getStyleClass().add("sidebar-btn-delete");
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setAlignment(Pos.CENTER_LEFT);
        btnDelete.setOnAction(e -> controller.handleDeleteNode());
        panel.getChildren().add(btnDelete);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        panel.getChildren().add(filler);

        return panel;
    }

    /**
     * Construye la barra inferior con métricas del árbol y leyendas de nodo.
     *
     * @return contenedor horizontal con estadísticas y elementos de leyenda
     */
    private HBox buildBottomBar() {
        HBox bar = new HBox(20);
        bar.getStyleClass().add("bottom-bar");
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(10, 24, 10, 24));

        bar.getChildren().add(legendItem("INTERNAL NODE", "#0f172a"));
        bar.getChildren().add(legendItem("LEAF NODE", "#e2e8f0"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        bar.getChildren().add(spacer);

        lblDepthBar = statLabel("0");
        lblLCIBar = statLabel("—");
        lblLCIMBar = statLabel("—");
        lblParentsBar = statLabel("—");
        lblLeavesBar = statLabel("—");

        bar.getChildren().add(statItem("TREE DEPTH:", lblDepthBar));
        bar.getChildren().add(statItem("LCI:", lblLCIBar));
        bar.getChildren().add(statItem("LCIM:", lblLCIMBar));
        bar.getChildren().add(statItem("PARENT NODES:", lblParentsBar));
        bar.getChildren().add(statItem("LEAF NODES:", lblLeavesBar));

        return bar;
    }

    /**
     * Restablece la vista a su estado inicial.
     *
     * <p>Esta acción limpia el lienzo, muestra el mensaje de árbol vacío,
     * reinicia el panel de inspección y selecciona la representación de árbol.
     * Se utiliza cuando el usuario crea un árbol nuevo.</p>
     */
    public void resetView() {
        clearCanvas();
        showEmptyState();
        setInspector("—", "—", "—", "—");
        setStats(0, "—", "—", "—", "—");
        setRepresentationActive(true);
    }

    /**
     * Limpia todos los elementos gráficos dibujados en el lienzo del árbol.
     */
    public void clearCanvas() {
        treeCanvas.getChildren().clear();
    }

    /**
     * Muestra el estado vacío cuando el árbol no tiene nodos.
     */
    public void showEmptyState() {
        showEmptyState(DEFAULT_EMPTY_TEXT);
    }

    public void showEmptyState(String message) {
        emptyLabel.setText(message);
        emptyLabel.setVisible(true);
        treeCanvas.getChildren().setAll(emptyLabel);
    }

    /**
     * Oculta el mensaje que indica que el árbol está vacío.
     */
    public void hideEmptyState() {
        emptyLabel.setVisible(false);
    }

    /**
     * Actualiza el panel de inspección con los datos del nodo seleccionado.
     *
     * @param key valor del nodo
     * @param degree grado del nodo (cantidad de hijos)
     * @param level nivel del nodo dentro del árbol
     * @param childNodes valores de los hijos directos o "none" si no tiene
     */
    public void setInspector(String key, String degree, String level, String childNodes) {
        lblKeyValue.setText(key);
        lblDegree.setText(degree);
        lblLevel.setText(level);
        lblChildNodes.setText(childNodes);
    }

    /**
     * Actualiza las métricas que se muestran en la barra inferior.
     *
     * @param depth profundidad del árbol
     * @param lci longitud de camino interno del árbol
     * @param lcim longitud de camino interno medio del árbol
     * @param parents lista de valores de nodos padres
     * @param leaves lista de valores de nodos hoja
     */
    public void setStats(int depth, String lci, String lcim, String parents, String leaves) {
        lblDepthBar.setText(String.valueOf(depth));
        lblLCIBar.setText(lci);
        lblLCIMBar.setText(lcim);
        lblParentsBar.setText(parents);
        lblLeavesBar.setText(leaves);
    }

    /**
     * Marca qué pestaña de representación está activa.
     *
     * @param treeView {@code true} si la vista es árbol, {@code false} si es tabla enlazada
     */
    public void setRepresentationActive(boolean treeView) {
        treeViewActive = treeView;
        btnTabTree.getStyleClass().setAll(treeView ? "sidebar-btn-active" : "sidebar-btn");
        btnTabList.getStyleClass().setAll(treeView ? "sidebar-btn" : "sidebar-btn-active");
        btnTabLevel.getStyleClass().setAll("sidebar-btn");
    }

    /**
     * Actualiza las opciones de nivel disponibles para la vista por nivel.
     *
     * @param maxLevel nivel máximo del árbol
     */
    /**
     * Marca la vista de nodos por nivel como activa.
     */
    public void setLevelRepresentationActive() {
        treeViewActive = true;
        btnTabTree.getStyleClass().setAll("sidebar-btn");
        btnTabList.getStyleClass().setAll("sidebar-btn");
        btnTabLevel.getStyleClass().setAll("sidebar-btn-active");
    }

    /**
     * Dibuja la representación activa actual del árbol.
     *
     * @param root nodo raíz del árbol que se desea visualizar
     */
    public void drawCurrentRepresentation(TreeNode root) {
        if (treeViewActive) {
            drawTree(root);
        } else {
            drawLinkedList(root);
        }
    }

    /**
     * Dibuja el árbol completo en el lienzo a partir de la raíz proporcionada.
     *
     * @param root nodo raíz del árbol que se desea visualizar
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
     * Dibuja una representación de lista enlazada en forma de tabla.
     *
     * @param root nodo raíz del árbol que se desea representar
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
     * Dibuja solamente los nodos que están en el nivel seleccionado.
     *
     * @param root nodo raíz del árbol
     * @param level nivel que se desea mostrar
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
        nodeView.setOnMouseClicked(e -> controller.handleNodeClick(node));
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
        row.setOnMouseClicked(e -> controller.handleNodeClick(node));
        row.setCursor(javafx.scene.Cursor.HAND);

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

    /**
     * Dibuja un nodo y sus hijos recursivamente en el lienzo.
     *
     * @param node nodo actual a dibujar
     * @param x coordenada horizontal del centro del nodo
     * @param y coordenada vertical del centro del nodo
     * @param offset distancia horizontal entre niveles
     * @param gapY distancia vertical entre niveles
     * @param rootNode indica si el nodo actual es la raíz
     */
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
            rootTag.setLayoutX(x - size / 2);
            rootTag.setLayoutY(y - 22);
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
        nodeView.setOnMouseClicked(e -> controller.handleNodeClick(node));
        nodeView.setStyle("-fx-cursor: hand;");

        treeCanvas.getChildren().add(nodeView);
    }

    /**
     * Crea una línea de conexión entre dos nodos del árbol.
     *
     * @param x1 coordenada x de inicio
     * @param y1 coordenada y de inicio
     * @param x2 coordenada x de fin
     * @param y2 coordenada y de fin
     * @return línea que conecta dos nodos hijos
     */
    private Line drawLine(double x1, double y1, double x2, double y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.web("#cbd5e1"));
        line.setStrokeWidth(1.8);
        return line;
    }

    /**
     * Crea una línea divisoria horizontal para separar secciones de la barra lateral.
     *
     * @return panel que actúa como divisor visual
     */
    private Pane createDivider() {
        Pane divider = new Pane();
        divider.getStyleClass().add("sidebar-divider");
        divider.setPrefHeight(1);
        return divider;
    }

    /**
     * Genera una etiqueta de sección para la barra lateral.
     *
     * @param text texto que se mostrará como encabezado de sección
     * @return etiqueta con estilo de sección
     */
    private Label sectionLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("sidebar-section");
        return label;
    }

    /**
     * Crea un botón estándar de segunda prioridad para la barra lateral.
     *
     * @param text etiqueta del botón
     * @param icon icono textual del botón
     * @param action manejador de evento al pulsar el botón
     * @return botón configurado con estilo secundario
     */
    private Button secondaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    /**
     * Crea un botón activo para marcar la vista de árbol seleccionada.
     *
     * @param text etiqueta del botón
     * @param icon icono textual del botón
     * @param action manejador de evento al pulsar el botón
     * @return botón configurado con estilo de activación
     */
    private Button activeButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn-active");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    /**
     * Crea un botón principal con estilo destacado en la barra lateral.
     *
     * @param text etiqueta del botón
     * @param icon icono textual del botón
     * @param action manejador de evento al pulsar el botón
     * @return botón con estilo primario
     */
    private Button primaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn-primary");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    /**
     * Construye una fila de datos para el panel inspector.
     *
     * @param title etiqueta de la fila
     * @param value control que muestra el valor correspondiente
     * @return fila horizontal con título y valor alineados
     */
    private HBox inspectorRow(String title, Node value) {
        HBox row = new HBox();
        row.getStyleClass().add("insp-row");
        row.setPadding(new Insets(14, 20, 14, 20));
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(title);
        label.getStyleClass().add("insp-label");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        row.getChildren().addAll(label, spacer, value);

        return row;
    }

    /**
     * Crea una etiqueta de valor usada en el panel inspector.
     *
     * @param value texto inicial que mostrará la etiqueta
     * @return etiqueta con estilo de valor simple
     */
    private Label inspectorValueLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-value");
        return label;
    }

    /**
     * Crea una etiqueta tipo chip usada para mostrar valores de hijos.
     *
     * @param value texto inicial que mostrará el chip
     * @return etiqueta con estilo de chip
     */
    private Label inspectorChipLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-chip");
        return label;
    }

    /**
     * Crea un elemento de leyenda que muestra un punto de color y texto.
     *
     * @param text descripción del elemento de leyenda
     * @param color color de relleno del punto
     * @return fila horizontal con icono y etiqueta
     */
    private HBox legendItem(String text, String color) {
        HBox item = new HBox(8);
        item.setAlignment(Pos.CENTER_LEFT);

        Circle dot = new Circle(7);
        dot.setFill(Color.web(color));
        dot.setStroke(Color.web("#cbd5e1"));
        dot.setStrokeWidth(1.5);

        Label label = new Label(text);
        label.getStyleClass().add("legend-label");

        item.getChildren().addAll(dot, label);
        return item;
    }

    /**
     * Crea una etiqueta numérica usada en la barra de estadísticas.
     *
     * @param value texto inicial que mostrará la etiqueta
     * @return etiqueta con estilo de valor estadístico
     */
    private Label statLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("stat-bar-value");
        return label;
    }

    /**
     * Construye un elemento de estadística con título y valor.
     *
     * @param title texto de la métrica
     * @param valueLabel etiqueta que muestra el valor de la métrica
     * @return fila horizontal con título y valor
     */
    private HBox statItem(String title, Label valueLabel) {
        HBox item = new HBox(4);
        item.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-bar-title");
        item.getChildren().addAll(titleLabel, valueLabel);

        return item;
    }

    /**
     * Centra el texto de estado vacío dentro del área de dibujo.
     *
     * <p>Este método enlaza las propiedades de posición de la etiqueta con las
     * dimensiones del lienzo para mantenerla siempre centrada.</p>
     */
    private void bindCenterLabel() {
        emptyLabel.layoutXProperty().bind(
                treeCanvas.widthProperty().subtract(emptyLabel.widthProperty()).divide(2)
        );
        emptyLabel.layoutYProperty().bind(
                treeCanvas.heightProperty().subtract(emptyLabel.heightProperty()).divide(2)
        );
    }

    /**
     * Muestra un popup indicando que el nodo buscado no existe en el árbol.
    *
    * @param value valor buscado que no fue encontrado
    */
    public void showNodeNotFound(int value) {
    Stage popup = new Stage();
    popup.initModality(javafx.stage.Modality.APPLICATION_MODAL);
    popup.setResizable(false);
    popup.setTitle("");

    VBox box = new VBox(12);
    box.setAlignment(Pos.CENTER);
    box.setPadding(new Insets(24, 32, 24, 32));
    box.setStyle(
        "-fx-background-color: white;" +
        "-fx-border-color: #e2e8f0;" +
        "-fx-border-radius: 12;" +
        "-fx-background-radius: 12;"
    );

    Label icon = new Label("✕");
    icon.setStyle("-fx-text-fill: #e11d48; -fx-font-size: 22px; -fx-font-weight: bold;");

    Label msg = new Label("Node " + value + " not found in the tree.");
    msg.setStyle("-fx-text-fill: #0f172a; -fx-font-size: 14px; -fx-font-weight: bold;");

    Button btnClose = new Button("OK");
    btnClose.setStyle(
        "-fx-background-color: #0f172a;" +
        "-fx-text-fill: white;" +
        "-fx-font-size: 13px;" +
        "-fx-font-weight: bold;" +
        "-fx-padding: 8 32 8 32;" +
        "-fx-background-radius: 8;" +
        "-fx-cursor: hand;"
    );
    btnClose.setOnAction(e -> popup.close());

    box.getChildren().addAll(icon, msg, btnClose);

    Scene scene = new Scene(box, 320, 150);
    scene.getStylesheets().add(
        getClass().getResource("/com/ds/application/core/view/Styles.css").toExternalForm()
    );
    popup.setScene(scene);
    popup.showAndWait();
}

    /**
     * Método principal que arranca la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos que se pasan a JavaFX
     */
    public static void main(String[] args) {
        launch(args);
    }
}
