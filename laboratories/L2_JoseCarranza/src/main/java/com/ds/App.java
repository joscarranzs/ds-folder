package com.ds.application;

import com.ds.application.view.window.Primary;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Minimal example that uses Primary to show a simple layout and a text label.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // create a simple layout and content
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Hello from Primary window manager"));

        // Use Primary to manage Stage/Scene
        Primary primary = new Primary(stage, root, 1280, 800);
        primary.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
