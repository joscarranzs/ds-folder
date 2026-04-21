package com.ds.application.core.view;

import java.util.List;

import com.ds.application.core.controller.TreeController;
import com.ds.application.core.model.TreeNode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

public class BinaryTree extends Application {

    private TreeController controller;
    private TreeCanvas treeCanvas;
    private InspectorPanel inspectorPanel;
    private StatsBar statsBar;

    private Button btnTabTree;
    private Button btnTabList;
    private Button btnTabLevel;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root-pane");

        treeCanvas = new TreeCanvas();
        inspectorPanel = new InspectorPanel();
        statsBar = new StatsBar();
        controller = new TreeController(this);
        inspectorPanel.setDeleteAction(e -> controller.handleDeleteNode());
        treeCanvas.setNodeClickHandler(controller::handleNodeClick);
        setInspector("—", "—", "—", "—");
        setStats(0, "—", "—", "—", "—");

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

    private HBox buildSidebar() {
        HBox sideWrapper = new HBox();
        sideWrapper.getChildren().add(createSidebarContent());
        return sideWrapper;
    }

    private VBox createSidebarContent() {
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
        sidebar.getChildren().add(primaryButton("Insert", "fas-plus", e -> controller.handleInsertNode()));

        Button btnSearch = secondaryButton("Search", "fas-search", e -> controller.handleSearch());
        sidebar.getChildren().add(btnSearch);
        sidebar.getChildren().add(secondaryButton("Reset", "fas-undo", e -> controller.handleNewTree()));
        sidebar.getChildren().add(createDivider());

        sidebar.getChildren().add(sectionLabel("REPRESENTATIONS"));
        btnTabTree = activeButton("Binary Tree", "fas-sitemap", e -> controller.handleSequential());
        btnTabList = secondaryButton("Linked List Table", "fas-table", e -> controller.handleLinkedList());
        btnTabLevel = secondaryButton("Nodes by Level", "fas-layer-group", e -> controller.handleNodesByLevel());

        sidebar.getChildren().addAll(btnTabTree, btnTabList, btnTabLevel);

        Region filler = new Region();
        VBox.setVgrow(filler, Priority.ALWAYS);
        sidebar.getChildren().add(filler);

        return sidebar;
    }

    private Parent buildCenter() {
        return treeCanvas.getView();
    }

    private VBox buildInspectorPanel() {
        return inspectorPanel.getRoot();
    }

    private HBox buildBottomBar() {
        return (HBox) statsBar.getRoot();
    }

    public void resetView() {
        treeCanvas.resetView();
        setInspector("—", "—", "—", "—");
        setStats(0, "—", "—", "—", "—");
    }

    public void drawCurrentRepresentation(TreeNode root) {
        treeCanvas.drawCurrentRepresentation(root);
    }

    public void drawNodesByLevel(TreeNode root) {
        treeCanvas.drawNodesByLevel(root);
    }

    public void animateSearch(List<TreeNode> path, boolean found) {
        treeCanvas.animateSearch(path, found);
    }

    public void setInspector(String key, String degree, String level, String childNodes) {
        inspectorPanel.setInspector(key, degree, level, childNodes);
    }

    public void setStats(int depth, String lci, String lcim, String parents, String leaves) {
        statsBar.setStats(depth, lci, lcim, parents, leaves);
    }

    public void setRepresentationActive(boolean treeView) {
        treeCanvas.setRepresentationActive(treeView);
        btnTabTree.getStyleClass().setAll(treeView ? "sidebar-btn-active" : "sidebar-btn");
        btnTabList.getStyleClass().setAll(treeView ? "sidebar-btn" : "sidebar-btn-active");
        btnTabLevel.getStyleClass().setAll("sidebar-btn");
    }

    public void setLevelRepresentationActive() {
        treeCanvas.setLevelRepresentationActive();
        btnTabTree.getStyleClass().setAll("sidebar-btn");
        btnTabList.getStyleClass().setAll("sidebar-btn");
        btnTabLevel.getStyleClass().setAll("sidebar-btn-active");
    }

    private Button secondaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setGraphic(createIcon(icon, Color.web("#475569")));
        button.setGraphicTextGap(12);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.getStyleClass().add("sidebar-btn");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    private Button activeButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setGraphic(createIcon(icon, Color.web("#1d4ed8")));
        button.setGraphicTextGap(12);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.getStyleClass().add("sidebar-btn-active");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    private Button primaryButton(String text, String icon, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setGraphic(createIcon(icon, Color.web("#1d4ed8")));
        button.setGraphicTextGap(12);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.getStyleClass().add("sidebar-btn-primary");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setOnAction(action);
        return button;
    }

    private FontIcon createIcon(String literal, Color color) {
        FontIcon icon = new FontIcon(literal);
        icon.setIconSize(20);
        icon.setIconColor(color);
        icon.getStyleClass().add("button-icon");
        return icon;
    }

    private Label sectionLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("sidebar-section");
        return label;
    }

    private Region createDivider() {
        Region divider = new Region();
        divider.getStyleClass().add("sidebar-divider");
        divider.setPrefHeight(1);
        return divider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
