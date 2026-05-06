package com.ds.application.controller.button.binary;

import com.ds.application.core.structures.BinaryTreeNode;
import com.ds.application.core.trees.BinarySearchTree;
import com.ds.application.core.trees.operations.binarysearchtree.BinaryTreeOperations;
import com.ds.application.view.components.inspector.NodeInspector;
import com.ds.application.view.components.visualizers.BinaryTreeVisualizer;

import java.util.List;

public class NodeInspectorController {

  // Árbol principal
  private BinarySearchTree tree;

  // Operaciones del árbol
  private BinaryTreeOperations operations;

  // Componentes visuales
  private final BinaryTreeVisualizer visualizer;
  private final NodeInspector inspector;

  /**
   * Constructor que recibe el visualizador y el inspector para controlar la
   * interacción entre ambos.
   *
   * @param visualizer visualizador del árbol para mostrar los cambios.
   * @param inspector  inspector de nodos para mostrar información detallada.
   */
  public NodeInspectorController(BinaryTreeVisualizer visualizer, NodeInspector inspector) {
    this.visualizer = visualizer;
    this.inspector = inspector;

    // Inicializo el árbol vacío
    this.tree = new BinarySearchTree();

    // Inicializo operaciones con la raíz actual
    this.operations = new BinaryTreeOperations(tree.getRoot());

    // Conecto el click del nodo visual con el inspector
    this.visualizer.setOnNodeSelected(value -> {
      visualizer.highlightValue(value);
      updateInspector(value);
      inspector.updateStatus("Nodo seleccionado: " + value);
    });
  }

  /**
   * Inserta un valor y actualiza la interfaz
   *
   * @param value valor a insertar en el árbol.
   */
  public void insert(int value) {
    visualizer.clearHighlight();

    tree.insert(value);
    operations.setRoot(tree.getRoot());

    visualizer.drawTree(tree.getRoot());
    updateInspector(value);

    inspector.updateStatus("Insertado: " + value);
  }

  /**
   * Busca un valor y actualiza la interfaz
   *
   * @param value valor a buscar en el árbol.
   */
  public void search(int value) {
    operations.setRoot(tree.getRoot());

    // Primero intento animar el camino de búsqueda, si no se pudo (porque el valor
    // no existe) entonces solo resalto el nodo encontrado o limpio el resaltado.
    if (operations.contains(value)) {
      boolean animated = visualizer.animateSearchPath(value);

      // Si no se pudo animar (porque el nodo ya estaba resaltado por otra búsqueda)
      // entonces lo resalto directamente.
      if (!animated) {
        visualizer.highlightValue(value);
      }

      // Actualizo el inspector con la información del nodo encontrado
      updateInspector(value);
      inspector.updateStatus("Encontrado: " + value);

      // El nodo ya está resaltado por la animación o el resaltado directo, no hago
      // nada más.
    } else {
      visualizer.clearHighlight();
      inspector.updateStatus("No encontrado: " + value);
    }
  }

  /**
   * Elimina un valor y actualiza la interfaz
   *
   * @param value valor a eliminar del árbol.
   */
  public void deleteSelected(int value) {
    operations.setRoot(tree.getRoot());

    // Verifico que el valor exista antes de intentar eliminarlo para evitar
    // animaciones
    if (!operations.contains(value)) {
      inspector.updateStatus("No encontrado: " + value);
      return;
    }

    // Primero animo el camino de búsqueda para llegar al nodo a eliminar, así el
    // usuario ve el proceso de eliminación. Si el nodo ya estaba resaltado por otra
    // búsqueda, lo resalto directamente.
    boolean animated = visualizer.animateSearchPath(value);
    tree.delete(value);
    operations.setRoot(tree.getRoot());

    visualizer.clearHighlight();
    visualizer.drawTree(tree.getRoot());

    inspector.clearInfo();
    inspector.updateStatus("Eliminado: " + value);
  }

  /**
   * Limpia el árbol y la interfaz
   */
  public void clear() {
    tree = new BinarySearchTree();
    operations.setRoot(tree.getRoot());

    visualizer.clear();
    inspector.clearInfo();
  }

  /**
   * Métodos para obtener información del árbol y los nodos, usados por el
   * inspector
   * y otras partes de la interfaz.
   */
  public boolean contains(int value) {
    operations.setRoot(tree.getRoot());
    return operations.contains(value);
  }

  /**
   * Recorridos en texto usados para mostrar en el inspector
   * (aunque podrían usarse también para animar, pero prefiero las listas para
   * eso)
   */
  public String preOrderString() {
    operations.setRoot(tree.getRoot());
    return operations.preOrderString();
  }

  public String inOrderString() {
    operations.setRoot(tree.getRoot());
    return operations.inOrderString();
  }

  public String postOrderString() {
    operations.setRoot(tree.getRoot());
    return operations.postOrderString();
  }

  /**
   * Representacion en anidacion de parentesis del arbol completo.
   */
  public String parenthesisString() {
    operations.setRoot(tree.getRoot());
    return operations.toParenthesisString();
  }

  /**
   * Recorridos en listas usados para animar los caminos de búsqueda o mostrar en
   * otras partes de la interfaz
   * (aunque podrían usarse también para el inspector, pero prefiero las cadenas
   * para eso)
   */
  public List<Integer> preOrderList() {
    operations.setRoot(tree.getRoot());
    return operations.preOrder();
  }

  public List<Integer> inOrderList() {
    operations.setRoot(tree.getRoot());
    return operations.inOrder();
  }

