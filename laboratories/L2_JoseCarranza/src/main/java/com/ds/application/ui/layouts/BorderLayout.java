package com.ds.application.ui.layouts;

import java.util.Objects;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * Encapsulador de {@link javafx.scene.layout.BorderPane}.
 *
 * <p>
 * Esta clase gestiona internamente un {@code BorderPane} y proporciona
 * métodos para modificar su contenido, márgenes y padding sin exponer el
 * pane subyacente. Todas las mutaciones sobre la vista se ejecutan en el
 * JavaFX Application Thread: si la llamada proviene de otro hilo se
 * programan mediante {@link javafx.application.Platform#runLater(Runnable)}.
 *
 * @see javafx.scene.layout.BorderPane
 */
public class BorderLayout {
  private final BorderPane borderPane;

  public BorderLayout() {
    this.borderPane = new BorderPane();
  }

  /**
   * Retorna el {@code BorderPane} interno que actúa como layout raíz de este
   * componente.
   *
   * <p>
   * La instancia devuelta se puede usar para crear la {@code Scene} o
   * para integrarla en la jerarquía de escenas. Se recomienda, siempre que
   * sea posible, usar los métodos del encapsulador para modificar el
   * contenido y evitar manipulaciones directas del {@code BorderPane}
   * desde código externo.
   *
   * @return el {@code BorderPane} interno que representa el layout
   */
  public BorderPane getLayout() {
    return borderPane;
  }

  // --- Position set/remove methods ---
  /**
   * Coloca un nodo en la región TOP (arriba) del {@code BorderPane}.
   *
   * <p>
   * Si se pasa {@code null}, la región TOP se eliminará. Las mutaciones se
   * ejecutan en el JavaFX Application Thread; si la llamada proviene de otro
   * hilo la operación se programará mediante {@link
   * javafx.application.Platform#runLater(Runnable)}.
   *
   * @param node el nodo a colocar en la región TOP, o {@code null} para
   *             eliminar la región
   */
  public void setTop(Node node) {
    runOnFx(() -> borderPane.setTop(node));
  }

  /**
   * Coloca un nodo en la región BOTTOM (abajo) del {@code BorderPane}.
   *
   * <p>
   * Si se pasa {@code null}, la región BOTTOM se eliminará. Las mutaciones
   * se ejecutan en el JavaFX Application Thread.
   *
   * @param node el nodo a colocar en la región BOTTOM, o {@code null} para
   *             eliminar la región
   */
  public void setBottom(Node node) {
    runOnFx(() -> borderPane.setBottom(node));
  }

  /**
   * Coloca un nodo en la región LEFT (izquierda) del {@code BorderPane}.
   *
   * <p>
   * Si se pasa {@code null}, la región LEFT se eliminará. Las mutaciones
   * se ejecutan en el JavaFX Application Thread.
   *
   * @param node el nodo a colocar en la región LEFT, o {@code null} para
   *             eliminar la región
   */
  public void setLeft(Node node) {
    runOnFx(() -> borderPane.setLeft(node));
  }

  /**
   * Coloca un nodo en la región RIGHT (derecha) del {@code BorderPane}.
   *
   * <p>
   * Si se pasa {@code null}, la región RIGHT se eliminará. Las mutaciones
   * se ejecutan en el JavaFX Application Thread.
   *
   * @param node el nodo a colocar en la región RIGHT, o {@code null} para
   *             eliminar la región
   */
  public void setRight(Node node) {
    runOnFx(() -> borderPane.setRight(node));
  }

  /**
   * Coloca un nodo en la región CENTER (centro) del {@code BorderPane}.
   *
   * <p>
   * Si se pasa {@code null}, la región CENTER se eliminará. Las mutaciones
   * se ejecutan en el JavaFX Application Thread.
   *
   * @param node el nodo a colocar en la región CENTER, o {@code null} para
   *             eliminar la región
   */
  public void setCenter(Node node) {
    runOnFx(() -> borderPane.setCenter(node));
  }

  /**
   * Remueve el nodo de la región TOP. Equivalente a {@code setTop(null)}.
   */
  public void clearTop() {
    runOnFx(() -> borderPane.setTop(null));
  }

  /**
   * Remueve el nodo de la región BOTTOM. Equivalente a {@code setBottom(null)}.
   */
  public void clearBottom() {
    runOnFx(() -> borderPane.setBottom(null));
  }

  /**
   * Remueve el nodo de la región LEFT. Equivalente a {@code setLeft(null)}.
   */
  public void clearLeft() {
    runOnFx(() -> borderPane.setLeft(null));
  }

  /**
   * Remueve el nodo de la región RIGHT. Equivalente a {@code setRight(null)}.
   */
  public void clearRight() {
    runOnFx(() -> borderPane.setRight(null));
  }

  /**
   * Remueve el nodo de la región CENTER. Equivalente a {@code setCenter(null)}.
   */
  public void clearCenter() {
    runOnFx(() -> borderPane.setCenter(null));
  }

  // --- Padding helpers ---
  /**
   * Establece padding individual (top, right, bottom, left) en píxeles.
   *
   * <p>
   * Las llamadas se ejecutan en el JavaFX Application Thread. Si se
   * invocan desde otro hilo la operación se programará mediante {@link
   * javafx.application.Platform#runLater(Runnable)}.
   *
   * @param top    padding superior en píxeles
   * @param right  padding derecho en píxeles
   * @param bottom padding inferior en píxeles
   * @param left   padding izquierdo en píxeles
   */
  public void setPadding(double top, double right, double bottom, double left) {
    runOnFx(() -> borderPane.setPadding(new Insets(top, right, bottom, left)));
  }

  /**
   * Establece padding uniforme (mismo valor para top/right/bottom/left) en
   * píxeles.
   *
   * @param all padding en píxeles que se aplicará en todas las direcciones
   */
  public void setPadding(double all) {
    runOnFx(() -> borderPane.setPadding(new Insets(all)));
  }

  // --- Margin helpers ---
  /**
   * Establece márgenes individuales para un nodo asociado a una región.
   *
   * <p>
   * El parámetro {@code node} no puede ser {@code null}. Si se pasa
   * {@code null} se lanzará una {@link NullPointerException}.
   *
   * @param node   nodo al que se aplicarán las márgenes (no nulo)
   * @param top    margen superior en píxeles
   * @param right  margen derecho en píxeles
   * @param bottom margen inferior en píxeles
   * @param left   margen izquierdo en píxeles
   * @throws NullPointerException si {@code node} es {@code null}
   */
  public void setMargin(Node node, double top, double right, double bottom, double left) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> BorderPane.setMargin(node, new Insets(top, right, bottom, left)));
  }

  /**
   * Establece margen uniforme para un nodo (mismo valor en las cuatro
   * direcciones).
   *
   * @param node nodo al que se aplicará la margen (no nulo)
   * @param all  margen en píxeles para todas las direcciones
   * @throws NullPointerException si {@code node} es {@code null}
   */
  public void setMargin(Node node, double all) {
    Objects.requireNonNull(node, "node must not be null");
    runOnFx(() -> BorderPane.setMargin(node, new Insets(all)));
  }

  // --- Internal helpers ---
  /**
   * Ejecuta la tarea en el JavaFX Application Thread. Si ya estamos en el
   * hilo de JavaFX, la tarea se ejecuta inmediatamente; en caso contrario se
   * programa mediante {@link javafx.application.Platform#runLater(Runnable)}.
   *
   * @param action la acción a ejecutar en el hilo de la UI
   */
  private void runOnFx(Runnable action) {
    if (Platform.isFxApplicationThread()) {
      action.run();
    } else {
      Platform.runLater(action);
    }
  }
}
