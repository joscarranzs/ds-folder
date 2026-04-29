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
        // Ejemplo mínimo: solo BorderLayout
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setCenter(new Text("Prueba: solo BorderLayout"));

        Window window = new Window(borderLayout.getLayout(), 800, 600);
        window.show();
    }
}
