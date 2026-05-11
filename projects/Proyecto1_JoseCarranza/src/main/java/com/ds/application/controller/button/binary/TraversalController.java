package com.ds.application.controller.button.binary;

import com.ds.application.view.components.indicators.TreeInfoBar;
import com.ds.application.view.components.inspector.NodeInspector;

import java.util.List;

/**
 * Controlador encargado de manejar los recorridos del árbol binario.
 *
 * Este controlador se encarga de ejecutar los diferentes tipos de recorridos
 * (pre-orden, in-orden, pos-orden) y actualizar la interfaz para mostrar el
 * resultado del recorrido, así como animar los nodos en el orden
 * correspondiente.
 */
public class TraversalController {
  private final NodeInspectorController nodeController;
  private final NodeInspector inspector;
  private final TreeInfoBar infoBar;

  /**
   * Constructor que recibe los controladores necesarios para manejar los
   * recorridos y actualizar la interfaz.
   *
   * @param nodeController controlador para manejar los recorridos del árbol
   *                       binario.
   * @param inspector      componente de inspección de nodos para mostrar el
   *                       estado actual del recorrido.
   * @param infoBar        barra de información para mostrar el resultado del
   *                       recorrido.
   */
  public TraversalController(NodeInspectorController nodeController, NodeInspector inspector, TreeInfoBar infoBar) {
    this.nodeController = nodeController;
    this.inspector = inspector;
    this.infoBar = infoBar;
  }

  /** Ejecuta recorrido pre-orden y anima los nodos en ese orden */
  public void preorder() {
    showTraversal(
        "Pre-orden",
        nodeController.preOrderString(),
        nodeController.preOrderList());
  }

  /** Ejecuta recorrido in-orden y anima los nodos en ese orden */
  public void inorder() {
    showTraversal(
        "In-orden",
        nodeController.inOrderString(),
        nodeController.inOrderList());
  }

  /** Ejecuta recorrido pos-orden y anima los nodos en ese orden */
  public void postorder() {
    showTraversal(
        "Pos-orden",
        nodeController.postOrderString(),
        nodeController.postOrderList());
  }

  /**
   * Método auxiliar para mostrar el resultado del recorrido y animar los nodos
   */
  private void showTraversal(String name, String result, List<Integer> values) {
    // Si el árbol está vacío, mostrar un mensaje de estado y no intentar animar
    // nada.
    if (nodeController.size() == 0) {
      inspector.updateStatus("Arbol vacio.");
      return;
    }

    // Actualizar el estado del inspector y la barra de información con el resultado
    // del recorrido.
    inspector.updateStatus(name + ": " + result);
    infoBar.updateTraversal(name, result);

    // Animar los nodos en el orden del recorrido utilizando el método del
    // controlador de nodos.
    nodeController.getVisualizer().animateTraversal(values);
  }
}
