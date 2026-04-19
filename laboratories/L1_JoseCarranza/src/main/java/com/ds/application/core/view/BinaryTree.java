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
 * Main UI entry point for the binary tree visualizer.
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
        sidebar.getChildren().add(secondaryButton("Search", "?", e -> controller.handleSearch()));
        sidebar.getChildren().add(secondaryButton("Reset", "↺", e -> controller.handleNewTree()));
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("OPERATIONS"));
        sidebar.getChildren().add(secondaryButton("Identify parent nodes", "⇈", e -> controller.handleParentNodes()));
        sidebar.getChildren().add(secondaryButton("Identify leaf nodes", "⇊", e -> controller.handleLeafNodes()));
        sidebar.getChildren().add(secondaryButton("Calculate LCI", "Σ", e -> controller.handleLCI()));
        sidebar.getChildren().add(secondaryButton("Calculate LCIM", "⊞", e -> controller.handleLCIM()));
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("REPRESENTATIONS"));
        btnTabTree = activeButton("Binary Tree", "⛶", e -> controller.handleSequential());
        btnTabList = secondaryButton("Linked List Table", "▦", e -> controller.handleLinkedList());
        sidebar.getChildren().addAll(btnTabTree, btnTabList);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        sidebar.getChildren().add(filler);

        Button btnNew = new Button("New Tree");
        btnNew.getStyleClass().add("btn-new");
        btnNew.setMaxWidth(Double.MAX_VALUE);
        btnNew.setOnAction(e -> controller.handleNewTree());
        VBox.setMargin(btnNew, new Insets(0, 16, 16, 16));

        sidebar.getChildren().add(btnNew);
        return sidebar;
    }

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

    public void resetView() {
        clearCanvas();
        showEmptyState();
        setInspector("—", "—", "—", "—");
        setStats(0, "—", "—", "—", "—");
        setRepresentationActive(true);
    }

    public void clearCanvas() {
        treeCanvas.getChildren().clear();
    }

    public void showEmptyState() {
        emptyLabel.setVisible(true);
        treeCanvas.getChildren().setAll(emptyLabel);
    }

    public void hideEmptyState() {
        emptyLabel.setVisible(false);
    }

    public void setInspector(String key, String degree, String level, String childNodes) {
        lblKeyValue.setText(key);
        lblDegree.setText(degree);
        lblLevel.setText(level);
        lblChildNodes.setText(childNodes);
    }

    public void setStats(int depth, String lci, String lcim, String parents, String leaves) {
        lblDepthBar.setText(String.valueOf(depth));
        lblLCIBar.setText(lci);
        lblLCIMBar.setText(lcim);
        lblParentsBar.setText(parents);
        lblLeavesBar.setText(leaves);
    }

    public void setRepresentationActive(boolean treeView) {
        btnTabTree.getStyleClass().setAll(treeView ? "sidebar-btn-active" : "sidebar-btn");
        btnTabList.getStyleClass().setAll(treeView ? "sidebar-btn" : "sidebar-btn-active");
    }

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

    private Line drawLine(double x1, double y1, double x2, double y2) {
        Line line = new Line(x1, y1, x2, y2);
        line.setStroke(Color.web("#cbd5e1"));
        line.setStrokeWidth(1.8);
        return line;
    }

    private Pane createDivider() {
        Pane divider = new Pane();
        divider.getStyleClass().add("sidebar-divider");
        divider.setPrefHeight(1);
        return divider;
    }

    private Label sectionLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("sidebar-section");
        return label;
    }

    private Button secondaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    private Button activeButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn-active");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    private Button primaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(icon + "   " + text);
        button.getStyleClass().add("sidebar-btn-primary");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

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

    private Label inspectorValueLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-value");
        return label;
    }

    private Label inspectorChipLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("insp-chip");
        return label;
    }

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

    private Label statLabel(String value) {
        Label label = new Label(value);
        label.getStyleClass().add("stat-bar-value");
        return label;
    }

    private HBox statItem(String title, Label valueLabel) {
        HBox item = new HBox(4);
        item.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("stat-bar-title");
        item.getChildren().addAll(titleLabel, valueLabel);

        return item;
    }

    private void bindCenterLabel() {
        emptyLabel.layoutXProperty().bind(
                treeCanvas.widthProperty().subtract(emptyLabel.widthProperty()).divide(2)
        );
        emptyLabel.layoutYProperty().bind(
                treeCanvas.heightProperty().subtract(emptyLabel.heightProperty()).divide(2)
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
