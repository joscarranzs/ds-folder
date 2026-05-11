package com.ds.application.controller.input;

import com.ds.application.controller.button.binary.NodeInspectorController;

/**
 * Controla la insercion de valores en el arbol.
 */
public class NodeInsertController {
  // Controlador para manejar la inserción de valores en el árbol a través del
  // NodeInspectorController.
  private final NodeInspectorController nodeController;

  /**
   * Constructor que recibe el controlador de inspección de nodos.
   *
   * @param nodeController controlador para manejar la inserción de valores en el árbol.
   */
  public NodeInsertController(NodeInspectorController nodeController) {
    this.nodeController = nodeController;
  }

  /**
   * Inserta un valor en el arbol.
   *
   * @param value valor a insertar.
   */
  public void insert(int value) {
    nodeController.insert(value);
  }
}
