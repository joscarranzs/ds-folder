package com.ds.application.controller.input;

import com.ds.application.controller.button.binary.NodeInspectorController;

/**
 * Controla la actualizacion de datos generales del arbol.
 */
public class TreeInfoController {
  // Controlador para manejar la actualización de datos generales del árbol a
  // través del NodeInspectorController.
  private final NodeInspectorController nodeController;

  /**
   * Constructor que recibe el controlador de inspección de nodos.
   *
   * @param nodeController controlador para manejar la actualización de datos generales del árbol.
   */
  public TreeInfoController(NodeInspectorController nodeController) {
    this.nodeController = nodeController;
  }

  /**
   * Construye un resumen del arbol para mostrar en la barra inferior.
   *
   * @return datos del arbol para la vista.
   */
  public TreeInfoSnapshot buildSnapshot() {
    // Construye un resumen del árbol utilizando los métodos del NodeInspectorController para obtener la información necesaria.
    return new TreeInfoSnapshot(
        nodeController.getParentNodesText(),
        nodeController.getLeafNodesText(),
        nodeController.height(),
        nodeController.degree());
  }
}
