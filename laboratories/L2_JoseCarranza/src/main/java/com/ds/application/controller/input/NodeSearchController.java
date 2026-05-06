package com.ds.application.controller.input;

public class NodeSearchController {
  /**
   * Controla la ejecucion de busqueda de nodos en el arbol.
   */

  // Controlador para manejar la búsqueda de valores en el árbol a través del
  private final com.ds.application.controller.button.binary.NodeInspectorController nodeController;

  /**
   * Constructor que recibe el controlador de inspección de nodos.
   *
   * @param nodeController controlador para manejar la búsqueda de valores en el árbol.
   */
  public NodeSearchController(com.ds.application.controller.button.binary.NodeInspectorController nodeController) {
    this.nodeController = nodeController;
  }

  /**
   * Ejecuta la busqueda de un valor en el arbol.
   *
   * @param value valor a buscar.
   */
  public void search(int value) {
    nodeController.search(value);
  }
}
