package com.ds.application.view;

import com.ds.application.view.components.header.Views;
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

    // Contenedor principal de toda la aplicación
    private BorderPane root;

    // Header principal de navegación entre vistas
    private Views header;

    public Interface() {
        root = new BorderPane();

        // Header superior con las opciones para cambiar de vista
        header = new Views(
                this::showBinaryTreeView,
                this::showHuffmanView
        );

        root.setStyle("-fx-background-color: #f1f5f9;");

        // Al iniciar la app, primero muestro la pantalla de bienvenida
        showWelcomeView();
    }

    // Pantalla inicial tipo portada del laboratorio
    private void showWelcomeView() {
        // En welcome oculto el header para que parezca una portada limpia
        root.setTop(null);
        root.setLeft(null);
        root.setRight(null);

        // Contenedor principal de la portada
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

        // Header interno con logos y título
        HBox topRow = createWelcomeHeader();

        // Línea separadora
        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #e5e7eb;");

        // Información de asignatura y grupo
        HBox infoRow = new HBox(110);
        infoRow.setAlignment(Pos.CENTER_LEFT);

        VBox subjectBox = createInfoBlock("ASIGNATURA", "Estructura de Datos II");
        VBox groupBox = createInfoBlock("GRUPO", "6GS121 · I Semestre 2026");

        infoRow.getChildren().addAll(subjectBox, groupBox);

        // Integrantes del laboratorio
        VBox membersBox = createInfoBlock(
                "INTEGRANTES",
                "Jose Carranza · Francisco Arena · Rodolfo Martinez\n" +
                "Michelle Sánchez · Aniel Benítez"
        );

        // Facilitadora del curso
        VBox teacherBox = createInfoBlock(
                "FACILITADORA",
                "Ing. Maria Y. Tejedor de Fernandez"
        );

        // Espaciador para empujar el botón hacia abajo y mejorar la estructura visual
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Botón de inicio
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

        // Al presionar, pasamos a la vista principal del árbol binario
        startButton.setOnAction(e -> showBinaryTreeView());

        // Agrego todo en orden
        container.getChildren().addAll(
                topRow,
                divider,
                infoRow,
                membersBox,
                teacherBox,
                spacer,
                startButton
        );

        // Wrapper centrado para que la portada no quede pegada a los bordes
        StackPane wrapper = new StackPane(container);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setPadding(new Insets(32));
        wrapper.setStyle("-fx-background-color: #f1f5f9;");

        root.setCenter(wrapper);
    }

    // Crea el header interno de la pantalla welcome
    private HBox createWelcomeHeader() {
        HBox topRow = new HBox(24);
        topRow.setAlignment(Pos.CENTER);

        // Logo izquierdo UTP
        ImageView leftLogo = createLogo("/images/utp.png");

        // Contenedor central del título
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

        // Logo derecho FISC
        ImageView rightLogo = createLogo("/images/fisc.png");

        topRow.getChildren().addAll(leftLogo, titleBox, rightLogo);
        return topRow;
    }

    // Crea un logo desde resources/images
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

    // Crea un bloque de información con título pequeño y valor destacado
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

    // Construye la vista completa del árbol binario
    private void showBinaryTreeView() {
        // Al entrar a una vista real, vuelvo a mostrar el header
        root.setTop(header);

        NodeInspector inspector = new NodeInspector();
        BinaryTreeVisualizer visualizer = new BinaryTreeVisualizer();
        BinaryTreeControlPanel panel = new BinaryTreeControlPanel(visualizer, inspector);

        StackPane leftWrap = new StackPane(panel);
        leftWrap.setPadding(new Insets(18, 10, 18, 18));

        StackPane centerWrap = new StackPane(visualizer);
        centerWrap.setPadding(new Insets(18, 10, 18, 10));
        centerWrap.setStyle("-fx-background-color: #f8fafc;");

        StackPane rightWrap = new StackPane(inspector);
        rightWrap.setPadding(new Insets(18, 18, 18, 10));

        root.setLeft(leftWrap);
        root.setCenter(centerWrap);
        root.setRight(rightWrap);
    }

    // Construye la vista del algoritmo Huffman
    private void showHuffmanView() {
        // Al entrar a una vista real, vuelvo a mostrar el header
        root.setTop(header);

        HuffmanAlgorithmVisualizer visualizer = new HuffmanAlgorithmVisualizer();

        root.setLeft(new HuffmanAlgorithmControlPanel(visualizer));
        root.setCenter(visualizer);
        root.setRight(null);
    }

    // Devuelve la raíz principal para que App pueda ponerla en la Scene
    public BorderPane getRoot() {
        return root;
    }
}