  public List<Integer> postOrderList() {
    operations.setRoot(tree.getRoot());
    return operations.postOrder();
  }

  /**
   * Getters para acceder a la información del árbol y el visualizador desde otras
   * partes de la interfaz.
   */
  public BinaryTreeVisualizer getVisualizer() {
    return visualizer;
  }

  /**
   * Información general del árbol usada para mostrar en el inspector y otras
   * partes
   * de la interfaz
   */
  public int size() {
    operations.setRoot(tree.getRoot());
    return operations.size();
  }

  /**
   * La altura se define como el número de aristas en el camino más largo desde la
   * raíz hasta una hoja.
   */
  public int height() {
    operations.setRoot(tree.getRoot());
    return operations.height();
  }

  /**
   * El nivel se define como el número de aristas desde la raíz hasta el nodo. La
   * raíz está en nivel 0, sus hijos en nivel 1, etc.
   *
   * @param value valor del nodo para obtener su nivel.
   * @return nivel del nodo o -1 si no existe.
   */
  public int getLevel(int value) {
    operations.setRoot(tree.getRoot());
    return operations.getLevel(value);
  }

  /**
   * Devuelve el texto con los nodos padres y hojas del árbol para mostrar en el
   * inspector.
   */
  public String getParentNodesText() {
    operations.setRoot(tree.getRoot());
    return operations.formatListAsText(operations.collectParentNodes());
  }

  /**
   * Devuelve el texto con los nodos hoja del árbol para mostrar en el inspector.
   */
  public String getLeafNodesText() {
    operations.setRoot(tree.getRoot());
    return operations.formatListAsText(operations.collectLeafNodes());
  }

  /**
   * El grado de un árbol se define como el número máximo de hijos que tiene
   * cualquier nodo del árbol.
   *
   * @return grado del árbol.
   */
  public int degree() {
    operations.setRoot(tree.getRoot());
    return operations.degree();
  }

  /**
   * Actualiza la información del inspector para un nodo específico.
   *
   * @param value valor del nodo para actualizar su información en el inspector.
   */
  private void updateInspector(int value) {
    operations.setRoot(tree.getRoot());

    BinaryTreeNode node = findNode(tree.getRoot(), value);

    // Si el nodo no existe (lo que no debería pasar porque se llama desde un nodo
    // seleccionado), limpio el inspector.
    if (node == null) {
      inspector.clearInfo();
      return;
    }

    inspector.updateNodeInfo(
        value,
        operations.getLevel(value),
        operations.nodeDegree(value),
        getNodeType(value),
        getChildText(node.getLeft()),
        getChildText(node.getRight()));
  }

  /**
   * Método auxiliar para encontrar un nodo por su valor. Devuelve null si no se
   * encuentra.
   */
  private BinaryTreeNode findNode(BinaryTreeNode node, int value) {
    // Este método asume que el valor existe en el árbol, pero lo hago recursivo
    // para encontrar el nodo específico y obtener su información. Si el nodo es
    // null, devuelvo null para evitar errores en el inspector. Si el valor del nodo
    // actual es el que busco, lo devuelvo. Si el valor es menor, busco en el
    // subárbol izquierdo; si es mayor, busco en el subárbol derecho.
    if (node == null) {
      return null;
    }

    int currentValue = ((Number) node.getValue()).intValue();

    // Si el valor del nodo actual es el que busco, lo devuelvo.
    if (currentValue == value) {
      return node;
    }

    // Si el valor es menor, busco en el subárbol izquierdo; si es mayor, busco en
    // el subárbol derecho.
    if (value < currentValue) {
      return findNode(node.getLeft(), value);
    }

    // Si el valor es mayor, busco en el subárbol derecho.
    return findNode(node.getRight(), value);
  }

  /**
   * Devuelve el tipo de nodo (Raiz, Padre, Hoja o -) para mostrar en el
   * inspector.
   * Se asume que el valor existe en el árbol, pero si no se encuentra se devuelve
   * "-" para evitar errores en el inspector.
   */
  private String getNodeType(int value) {
    // El tipo de nodo se determina por su posición en el árbol. Si es la raíz, se
    // devuelve "Raiz". Si es una hoja (no tiene hijos), se devuelve "Hoja". Si es
    // un padre (tiene al menos un hijo), se devuelve "Padre". Si el nodo no se
    // encuentra (lo que no debería pasar porque se llama desde un nodo
    // seleccionado), se devuelve "-" para evitar errores en el inspector.
    if (operations.isRoot(value)) {
      return "Raiz";
    }

    // Si el nodo no se encuentra (lo que no debería pasar porque se llama desde un
    // nodo seleccionado), se devuelve "-" para evitar errores en el inspector.
    if (operations.isLeaf(value)) {
      return "Hoja";
    }

    // Si el nodo no se encuentra (lo que no debería pasar porque se llama desde un
    // nodo seleccionado), se devuelve "-" para evitar errores en el inspector.
    if (operations.isParent(value)) {
      return "Padre";
    }

    // Si el nodo no se encuentra (lo que no debería pasar porque se llama desde un
    // nodo seleccionado), se devuelve "-" para evitar errores en el inspector.
    return "-";
  }

  /**
   * Devuelve el texto con el valor del hijo o "-" si el hijo es null, para
   * mostrar
   * en el inspector.
   */
  private String getChildText(BinaryTreeNode child) {
    // Si el hijo es null, devuelvo "-" para mostrar en el inspector. Si no es null,
    // devuelvo su valor como texto.
    if (child == null) {
      return "-";
    }

    return String.valueOf(child.getValue());
  }
}
