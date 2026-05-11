package com.ds.application.controller.button.binary;

public class DeletionController {

  private final NodeInspectorController nodeController;

  /**
   * Crea el controlador que delega la eliminacion de nodos.
   *
   * @param nodeController controlador principal del arbol.
   */
  public DeletionController(NodeInspectorController nodeController) {
    this.nodeController = nodeController;
  }

  /**
   * Solicita la eliminacion de un nodo por valor.
   *
   * @param value valor del nodo a eliminar.
   */
  public void delete(int value) {
    nodeController.deleteSelected(value);
  }
}
