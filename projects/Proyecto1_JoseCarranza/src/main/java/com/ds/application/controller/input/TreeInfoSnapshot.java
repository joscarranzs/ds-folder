package com.ds.application.controller.input;

/**
 * Representa el estado basico del arbol para la vista.
 */
public class TreeInfoSnapshot {

  private final String parentNodes;
  private final String leafNodes;
  private final int depth;
  private final int degree;

  /**
   * Constructor para crear una instantánea de la información del árbol.
   *
   * @param parentNodes texto que representa los nodos padres del árbol.
   * @param leafNodes   texto que representa los nodos hoja del árbol.
   * @param depth       profundidad del árbol.
   * @param degree      grado del árbol.
   */
  public TreeInfoSnapshot(String parentNodes, String leafNodes, int depth, int degree) {
    this.parentNodes = parentNodes;
    this.leafNodes = leafNodes;
    this.depth = depth;
    this.degree = degree;
  }

  /** Getters para acceder a la información del árbol. */
  public String getParentNodes() {
    return parentNodes;
  }

  /**
   * Devuelve el texto que representa los nodos hoja del árbol.
   *
   * @return texto de los nodos hoja.
   */
  public String getLeafNodes() {
    return leafNodes;
  }

  /**
   * Devuelve la profundidad del árbol.
   *
   * @return profundidad del árbol.
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Devuelve el grado del árbol.
   *
   * @return grado del árbol.
   */
  public int getDegree() {
    return degree;
  }
}
