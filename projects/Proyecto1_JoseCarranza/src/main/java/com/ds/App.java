package com.ds;

import com.ds.application.view.Interface;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación JavaFX. Configura y muestra la ventana principal
 * con la interfaz de usuario para visualizar árboles.
 */
public class App extends Application {

  /**
   * Método de entrada para la aplicación JavaFX. Configura la escena y muestra la
   * ventana principal.
   *
   * @param stage el escenario principal proporcionado por JavaFX
   */
  @Override
  public void start(Stage stage) {
    // Crear la interfaz de usuario
    Interface ui = new Interface();

    Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
    double targetWidth = visualBounds.getWidth() * 0.92;
    double targetHeight = visualBounds.getHeight() * 0.92;

    Scene scene = new Scene(ui.getRoot(), targetWidth, targetHeight);

    stage.setTitle("Visualizador de Árboles");
    stage.setScene(scene);

    // Tamaño mínimo para evitar que se rompa el layout
    stage.setMinWidth(1080);
    stage.setMinHeight(680);

    stage.centerOnScreen();
    // Opcional: permite redimensionar
    stage.setResizable(true);

    // Mostrar la ventana
    stage.show();
  }

  /** Método principal que lanza la aplicación JavaFX.
   *
   * @param args argumentos de línea de comandos (no utilizados)
   */
  public static void main(String[] args) {
    launch();
  }
}
