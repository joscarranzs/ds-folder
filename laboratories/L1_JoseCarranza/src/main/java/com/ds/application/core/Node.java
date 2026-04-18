package com.ds.application.core;

/**
 * Representa un nodo dentro de un árbol binario de búsqueda.
 *
 * @param <T> tipo de dato almacenado en el nodo
 */
public class Node<T> {
  private T data;
  private Node<T> left;
  private Node<T> right;

  /**
   * Crea un nodo con el valor dado y sin hijos iniciales.
   *
   * @param data valor almacenado en el nodo
   */
  public Node(T data) {
    this.data = data;
    this.left = null;
    this.right = null;
  }

  /**
   * Obtiene el valor almacenado en el nodo.
   *
   * @return el dato del nodo
   */
  public T getData() {
    return data;
  }

  /**
   * Obtiene el hijo izquierdo del nodo.
   *
   * @return el nodo izquierdo o null si no existe
   */
  public Node<T> getLeft() {
    return left;
  }

  /**
   * Establece el hijo izquierdo del nodo solo si aún no existe uno.
   *
   * @param left nodo izquierdo a asignar
   */
  public void setLeft(Node<T> left) {
    if (this.left == null) {
      this.left = left;
    }
  }

  /**
   * Obtiene el hijo derecho del nodo.
   *
   * @return el nodo derecho o null si no existe
   */
  public Node<T> getRight() {
    return right;
  }

  /**
   * Establece el hijo derecho del nodo solo si aún no existe uno.
   *
   * @param right nodo derecho a asignar
   */
  public void setRight(Node<T> right) {
    if (this.right == null) {
      this.right = right;
    }
  }
}
