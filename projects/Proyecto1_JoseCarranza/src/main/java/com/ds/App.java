package com.ds;

import com.ds.application.view.Interface;

import javafx.application.Application;
import javafx.scene.Scene;
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

    // Tamaño inicial correcto
    Scene scene = new Scene(ui.getRoot(), 1200, 720);

    // Configurar el escenario
    stage.setTitle("Visualizador de Árboles");
    stage.setScene(scene);

    // Tamaño inicial de la ventana
    stage.setWidth(1952);
    stage.setHeight(834);

    // Tamaño mínimo para evitar que se rompa el layout
    stage.setMinWidth(1952);
    stage.setMinHeight(834);

    // Centrar ventana en pantalla
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
