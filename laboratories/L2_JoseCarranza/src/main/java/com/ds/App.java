package com.ds;

import com.ds.application.view.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // Crear la interfaz principal
        Interface ui = new Interface();

        // Tamaño inicial correcto
        Scene scene = new Scene(ui.getRoot(), 1723, 830);

        stage.setTitle("Visualizador de Árboles");
        stage.setScene(scene);

        // Tamaño inicial de la ventana
        stage.setWidth(1723);
        stage.setHeight(830);

        // Tamaño mínimo para evitar que se rompa el layout
        stage.setMinWidth(1400);
        stage.setMinHeight(750);

        // Centrar ventana en pantalla
        stage.centerOnScreen();

        // Opcional: permite redimensionar
        stage.setResizable(true);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}