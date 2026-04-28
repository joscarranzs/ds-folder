package com.ds;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import com.ds.application.Window;

/**
 * Ejemplo mínimo que muestra cómo usar la clase Window para presentar
 * un BorderPane con texto centrado.
 */
public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    BorderPane root = new BorderPane();
    Text text = new Text("Hola mundo - prueba de Window");
    root.setCenter(text);

    // Usamos Window (la clase encapsula su propio Stage)
    Window window = new Window(root, 800, 600);
    window.show();
  }
}
