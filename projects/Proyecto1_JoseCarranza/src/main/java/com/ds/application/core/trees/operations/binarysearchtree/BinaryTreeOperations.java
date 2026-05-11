package com.ds.application.core.trees.operations.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.ds.application.core.structures.BinaryTreeNode;

/**
 * Operaciones de consulta sobre un árbol binario de búsqueda que almacena
 * Integer en sus nodos. Incluye funciones de recorrido (pre/in/post), cálculo
 * de altura, grado, identificadores de padres/hojas/hermanos, LCI y LCIM, entre
 * otras operaciones relacionadas con la estructura del árbol.
 */
public class BinaryTreeOperations {

  private BinaryTreeNode root;

  public BinaryTreeOperations() {
    this.root = null;
  }

  public BinaryTreeOperations(BinaryTreeNode root) {
    this.root = root;
  }

  public void setRoot(BinaryTreeNode root) {
    this.root = root;
  }

  public BinaryTreeNode getRoot() {
    return root;
  }

  /**
   * Tamaño (número de nodos) del árbol.
   */
  public int size() {
    return sizeRec(root);
  }

  private int sizeRec(BinaryTreeNode node) {
    if (node == null)
      return 0;
    return 1 + sizeRec(node.getLeft()) + sizeRec(node.getRight());
  }

  /**
   * Altura/profundidad del árbol (número de niveles).
   */
  public int height() {
    return heightRec(root);
  }

  private int heightRec(BinaryTreeNode node) {
    if (node == null)
      return 0;
    int lh = heightRec(node.getLeft());
    int rh = heightRec(node.getRight());
    return Math.max(lh, rh) + 1;
  }

  /**
   * Grado del árbol: máximo número de hijos entre todos los nodos (0..2).
   */
  public int degree() {
    return degreeRec(root);
  }

  private int degreeRec(BinaryTreeNode node) {
    if (node == null)
      return 0;
    int d = node.childCount();
    int dl = degreeRec(node.getLeft());
    int dr = degreeRec(node.getRight());
    return Math.max(d, Math.max(dl, dr));
  }

  /**
   * Recolecta nodos que son padres (tienen al menos un hijo).
   */
  public List<Integer> collectParentNodes() {
    List<Integer> out = new ArrayList<>();
    collectParentNodesRec(root, out);
    return out;
  }

  private void collectParentNodesRec(BinaryTreeNode node, List<Integer> out) {
    if (node == null)
      return;
    if (!node.isLeaf())
      out.add(node.getValue());
    collectParentNodesRec(node.getLeft(), out);
    collectParentNodesRec(node.getRight(), out);
  }

  /**
   * Recolecta nodos hoja (sin hijos).
   */
  public List<Integer> collectLeafNodes() {
    List<Integer> out = new ArrayList<>();
    collectLeafNodesRec(root, out);
    return out;
  }

  private void collectLeafNodesRec(BinaryTreeNode node, List<Integer> out) {
    if (node == null)
      return;
    if (node.isLeaf())
      out.add(node.getValue());
    collectLeafNodesRec(node.getLeft(), out);
    collectLeafNodesRec(node.getRight(), out);
  }

  /**
   * Recolecta pares de hermanos (cada par representado como una lista de dos
   * enteros).
   */
  public List<List<Integer>> collectSiblingPairs() {
    List<List<Integer>> out = new ArrayList<>();
    collectSiblingPairsRec(root, out);
    return out;
  }

  private void collectSiblingPairsRec(BinaryTreeNode node, List<List<Integer>> out) {
    if (node == null)
      return;
    BinaryTreeNode l = node.getLeft();
    BinaryTreeNode r = node.getRight();
    if (l != null && r != null) {
      List<Integer> pair = new ArrayList<>();
      pair.add(l.getValue());
      pair.add(r.getValue());
      out.add(pair);
    }
    collectSiblingPairsRec(l, out);
    collectSiblingPairsRec(r, out);
  }

  /**
   * Indica si un valor corresponde a la raíz.
   */
  public boolean isRoot(int value) {
    return root != null && root.getValue() == value;
  }

  /**
   * Indica si un valor corresponde a un nodo padre (tiene al menos un hijo).
   */
  public boolean isParent(int value) {
    BinaryTreeNode n = findNode(root, value);
    return n != null && !n.isLeaf();
  }

  /**
   * Indica si un valor corresponde a un nodo hoja.
   */
  public boolean isLeaf(int value) {
    BinaryTreeNode n = findNode(root, value);
    return n != null && n.isLeaf();
  }

  /**
   * Devuelve el grado (número de hijos) de un nodo identificado por su valor.
   */
  public int nodeDegree(int value) {
    BinaryTreeNode n = findNode(root, value);
    if (n == null)
      return -1;
    return n.childCount();
  }

  /**
   * Indica si un valor está presente en el árbol.
   *
   * @param value valor a buscar
   * @return {@code true} si el valor existe en el árbol
   */
  public boolean contains(int value) {
    return findNode(root, value) != null;
  }

  /**
   * Devuelve el nivel (profundidad) de un nodo identificado por su valor.
   * La raíz tiene nivel 1. Si no se encuentra retorna -1.
   *
   * @param value valor del nodo
   * @return nivel del nodo o -1 si no existe
   */
  public int getLevel(int value) {
    return getLevelRec(root, value, 1);
  }

  private int getLevelRec(BinaryTreeNode node, int value, int level) {
    if (node == null)
      return -1;
    if (node.getValue() == value)
      return level;
    int l = getLevelRec(node.getLeft(), value, level + 1);
    if (l != -1)
      return l;
    return getLevelRec(node.getRight(), value, level + 1);
  }

