package com.ds.application.core.trees;

import com.ds.application.core.structures.BinaryTreeNode;

/**
 * Implementación de un Árbol Binario de Búsqueda (BST). Permite insertar,
 * eliminar y buscar
 * valores enteros. No permite valores duplicados.
 */
public class BinarySearchTree {
  private BinaryTreeNode root;

  /**
   * Constructor para crear un árbol vacío.
   */
  public BinarySearchTree() {
    this.root = null;
  }

  /**
   * Obtiene la raíz del árbol.
   *
   * @return nodo raíz del árbol
   */
  public BinaryTreeNode getRoot() {
    return root;
  }

  /**
   * Inserta un valor en el árbol. No inserta valores duplicados.
   *
   * @param value valor a insertar
   */
  public void insert(int value) {
    root = insertRec(root, value);
    /**
     * Después de la inserción, aseguramos que la raíz no tenga padre (en caso de
     * que se haya creado un nuevo nodo raíz). Esto es importante para mantener la
     * integridad de la estructura del árbol, especialmente si el nodo raíz cambia
     * durante la inserción.
     */
    if (root != null)
      root.setParent(null);
  }

  /**
   * Helper recursivo para insertar un valor en el árbol. Devuelve el nodo
   * actualizado después de la inserción.
   *
   * @param node  nodo actual en la recursión
   * @param value valor a insertar
   * @return nodo actualizado después de la inserción
   */
  private BinaryTreeNode insertRec(BinaryTreeNode node, int value) {
    /**
     * Si el nodo actual es null, hemos encontrado la posición para insertar el
     * nuevo
     * valor. Creamos un nuevo nodo con el valor dado y lo devolvemos.
     */
    if (node == null) {
      return new BinaryTreeNode(value);
    }

    Integer nodeVal = node.getValue();

    /**
     * Si el valor a insertar es menor que el valor del nodo actual, insertamos en
     * el
     * subárbol izquierdo. Si es mayor, insertamos en el subárbol derecho. Si es
     * igual, no hacemos nada (no se permiten duplicados).
     */
    if (value < nodeVal) {
      /**
       * Insertamos recursivamente en el subárbol izquierdo y actualizamos el enlace
       * del nodo actual al nuevo nodo insertado. Esto asegura que la estructura del
       * árbol se mantenga correctamente durante la recursión.
       */
      BinaryTreeNode left = insertRec(node.getLeft(), value);
      node.setLeft(left);
    } else if (value > nodeVal) {
      /**
       * Insertamos recursivamente en el subárbol derecho y actualizamos el enlace
       * del nodo actual al nuevo nodo insertado. Esto asegura que la estructura del
       * árbol se mantenga correctamente durante la recursión.
       */
      BinaryTreeNode right = insertRec(node.getRight(), value);
      node.setRight(right);
    }

    /**
     * Devolvemos el nodo actual (sin cambios) para mantener la estructura del árbol
     * durante la recursión. El nodo raíz se actualizará solo si se inserta un nuevo
     * nodo en una posición vacía.
     */
    return node;
  }

  /**
   * Elimina un valor del árbol si existe.
   *
   * @param value valor a eliminar
   */
  public void delete(int value) {
    root = deleteRec(root, value);
    if (root != null)
      root.setParent(null);
  }

