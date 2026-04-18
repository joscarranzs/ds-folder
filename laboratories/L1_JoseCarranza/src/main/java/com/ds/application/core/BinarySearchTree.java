package com.ds.application.core;

import com.ds.application.core.utils.SimpleList;
import com.ds.application.core.utils.Entry;

/**
 * Implementa un árbol binario de búsqueda simple para valores enteros.
 * Esta clase mantiene la raíz del árbol y varias colecciones auxiliares.
 */
public class BinarySearchTree {
  private Node<Integer> root;
  private SimpleList<Node<Integer>> parentNodes;
  private SimpleList<Node<Integer>> leafNodes;
  private SimpleList<Entry<Node<Integer>, Integer>> dregreeNodes;
  private SimpleList<Entry<Integer, Node<Integer>>> nodesByLevel;
  private int ABDepth;
  private int LCI;
  private float LCIM;

  /**
   * Crea una nueva instancia de árbol binario vacía.
   */
  public BinarySearchTree() {
    this.root = null;
    this.parentNodes = new SimpleList<>();
    this.leafNodes = new SimpleList<>();
    this.dregreeNodes = new SimpleList<>();
    this.nodesByLevel = new SimpleList<>();
    this.ABDepth = 0;
    this.LCI = 0;
    this.LCIM = 0;
  }

  /**
   * Inserta un nuevo valor en el árbol binario de búsqueda.
   *
   * @param value valor entero que se añadirá al árbol
   */
  public void insertToTree(int value) {
    Node<Integer> newNode = new Node<>(value);
    if (root == null) {
      root = newNode;
    } else {
      insertNode(root, newNode);
    }
  }

  /**
   * Inserta un nodo en la posición correcta según las reglas del árbol binario de búsqueda.
   *
   * @param current nodo actual usado como referencia durante la inserción
   * @param newNode nodo que se intenta insertar
   */
  public void insertNode(Node<Integer> current, Node<Integer> newNode) {
    if (newNode.getData() < current.getData()) {
      if (current.getLeft() == null) {
        current.setLeft(newNode);
      } else {
        insertNode(current.getLeft(), newNode);
      }
    } else {
      if (current.getRight() == null) {
        current.setRight(newNode);
      } else {
        insertNode(current.getRight(), newNode);
      }
    }
  }
}
