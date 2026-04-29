package com.ds;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import com.ds.application.Window;
import com.ds.application.ui.layouts.BorderLayout;

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
        BorderLayout layout = new BorderLayout();
        Text text = new Text("Hola mundo - prueba de Window");
        layout.setCenter(text);

        // Usamos Window (la clase encapsula su propio Stage)
        // Pasamos el BorderPane interno como Pane al constructor de Window
        Window window = new Window(layout.getLayout(), 800, 600);
        window.show();
    }
}