  /**
   * Helper recursivo para eliminar un valor del árbol. Devuelve el nodo
   * actualizado
   * después de la eliminación.
   *
   * @param node nodo actual en la recursión
   * @param key  valor a eliminar
   * @return nodo actualizado después de la eliminación
   */
  private BinaryTreeNode deleteRec(BinaryTreeNode node, int key) {
    /**
     * Si el nodo actual es null, el valor a eliminar no se encuentra en el árbol.
     * Devolvemos null para indicar que no se ha realizado ningún cambio.
     */
    if (node == null)
      return null;

    Integer nodeVal = node.getValue();

    /**
     * Si el valor a eliminar es menor que el valor del nodo actual, buscamos en el
     * subárbol izquierdo. Si es mayor, buscamos en el subárbol derecho. Si es
     * igual, hemos encontrado el nodo a eliminar.
     */
    if (key < nodeVal) {
      /**
       * Eliminamos recursivamente en el subárbol izquierdo y actualizamos el enlace
       * del
       * nodo actual al nuevo nodo resultante de la eliminación. Esto asegura que la
       * estructura del árbol se mantenga correctamente durante la recursión.
       */
      BinaryTreeNode newLeft = deleteRec(node.getLeft(), key);
      node.setLeft(newLeft);
    } else if (key > nodeVal) {
      /**
       * Eliminamos recursivamente en el subárbol derecho y actualizamos el enlace del
       * nodo actual al nuevo nodo resultante de la eliminación. Esto asegura que la
       * estructura del árbol se mantenga correctamente durante la recursión.
       */
      BinaryTreeNode newRight = deleteRec(node.getRight(), key);
      node.setRight(newRight);
    } else {
      /** Caso 1: nodo con solo un hijo o sin hijos */
      if (node.getLeft() == null) {
        /**
         * Si el nodo no tiene hijo izquierdo, devolvemos el hijo derecho (que puede ser
         * null). Esto elimina el nodo actual y conecta su padre directamente con su
         * hijo derecho.
         */
        return node.getRight();
      } else if (node.getRight() == null) {
        /**
         * Si el nodo no tiene hijo derecho, devolvemos el hijo izquierdo. Esto elimina
         * el nodo actual y conecta su padre directamente con su hijo izquierdo.
         */
        return node.getLeft();
      } else {
        /** Caso 2: nodo con dos hijos */
        BinaryTreeNode succ = findMin(node.getRight());
        node.setValue(succ.getValue());
        /**
         * Eliminamos el nodo sucesor que acabamos de copiar. Esto asegura que no haya
         * duplicados en el árbol después de la eliminación.
         */
        BinaryTreeNode newRight = deleteRec(node.getRight(), succ.getValue());
        node.setRight(newRight);
      }
    }

    /**
     * Devolvemos el nodo actual (posiblemente modificado) para mantener la
     * estructura del
     * árbol durante la recursión. El nodo raíz se actualizará solo si se elimina el
     * nodo raíz o si se realiza una eliminación que afecta a la estructura del
     * árbol.
     */
    return node;
  }

  /**
   * Encuentra el nodo con el valor mínimo en un subárbol dado. Se utiliza para
   * encontrar el sucesor en la eliminación de nodos con dos hijos.
   *
   * @param node nodo raíz del subárbol
   * @return nodo con el valor mínimo en el subárbol
   */
  private BinaryTreeNode findMin(BinaryTreeNode node) {
    BinaryTreeNode current = node;

    /**
     * El nodo con el valor mínimo en un BST siempre se encuentra en el extremo
     * izquierdo del subárbol.
     */
    while (current.getLeft() != null) {
      current = current.getLeft();
    }
    return current;
  }

  /**
   * Indica si un valor está presente en el árbol.
   *
   * @param value valor a buscar
   * @return {@code true} si existe en el árbol; {@code false} en caso contrario
   */
  public boolean contains(int value) {
    return containsRec(root, value);
  }

  /**
   * Helper recursivo para buscar un valor en el árbol.
   *
   * @param node  nodo actual en la recursión
   * @param value valor a buscar
   * @return {@code true} si el valor se encuentra en el subárbol; {@code false}
   *         en caso contrario
   */
  private boolean containsRec(BinaryTreeNode node, int value) {
    /**
     * Si el nodo actual es null, hemos llegado a una hoja sin encontrar el valor.
     * Devolvemos false para indicar que el valor no se encuentra en el árbol.
     */
    if (node == null)
      return false;
    Integer nodeVal = node.getValue();

    /**
     * Si el valor del nodo actual es igual al valor buscado, hemos encontrado el
     * valor en el árbol. Devolvemos true para indicar que el valor existe en el
     * árbol.
     */
    if (value == nodeVal)
      return true;

    /**
     * Si el valor buscado es menor que el valor del nodo actual, continuamos la
     * búsqueda en el subárbol izquierdo. Si es mayor, continuamos en el
     * subárbol derecho. Esto aprovecha la propiedad de ordenamiento del BST para
     * reducir el espacio de búsqueda.
     */
    if (value < nodeVal)
      return containsRec(node.getLeft(), value);

    /**
     * Retorna la búsqueda en el subárbol derecho si el valor buscado es mayor que
     * el
     * valor del nodo actual.
     */
    return containsRec(node.getRight(), value);
  }
}
