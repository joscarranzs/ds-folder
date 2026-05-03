package com.ds.application.view;

import com.ds.application.view.components.header.Views;
import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.sidebar.BinaryTreeControlPanel;
import com.ds.application.view.components.sidebar.HuffmanAlgorithmControlPanel;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;
import com.ds.application.view.components.visualizers.HuffmanAlgorithmVisualizer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Interface {

    private BorderPane root;
    private Views header;

    public Interface() {
        root = new BorderPane();

        header = new Views(
                this::showBinaryTreeView,
                this::showHuffmanView
        );

        root.setStyle("-fx-background-color: #f1f5f9;");

        showWelcomeView();
    }

    private void showWelcomeView() {
        root.setTop(null);
        root.setLeft(null);
        root.setRight(null);

        VBox container = new VBox(24);
        container.setPadding(new Insets(34));
        container.setAlignment(Pos.TOP_LEFT);
        container.setPrefWidth(820);
        container.setMaxWidth(820);
        container.setMinHeight(560);
        container.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 18;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.10), 22, 0.25, 0, 8);"
        );

        HBox topRow = createWelcomeHeader();

        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #e5e7eb;");

        HBox infoRow = new HBox(110);
        infoRow.setAlignment(Pos.CENTER_LEFT);

        VBox subjectBox = createInfoBlock("ASIGNATURA", "Estructura de Datos II");
        VBox groupBox = createInfoBlock("GRUPO", "6GS121 · I Semestre 2026");

        infoRow.getChildren().addAll(subjectBox, groupBox);

        VBox membersBox = createInfoBlock(
                "INTEGRANTES",
                "Jose Carranza · Francisco Arena · Rodolfo Martinez\n" +
                "Michelle Sánchez · Aniel Benítez"
        );

        VBox teacherBox = createInfoBlock(
                "FACILITADORA",
                "Ing. Maria Y. Tejedor de Fernandez"
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button startButton = new Button("Inicio →");
        startButton.setMaxWidth(Double.MAX_VALUE);
        startButton.setStyle(
                "-fx-background-color: #0f172a;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 14;" +
                "-fx-cursor: hand;"
        );

        startButton.setOnAction(e -> showBinaryTreeView());

        container.getChildren().addAll(
                topRow,
                divider,
                infoRow,
                membersBox,
                teacherBox,
                spacer,
                startButton
        );

        StackPane wrapper = new StackPane(container);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setPadding(new Insets(32));
        wrapper.setStyle("-fx-background-color: #f1f5f9;");

        root.setCenter(wrapper);
    }

    private HBox createWelcomeHeader() {
        HBox topRow = new HBox(24);
        topRow.setAlignment(Pos.CENTER);

        ImageView leftLogo = createLogo("/images/utp.png");

        VBox titleBox = new VBox(8);
        titleBox.setAlignment(Pos.CENTER);

        Label lab = new Label("LAB #2");
        lab.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #9ca3af;" +
                "-fx-font-weight: bold;"
        );

        Label title = new Label("BINARY TREE VISUALIZER");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0f172a;"
        );

        titleBox.getChildren().addAll(lab, title);
        HBox.setHgrow(titleBox, Priority.ALWAYS);

        ImageView rightLogo = createLogo("/images/fisc.png");

        topRow.getChildren().addAll(leftLogo, titleBox, rightLogo);
        return topRow;
    }

    private ImageView createLogo(String path) {
        java.io.InputStream stream = getClass().getResourceAsStream(path);
        ImageView logo = new ImageView();

        if (stream == null) {
            System.out.println("No se encontro el recurso: " + path);
            logo.setFitWidth(76);
            logo.setFitHeight(76);
            return logo;
        }

        Image image = new Image(stream);
        logo.setImage(image);
        logo.setFitWidth(76);
        logo.setFitHeight(76);
        logo.setPreserveRatio(true);

        return logo;
    }

    private VBox createInfoBlock(String titleText, String valueText) {
        VBox box = new VBox(7);

        Label title = new Label(titleText);
        title.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #9ca3af;" +
                "-fx-font-weight: bold;"
        );

        Label value = new Label(valueText);
        value.setWrapText(true);
        value.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #111827;"
        );

        box.getChildren().addAll(title, value);
        return box;
    }

    private void showBinaryTreeView() {
        root.setTop(header);

        NodeInspector inspector = new NodeInspector();
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer();
        TreeInfoBar infoBar = new TreeInfoBar();

        BinaryTreeControlPanel panel = new BinaryTreeControlPanel(visualizer, inspector, infoBar);

        StackPane leftWrap = new StackPane(panel);
        leftWrap.setPadding(new Insets(18, 10, 18, 18));

        StackPane visualizerWrap = new StackPane(visualizer);
        visualizerWrap.setPadding(new Insets(18, 10, 10, 10));
        visualizerWrap.setStyle("-fx-background-color: #f8fafc;");

        HBox bottomContent = new HBox(10);
        bottomContent.setAlignment(Pos.CENTER_LEFT);
        bottomContent.getChildren().addAll(infoBar, createZoomControls(visualizer));
        HBox.setHgrow(infoBar, Priority.ALWAYS);

        StackPane bottomWrap = new StackPane(bottomContent);
        bottomWrap.setPadding(new Insets(0, 10, 18, 10));
        bottomWrap.setStyle("-fx-background-color: #f8fafc;");

        BorderPane centerLayout = new BorderPane();
        centerLayout.setCenter(visualizerWrap);
        centerLayout.setBottom(bottomWrap);
        centerLayout.setStyle("-fx-background-color: #f8fafc;");

        StackPane rightWrap = new StackPane(inspector);
        rightWrap.setAlignment(Pos.BOTTOM_CENTER);
        rightWrap.setPadding(new Insets(18, 18, 18, 10));
        rightWrap.setMinHeight(0);
        rightWrap.setMaxHeight(Double.MAX_VALUE);

        BorderPane.setAlignment(rightWrap, Pos.BOTTOM_CENTER);

        root.setLeft(leftWrap);
        root.setCenter(centerLayout);
        root.setRight(rightWrap);
    }

    // Crea controles pequeños para zoom y centrado del árbol
    private HBox createZoomControls(BinaryTreeVisualizer visualizer) {
        HBox controls = new HBox(6);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(6));
        controls.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 18;"
        );

        Button zoomIn = createZoomButton("+");
        Button zoomOut = createZoomButton("-");
        Button center = createZoomButton("Centrar");

        zoomIn.setOnAction(e -> visualizer.zoomIn());
        zoomOut.setOnAction(e -> visualizer.zoomOut());
        center.setOnAction(e -> visualizer.centerView());

        controls.getChildren().addAll(zoomIn, zoomOut, center);
        return controls;
    }

    // Crea botones pequeños para la barra de zoom
    private Button createZoomButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #f1f5f9;" +
                "-fx-text-fill: #111827;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 9 12;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private void showHuffmanView() {
        root.setTop(header);

        HuffmanAlgorithmVisualizer visualizer = new HuffmanAlgorithmVisualizer();

        root.setLeft(new HuffmanAlgorithmControlPanel(visualizer));
        root.setCenter(visualizer);
        root.setRight(null);
    }

    public BorderPane getRoot() {
        return root;
    }
}