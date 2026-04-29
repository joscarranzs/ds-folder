package com.ds.application.ui.layouts;

import java.util.Arrays;
import java.util.Objects;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * Encapsulador de {@link javafx.scene.layout.HBox} que expone una API limitada
 * y segura para manipular el layout sin requerir que el resto de la
 * aplicación trabaje directamente con JavaFX fuera de los encapsuladores.
 */
public class HBoxLayout {
  private final HBox hbox;

  public HBoxLayout() {
    this.hbox = new HBox();
  }

  /**
   * Retorna el HBox interno usado como layout.
   *
   * @return el HBox interno
   */
  public HBox getLayout() {
    return hbox;
  }

  /** Añade un nodo al final del HBox. */
  public void add(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> hbox.getChildren().add(node));
  }

  /** Añade varios nodos al HBox. */
  public void addAll(Node... nodes) {
    Objects.requireNonNull(nodes, "nodes must not be null");
    runOnFx(() -> hbox.getChildren().addAll(Arrays.asList(nodes)));
  }

  /** Remueve un nodo del HBox. */
  public void remove(Node node) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> hbox.getChildren().remove(node));
  }

  /** Elimina todos los hijos del HBox. */
  public void clear() { runOnFx(() -> hbox.getChildren().clear()); }

  /** Establece el espacio entre hijos. */
  public void setSpacing(double spacing) { runOnFx(() -> hbox.setSpacing(spacing)); }

  /** Establece el padding (uniforme) del HBox. */
  public void setPadding(double all) { runOnFx(() -> hbox.setPadding(new Insets(all))); }

  /** Establece padding individual (top, right, bottom, left). */
  public void setPadding(double top, double right, double bottom, double left) {
    runOnFx(() -> hbox.setPadding(new Insets(top, right, bottom, left)));
  }

  /** Establece la alineación de los hijos dentro del HBox. */
  public void setAlignment(Pos pos) { runOnFx(() -> hbox.setAlignment(pos)); }

  /** Establece margen para un hijo (node no puede ser null). */
  public void setMargin(Node node, double all) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> HBox.setMargin(node, new Insets(all)));
  }

  /** Establece margen individual para un hijo. */
  public void setMargin(Node node, double top, double right, double bottom, double left) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> HBox.setMargin(node, new Insets(top, right, bottom, left)));
  }

  // --- internal helper ---
  private void runOnFx(Runnable action) {
    if (Platform.isFxApplicationThread()) action.run(); else Platform.runLater(action);
  }
}
