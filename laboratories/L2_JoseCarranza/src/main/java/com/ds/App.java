package com.ds.application;

import com.ds.application.view.Interface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Interface ui = new Interface();

        Scene scene = new Scene(ui.getRoot(), 1280, 800);

        stage.setTitle("Visualizador de Árboles");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}