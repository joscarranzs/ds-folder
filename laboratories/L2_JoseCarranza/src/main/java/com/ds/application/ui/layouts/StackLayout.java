package com.ds.application.ui.layouts;

import java.util.Arrays;
import java.util.Objects;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Encapsulador de {@link javafx.scene.layout.StackPane} que ofrece helpers para
 * gestionar hijos y padding sin exponer la implementación.
 */
public class StackLayout {
  private final StackPane pane;

  public StackLayout() {
    this.pane = new StackPane();
  }

  /** Retorna el StackPane interno que actúa como layout. */
  public StackPane getLayout() {
    return pane;
  }

  /** Añade un hijo al final (tope) del StackPane. */
  public void add(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> pane.getChildren().add(node));
  }

  /** Añade varios hijos al StackPane. */
  public void addAll(Node... nodes) {
    Objects.requireNonNull(nodes, "nodes must not be null");
    runOnFx(() -> pane.getChildren().addAll(Arrays.asList(nodes)));
  }

  /** Remueve un hijo del StackPane. */
  public void remove(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> pane.getChildren().remove(node));
  }

  /** Elimina todos los hijos. */
  public void clear() {
    runOnFx(() -> pane.getChildren().clear());
  }

  /** Establece padding uniforme. */
  public void setPadding(double all) {
    runOnFx(() -> pane.setPadding(new Insets(all)));
  }

  /** Establece padding individual. */
  public void setPadding(double top, double right, double bottom, double left) {
    runOnFx(() -> pane.setPadding(new Insets(top, right, bottom, left)));
  }

  private void runOnFx(Runnable action) {
    if (Platform.isFxApplicationThread())
      action.run();
    else
      Platform.runLater(action);
  }
}
