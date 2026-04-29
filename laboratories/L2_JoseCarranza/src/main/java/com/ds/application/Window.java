package com.ds.application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

/**
 * Clase que encapsula una ventana de JavaFX (Stage) y su Scene asociada.
 *
 * Esta clase proporciona métodos simples para actualizar el root de la escena
 * y para mostrar la ventana. Está pensada como una envoltura mínima para
 * centralizar operaciones de ventana en la aplicación de visualización de
 * árboles.
 */
public class Window {
  private Stage stage;
  private Scene scene;

  private final String DEFAULT_TITLE = "Visualizador de Árboles";

  /**
   * Crea una nueva ventana con el Pane raíz indicado y tamaño inicial.
   *
   * @param root   el Pane raíz que contendrá los nodos visuales
   * @param width  ancho inicial de la ventana
   * @param height alto inicial de la ventana
   */
  public Window(Pane root, double width, double height) {
    this.stage = new Stage();
    this.scene = new Scene(root, width, height);
    stage.setScene(scene);
    stage.setTitle(DEFAULT_TITLE);
    stage.setMinWidth(1100);
    stage.setMinHeight(700);
    stage.centerOnScreen();
  }

  /**
   * Actualiza el título de la ventana.
   *
   * @param title nuevo título a mostrar en la barra de la ventana
   */
  public void setNewTitle(String title) {
    stage.setTitle(title);
  }

  /**
   * Reemplaza el Pane raíz de la Scene.
   *
   * Si la llamada se realiza desde el hilo de JavaFX (hilo de la UI) el cambio se
   * aplica inmediatamente. Si proviene de otro hilo, se programa la operación
   * mediante Platform.runLater para que se ejecute de forma segura en el hilo
   * de la interfaz.
   *
   * @param newRoot nuevo Pane raíz que se establecerá en la Scene
   */
  public void updateRoot(Pane newRoot) {
    if (Platform.isFxApplicationThread()) {
      scene.setRoot(newRoot);
    } else {
      Platform.runLater(() -> scene.setRoot(newRoot));
    }
  }

  /**
   * Muestra la ventana en pantalla.
   */
  public void show() {
    stage.show();
  }
}
