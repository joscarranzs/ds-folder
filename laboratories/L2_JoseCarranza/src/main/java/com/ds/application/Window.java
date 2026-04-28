package com.ds.application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

public class Window {
  private Stage stage;
  private Scene scene;

  private final String DEFAULT_TITLE = "Visualizador de Árboles";

  public Window(Pane root, double width, double height) {
    this.stage = new Stage();
    this.scene = new Scene(root, width, height);
    stage.setScene(scene);
    stage.setTitle(DEFAULT_TITLE);
    stage.setMinWidth(1100);
    stage.setMinHeight(700);
    stage.centerOnScreen();
  }

  public void setNewTitle(String title) {
    stage.setTitle(title);
  }

  public void updateRoot(Pane newRoot) {
    if (Platform.isFxApplicationThread()) {
      scene.setRoot(newRoot);
    } else {
      Platform.runLater(() -> scene.setRoot(newRoot));
    }
  }

  public void show() {
    stage.show();
  }
}
