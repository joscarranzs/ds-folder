package com.ds.application.ui.layouts;

import java.util.Arrays;
import java.util.Objects;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Encapsulador de {@link javafx.scene.layout.VBox} con API controlada y segura
 * para manipular hijos, padding y márgenes.
 */
public class VBoxLayout {
  private final VBox vbox;

  public VBoxLayout() {
    this.vbox = new VBox();
  }

  /** Retorna el VBox interno que actúa como layout. */
  public VBox getLayout() { return vbox; }

  /** Añade un nodo al final del VBox. */
  public void add(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> vbox.getChildren().add(node));
  }

  /** Añade varios nodos al VBox. */
  public void addAll(Node... nodes) {
    Objects.requireNonNull(nodes, "nodes must not be null");
    runOnFx(() -> vbox.getChildren().addAll(Arrays.asList(nodes)));
  }

  /** Remueve un nodo del VBox. */
  public void remove(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> vbox.getChildren().remove(node));
  }

  /** Elimina todos los hijos del VBox. */
  public void clear() { runOnFx(() -> vbox.getChildren().clear()); }

  /** Establece espacio entre hijos. */
  public void setSpacing(double spacing) { runOnFx(() -> vbox.setSpacing(spacing)); }

  /** Establece padding (uniforme). */
  public void setPadding(double all) { runOnFx(() -> vbox.setPadding(new Insets(all))); }

  /** Establece padding individual (top,right,bottom,left). */
  public void setPadding(double top, double right, double bottom, double left) {
    runOnFx(() -> vbox.setPadding(new Insets(top, right, bottom, left)));
  }

  /** Establece la alineación de los hijos dentro del VBox. */
  public void setAlignment(Pos pos) { runOnFx(() -> vbox.setAlignment(pos)); }

  /** Establece margen uniforme para un hijo. */
  public void setMargin(Node node, double all) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> VBox.setMargin(node, new Insets(all)));
  }

  /** Establece margen individual para un hijo. */
  public void setMargin(Node node, double top, double right, double bottom, double left) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> VBox.setMargin(node, new Insets(top, right, bottom, left)));
  }

  private void runOnFx(Runnable action) { if (Platform.isFxApplicationThread()) action.run(); else Platform.runLater(action); }
}
