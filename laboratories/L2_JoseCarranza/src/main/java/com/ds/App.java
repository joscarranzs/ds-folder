package com.ds;

import com.ds.application.view.window.Primary;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Ejemplo mínimo que usa Primary para mostrar un layout sencillo y una etiqueta de texto.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // crear un layout simple y contenido
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Hello from Primary window manager"));

        // Usar Primary para gestionar Stage/Scene
        Primary primary = new Primary(stage, root, 1280, 800);
        primary.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
