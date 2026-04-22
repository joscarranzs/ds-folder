package com.ds.application.core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Muestra la pantalla de bienvenida inicial y navega al visualizador principal de árbol binario.
 *
 * <p>La pantalla de bienvenida presenta metadatos de la aplicación y ofrece un único punto
 * de entrada para iniciar la etapa principal de visualización.</p>
 */
public class WelcomeScreen {

    /**
     * Construye y muestra la pantalla de bienvenida.
     *
     * @param stage la etapa usada para mostrar la interfaz de bienvenida
     */
    public void show(Stage stage) {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(buildCard(stage));

        Scene scene = new Scene(root, 560, 480);
        scene.getStylesheets().add(
                getClass().getResource("/com/ds/application/core/view/Styles.css")
                        .toExternalForm()
        );

        stage.setTitle("Binary Tree Visualizer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private VBox buildCard(Stage stage) {
        VBox card = new VBox(0);
        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #e2e8f0;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 16;" +
            "-fx-background-radius: 16;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.10), 24, 0, 0, 4);"
        );
        card.setMaxWidth(440);

        
        VBox topBox = new VBox(10);
        topBox.setPadding(new Insets(20, 24, 18, 24));

        HBox logosRow = new HBox();
        logosRow.setAlignment(Pos.CENTER);

        ImageView logoLeft = new ImageView(new Image(getClass().getResourceAsStream(
        "/com/ds/application/core/view/6_logo_utp_-_rgb_oficial.png")));
        logoLeft.setFitHeight(52);
        logoLeft.setPreserveRatio(true);

        VBox centerText = new VBox(4);
        centerText.setAlignment(Pos.CENTER);
        HBox.setHgrow(centerText, Priority.ALWAYS);

        Label labTag = new Label("LAB #1");
        labTag.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 11px; -fx-font-weight: bold;");
        labTag.setMaxWidth(Double.MAX_VALUE);
        labTag.setAlignment(Pos.CENTER);

        Label title = new Label("BINARY TREE VISUALIZER");
        title.setStyle("-fx-text-fill: #0f172a; -fx-font-size: 16px; -fx-font-weight: bold;");
        title.setWrapText(true);
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);

        centerText.getChildren().addAll(labTag, title);

        ImageView logoRight = new ImageView(new Image(getClass().getResourceAsStream(
        "/com/ds/application/core/view/logo_en_contactenos-2.png")));
        logoRight.setFitHeight(52);
        logoRight.setPreserveRatio(true);

        logosRow.getChildren().addAll(logoLeft, centerText, logoRight);
        topBox.getChildren().add(logosRow);

        
        Pane divider1 = new Pane();
        divider1.setStyle("-fx-background-color: #e2e8f0;");
        divider1.setPrefHeight(1);

        
        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(18, 24, 20, 24));

        HBox grid = new HBox(12);
        grid.getChildren().addAll(
            infoCard("ASIGNATURA", "Estructura de Datos II"),
            infoCard("GRUPO", "6GS121 · I Semestre 2026")
        );
        HBox.setHgrow(grid.getChildren().get(0), Priority.ALWAYS);
        HBox.setHgrow(grid.getChildren().get(1), Priority.ALWAYS);

        VBox integrantes = infoBlock("INTEGRANTES",
            "Jose Carranza · Francisco Arena · Rodolfo Martínez\n Michelle Sánchez  · Ariel Benítez");

        VBox facilitadora = infoBlock("FACILITADORA",
            "Ing. Maria Y. Tejedor de Fernandez");

        Button btnStart = new Button("Inicio  →");
        btnStart.setMaxWidth(Double.MAX_VALUE);
        btnStart.setStyle(
            "-fx-background-color: #0f172a;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 12 0 12 0;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );
        btnStart.setOnAction(e -> {
            stage.close();
            Stage mainStage = new Stage();
            new BinaryTree().start(mainStage);
        });

        bottomBox.getChildren().addAll(grid, integrantes, facilitadora, btnStart);

        card.getChildren().addAll(topBox, divider1, bottomBox);
        return card;
    }

    /**
     * Crea una tarjeta compacta de etiqueta para la sección de detalles de la pantalla de bienvenida.
     *
     * @param label la etiqueta de título de la tarjeta
     * @param value el contenido de valor mostrado en la tarjeta
     * @return un nodo de tarjeta configurado
     */
    private VBox infoCard(String label, String value) {
        VBox box = new VBox(4);
        box.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-padding: 10 14 10 14;" +
            "-fx-background-radius: 10;"
        );
        Label l = new Label(label);
        l.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 10px; -fx-font-weight: bold;");
        Label v = new Label(value);
        v.setStyle("-fx-text-fill: #334155; -fx-font-size: 13px; -fx-font-weight: bold;");
        box.getChildren().addAll(l, v);
        return box;
    }

    /**
     * Crea un bloque de información que contiene un encabezado y contenido multilineal.
     *
     * @param label el texto del encabezado
     * @param value el texto descriptivo multilineal
     * @return un nodo de bloque de información configurado
     */
    private VBox infoBlock(String label, String value) {
        VBox box = new VBox(4);
        box.setStyle(
            "-fx-background-color: #f8fafc;" +
            "-fx-padding: 10 14 10 14;" +
            "-fx-background-radius: 10;"
        );
        Label l = new Label(label);
        l.setStyle("-fx-text-fill: #94a3b8; -fx-font-size: 10px; -fx-font-weight: bold;");
        Label v = new Label(value);
        v.setStyle("-fx-text-fill: #334155; -fx-font-size: 13px; -fx-font-weight: bold;");
        v.setWrapText(true);
        box.getChildren().addAll(l, v);
        return box;
    }
}