  // Encuentra un nodo por su valor (asumiendo valores únicos)
  private BinaryTreeNode findNode(BinaryTreeNode node, int value) {
    if (node == null)
      return null;
    if (node.getValue() == value)
      return node;
    BinaryTreeNode left = findNode(node.getLeft(), value);
    if (left != null)
      return left;
    return findNode(node.getRight(), value);
  }

  /**
   * Devuelve una lista de nodos por nivel (lista de listas; cada sublista
   * contiene
   * los valores en ese nivel, empezando por nivel 1 = raíz).
   */
  public List<List<Integer>> nodesByLevel() {
    List<List<Integer>> out = new ArrayList<>();
    if (root == null)
      return out;
    Queue<BinaryTreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      int sz = q.size();
      List<Integer> level = new ArrayList<>();
      for (int i = 0; i < sz; i++) {
        BinaryTreeNode n = q.poll();
        level.add(n.getValue());
        if (n.getLeft() != null)
          q.add(n.getLeft());
        if (n.getRight() != null)
          q.add(n.getRight());
      }
      out.add(level);
    }
    return out;
  }

  /**
   * Longitud de camino interno (LCI): suma de las profundidades de todos los
   * nodos.
   */
  public long internalPathLength() {
    return internalPathLengthRec(root, 0);
  }

  private long internalPathLengthRec(BinaryTreeNode node, int depth) {
    if (node == null)
      return 0L;
    long sum = depth;
    sum += internalPathLengthRec(node.getLeft(), depth + 1);
    sum += internalPathLengthRec(node.getRight(), depth + 1);
    return sum;
  }

  /**
   * Media de longitud de camino interno (LCIM): LCI / n (n = número de nodos, si
   * n>0).
   */
  public double averageInternalPathLength() {
    int n = size();
    if (n == 0)
      return 0.0;
    return (double) internalPathLength() / (double) n;
  }

  /**
   * Recorrido en-orden (left, root, right).
   * Devuelve la lista de valores en el orden en-orden; este recorrido es
   * utilizado también por otras operaciones que analizan la estructura del árbol.
   *
   * @return lista de valores visitados en orden en-orden
   */
  public List<Integer> inOrder() {
    List<Integer> out = new ArrayList<>();
    inOrderRec(root, out);
    return out;
  }

  private void inOrderRec(BinaryTreeNode node, List<Integer> out) {
    if (node == null)
      return;
    inOrderRec(node.getLeft(), out);
    out.add(node.getValue());
    inOrderRec(node.getRight(), out);
  }

  /**
   * Variante de inOrder que devuelve una representación en texto separada por
   * espacios (ej. "1 3 6 8").
   *
   * @return cadena con los valores en orden en-orden separados por espacios
   */
  public String inOrderString() {
    List<Integer> l = inOrder();
    return formatListAsText(l);
  }

  /**
   * Recorrido pre-orden (root, left, right).
   *
   * @return lista de valores visitados en pre-orden
   */
  public List<Integer> preOrder() {
    List<Integer> out = new ArrayList<>();
    preOrderRec(root, out);
    return out;
  }

  private void preOrderRec(BinaryTreeNode node, List<Integer> out) {
    if (node == null)
      return;
    out.add(node.getValue());
    preOrderRec(node.getLeft(), out);
    preOrderRec(node.getRight(), out);
  }

  /**
   * Variante de preOrder que devuelve una representación en texto separada por
   * espacios.
   *
   * @return cadena con los valores en pre-orden separados por espacios
   */
  public String preOrderString() {
    return formatListAsText(preOrder());
  }

  /**
   * Recorrido post-orden (left, right, root).
   *
   * @return lista de valores visitados en post-orden
   */
  public List<Integer> postOrder() {
    List<Integer> out = new ArrayList<>();
    postOrderRec(root, out);
    return out;
  }

  private void postOrderRec(BinaryTreeNode node, List<Integer> out) {
    if (node == null)
      return;
    postOrderRec(node.getLeft(), out);
    postOrderRec(node.getRight(), out);
    out.add(node.getValue());
  }

  /**
   * Variante de postOrder que devuelve una representación en texto separada
   * por espacios.
   *
   * @return cadena con los valores en post-orden separados por espacios
   */
  public String postOrderString() {
    return formatListAsText(postOrder());
  }

  /**
   * Genera una representación del árbol usando anidación de paréntesis.
   * Formato: nodo(izquierdo, derecho)
   * Ejemplo: 10(5(3,),20(,30))
   */
  public String toParenthesisString() {
    if (root == null)
      return "";
    StringBuilder sb = new StringBuilder();
    buildParenthesisRec(root, sb);
    return sb.toString();
  }

  private void buildParenthesisRec(BinaryTreeNode node, StringBuilder sb) {
    if (node == null)
      return;

    // valor del nodo
    sb.append(node.getValue());

    // si tiene hijos, agregar estructura
    if (node.getLeft() != null || node.getRight() != null) {
      sb.append("(");

      // hijo izquierdo
      if (node.getLeft() != null) {
        buildParenthesisRec(node.getLeft(), sb);
      }

      sb.append(",");

      // hijo derecho
      if (node.getRight() != null) {
        buildParenthesisRec(node.getRight(), sb);
      }

      sb.append(")");
    }
  }

  /**
   * Formatea una lista de objetos convirtiéndolos a cadena y separando por un
   * espacio. Si la lista es vacía o nula devuelve cadena vacía.
   *
   * @param list lista a formatear
   * @return representación en texto separada por espacios
   */
  public String formatListAsText(List<?> list) {
    if (list == null || list.isEmpty())
      return "";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
      if (i > 0)
        sb.append(" ");
      sb.append(list.get(i));
    }
    return sb.toString();
  }

}
