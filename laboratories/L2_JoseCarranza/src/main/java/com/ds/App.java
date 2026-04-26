package com.ds;

import com.ds.application.view.Interface;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Interface screen = new Interface();

        Scene scene = new Scene(screen.getRoot(), 1200, 720);

        stage.setTitle("Visualizador de Arboles");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
