package com.ds.application.ui.Resources;

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
import javafx.stage.Stage;

/**
 * ArbolBinarioUI.java
 * Interfaz principal del visualizador de árboles binarios.
 */
public class BinaryTree extends Application {

    // =====================================================
    // NODE INSPECTOR
    // =====================================================

    public Label lblKeyValue;
    public Label lblDegree;
    public Label lblLevel;
    public Label lblChildNodes;

    // =====================================================
    // PANEL CENTRAL
    // =====================================================

    public Pane panelArbol;
    public Label lblVacio;

    // =====================================================
    // BARRA INFERIOR
    // =====================================================

    public Label lblDepthBar;
    public Label lblLCIBar;
    public Label lblLCIMBar;
    public Label lblParentsBar;
    public Label lblLeavesBar;

    // =====================================================
    // REPRESENTACIÓN
    // =====================================================

    public Button btnTabArbol;
    public Button btnTabLista;

    // =====================================================
    // CONTROLLER
    // =====================================================

    private TreeController controller;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");

        root.setTop(buildTopBar());
        root.setLeft(buildSidebar());
        root.setCenter(buildCenter());
        root.setRight(buildRightPanel());
        root.setBottom(buildBottomBar());

        Scene scene = new Scene(root, 1280, 820);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/com/ds/application/ui/Resources/Styles.css")
                        .toExternalForm()
        );

        stage.setTitle("Binary Tree Visualizer");
        stage.setMinWidth(1000);
        stage.setMinHeight(680);
        stage.setScene(scene);

        controller = new TreeController(this);

        stage.show();
    }

    // =====================================================
    // TOP BAR
    // =====================================================

    private HBox buildTopBar() {

        HBox bar = new HBox();
        bar.getStyleClass().add("top-bar");
        bar.setAlignment(Pos.CENTER);
        bar.setPadding(new Insets(16, 32, 16, 32));

        Label titulo = new Label("Binary Tree Visualizer");
        titulo.getStyleClass().add("top-title");

        bar.getChildren().add(titulo);

        return bar;
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

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
        sidebar.getChildren().add(sidebarDivider());

        // ACTIONS
        sidebar.getChildren().add(sectionLabel("ACTIONS"));
        sidebar.getChildren().add(
                sidebarBtnPrimary("Insert", "+",
                        e -> controller.handleInsertNode())
        );

        sidebar.getChildren().add(
                sidebarBtn("Search", "?",
                        e -> controller.handleSearch())
        );

        sidebar.getChildren().add(
                sidebarBtn("Reset", "↺",
                        e -> controller.handleNewTree())
        );

        sidebar.getChildren().add(sidebarDivider());

        // OPERATIONS
        sidebar.getChildren().add(sectionLabel("OPERATIONS"));

        sidebar.getChildren().add(
                sidebarBtn("Identify parent nodes", "⇈",
                        e -> controller.handleParentNodes())
        );

        sidebar.getChildren().add(
                sidebarBtn("Identify leaf nodes", "⇊",
                        e -> controller.handleLeafNodes())
        );

        sidebar.getChildren().add(
                sidebarBtn("Calculate LCI", "Σ",
                        e -> controller.handleLCI())
        );

        sidebar.getChildren().add(
                sidebarBtn("Calculate LCIM", "⊞",
                        e -> controller.handleLCIM())
        );

        sidebar.getChildren().add(sidebarDivider());

        // REPRESENTATIONS
        sidebar.getChildren().add(sectionLabel("REPRESENTATIONS"));

        btnTabArbol = sidebarBtnActive(
                "Binary Tree", "⛶",
                e -> controller.handleSequential()
        );

        btnTabLista = sidebarBtn(
                "Linked List Table", "▦",
                e -> controller.handleLinkedList()
        );

        sidebar.getChildren().addAll(btnTabArbol, btnTabLista);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);

        sidebar.getChildren().add(filler);

        Button btnNuevo = new Button("New Tree");
        btnNuevo.getStyleClass().add("btn-nuevo");
        btnNuevo.setMaxWidth(Double.MAX_VALUE);
        btnNuevo.setOnAction(e -> controller.handleNewTree());

        VBox.setMargin(btnNuevo, new Insets(0, 16, 16, 16));

        sidebar.getChildren().add(btnNuevo);

        return sidebar;
    }

    // =====================================================
    // CENTER
    // =====================================================

    private StackPane buildCenter() {

        panelArbol = new Pane();
        panelArbol.getStyleClass().add("tree-canvas");

        lblVacio = new Label("Empty tree · Insert a root node to begin");
        lblVacio.getStyleClass().add("empty-label");

        lblVacio.layoutXProperty().bind(
                panelArbol.widthProperty()
                        .subtract(lblVacio.widthProperty())
                        .divide(2)
        );

        lblVacio.layoutYProperty().bind(
                panelArbol.heightProperty()
                        .subtract(lblVacio.heightProperty())
                        .divide(2)
        );

        panelArbol.getChildren().add(lblVacio);

        ScrollPane scroll = new ScrollPane(panelArbol);
        scroll.getStyleClass().add("tree-scroll");
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        StackPane center = new StackPane(scroll);
        center.getStyleClass().add("center-area");

        return center;
    }

    // =====================================================
    // RIGHT PANEL
    // =====================================================

    private VBox buildRightPanel() {

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

        panel.getChildren().add(header);
        panel.getChildren().add(panelDivider());

        lblKeyValue = inspValueLabel("—");
        lblDegree = inspValueLabel("—");
        lblLevel = inspValueLabel("—");
        lblChildNodes = inspChipLabel("—");

        panel.getChildren().add(inspRow("KEY VALUE", lblKeyValue));
        panel.getChildren().add(panelDivider());

        panel.getChildren().add(inspRow("DEGREE", lblDegree));
        panel.getChildren().add(panelDivider());

        panel.getChildren().add(inspRow("LEVEL", lblLevel));
        panel.getChildren().add(panelDivider());

        panel.getChildren().add(inspRow("CHILD NODES", lblChildNodes));
        panel.getChildren().add(panelDivider());

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);

        panel.getChildren().add(filler);

        return panel;
    }

    // =====================================================
    // BOTTOM BAR
    // =====================================================

    private HBox buildBottomBar() {

        HBox bar = new HBox(20);
        bar.getStyleClass().add("bottom-bar");
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(10, 24, 10, 24));

        bar.getChildren().add(
                legendItem("INTERNAL NODE", "#0f172a")
        );

        bar.getChildren().add(
                legendItem("LEAF NODE", "#e2e8f0")
        );

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

    // =====================================================
    // MÉTODOS PÚBLICOS
    // =====================================================

    public void setInspector(
            String key,
            String degree,
            String level,
            String childNodes
    ) {
        lblKeyValue.setText(key);
        lblDegree.setText(degree);
        lblLevel.setText(level);
        lblChildNodes.setText(childNodes);
    }

    public void setStats(
            int depth,
            String lci,
            String lcim,
            String parents,
            String leaves
    ) {
        lblDepthBar.setText(String.valueOf(depth));
        lblLCIBar.setText(lci);
        lblLCIMBar.setText(lcim);
        lblParentsBar.setText(parents);
        lblLeavesBar.setText(leaves);
    }

    public void setRepresentationActive(boolean arbol) {

        btnTabArbol.getStyleClass().setAll(
                arbol
                        ? "sidebar-btn-active"
                        : "sidebar-btn"
        );

        btnTabLista.getStyleClass().setAll(
                arbol
                        ? "sidebar-btn"
                        : "sidebar-btn-active"
        );
    }

    // =====================================================
    // HELPERS
    // =====================================================

    private Pane sidebarDivider() {
        Pane p = new Pane();
        p.getStyleClass().add("sidebar-divider");
        p.setPrefHeight(1);
        return p;
    }

    private Pane panelDivider() {
        Pane p = new Pane();
        p.getStyleClass().add("panel-divider");
        p.setPrefHeight(1);
        return p;
    }

    private Label sectionLabel(String text) {
        Label l = new Label(text);
        l.getStyleClass().add("sidebar-section");
        return l;
    }

    private Button sidebarBtn(
            String text,
            String icon,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {
        Button btn = new Button(icon + "   " + text);
        btn.getStyleClass().add("sidebar-btn");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setOnAction(action);
        return btn;
    }

    private Button sidebarBtnActive(
            String text,
            String icon,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {
        Button btn = new Button(icon + "   " + text);
        btn.getStyleClass().add("sidebar-btn-active");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setOnAction(action);
        return btn;
    }

    private Button sidebarBtnPrimary(
            String text,
            String icon,
            javafx.event.EventHandler<javafx.event.ActionEvent> action
    ) {
        Button btn = new Button(icon + "   " + text);
        btn.getStyleClass().add("sidebar-btn-primary");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setOnAction(action);
        return btn;
    }

    private HBox inspRow(String title, Node value) {

        HBox row = new HBox();
        row.getStyleClass().add("insp-row");
        row.setPadding(new Insets(14, 20, 14, 20));
        row.setAlignment(Pos.CENTER_LEFT);

        Label lbl = new Label(title);
        lbl.getStyleClass().add("insp-label");

        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);

        row.getChildren().addAll(lbl, sp, value);

        return row;
    }

    private Label inspValueLabel(String v) {
        Label l = new Label(v);
        l.getStyleClass().add("insp-value");
        return l;
    }

    private Label inspChipLabel(String v) {
        Label l = new Label(v);
        l.getStyleClass().add("insp-chip");
        return l;
    }

    private HBox legendItem(String text, String color) {

        HBox item = new HBox(8);
        item.setAlignment(Pos.CENTER_LEFT);

        Circle dot = new Circle(7);
        dot.setFill(Color.web(color));
        dot.setStroke(Color.web("#cbd5e1"));
        dot.setStrokeWidth(1.5);

        Label lbl = new Label(text);
        lbl.getStyleClass().add("legend-label");

        item.getChildren().addAll(dot, lbl);

        return item;
    }

    private Label statLabel(String value) {
        Label l = new Label(value);
        l.getStyleClass().add("stat-bar-value");
        return l;
    }

    private HBox statItem(String title, Label valueLabel) {

        HBox item = new HBox(4);
        item.setAlignment(Pos.CENTER_LEFT);

        Label t = new Label(title);
        t.getStyleClass().add("stat-bar-title");

        item.getChildren().addAll(t, valueLabel);

        return item;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
