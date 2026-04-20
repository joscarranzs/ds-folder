package com.ds.application.core.view;

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

        Label logoSub = new Label("V1.0 Technical Preview");
        logoSub.getStyleClass().add("logo-sub");

        logo.getChildren().addAll(logoTitle, logoSub);
        sidebar.getChildren().add(logo);
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("ACTIONS"));
        sidebar.getChildren().add(primaryButton("Insert", "+", e -> controller.handleInsertNode()));
        sidebar.getChildren().add(secondaryButton("Reset", "↺", e -> controller.handleNewTree()));
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("REPRESENTATIONS"));
        btnTabTree = activeButton("Binary Tree", "⛶", e -> controller.handleSequential());
        btnTabList = secondaryButton("Linked List Table", "▦", e -> controller.handleLinkedList());
        sidebar.getChildren().addAll(btnTabTree, btnTabList);

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
        scroll.setFitToHeight(true);

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
        btnTabTree.getStyleClass().setAll(treeView ? "sidebar-btn-active" : "sidebar-btn");
        btnTabList.getStyleClass().setAll(treeView ? "sidebar-btn" : "sidebar-btn-active");
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

        drawNode(root, width / 2, 70, width / 4, 100, true);
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
     * Método principal que arranca la aplicación JavaFX.
     *
     * @param args argumentos de línea de comandos que se pasan a JavaFX
     */
    public static void main(String[] args) {
        launch(args);
    }
}
