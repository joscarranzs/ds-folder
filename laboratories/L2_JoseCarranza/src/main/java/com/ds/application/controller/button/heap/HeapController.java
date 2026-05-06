package com.ds.application.controller.button.heap;

import com.ds.application.core.trees.MaxHeapTree;
import com.ds.application.core.trees.MinHeapTree;
import com.ds.application.view.components.visualizers.HeapVisualizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de manejar las operaciones del montón (heap) y su
 * representación visual.
 *
 * Este controlador se encarga de insertar valores, eliminar la raíz, limpiar el
 * montón y actualizar la visualización del mismo. Además, maneja tanto el
 * montón mínimo como el máximo según el modo seleccionado.
 */
public class HeapController {
  private final HeapVisualizer visualizer;
  private MinHeapTree<Integer> minHeap;
  private MaxHeapTree<Integer> maxHeap;
  private List<Integer> elements;
  private final boolean minHeapMode;

  /**
   * Constructor que recibe el visualizador del montón y el modo (mínimo o
   * máximo).
   *
   * @param visualizer  visualizador para mostrar el montón.
   * @param minHeapMode true si se trabaja con un montón mínimo, false para un
   *                    montón máximo.
   */
  public HeapController(HeapVisualizer visualizer, boolean minHeapMode) {
    this.visualizer = visualizer;
    this.minHeapMode = minHeapMode;

    this.elements = new ArrayList<>();

    // Inicializo el montón según el modo seleccionado y la lista de elementos
    // vacía.
    if (minHeapMode) {
      minHeap = new MinHeapTree<>();
    } else {
      maxHeap = new MaxHeapTree<>();
    }

    // Dibujo inicial del montón vacío.
    draw();
  }

  /**
   * Inserta un valor en el montón y actualiza la visualización. El valor se
   * inserta en la estructura de datos correspondiente (mínimo o máximo) y luego
   * se actualiza la lista visual para reflejar el estado actual del montón.
   *
   * @param value valor a insertar en el montón.
   */
  public void insert(int value) {
    // Inserto el valor en la estructura de datos correspondiente según el modo
    // seleccionado.
    if (minHeapMode) {
      minHeap.insert(value);
    } else {
      maxHeap.insert(value);
    }

    // Actualizo la lista visual desde la estructura real del montón para asegurarme
    // de que refleja el estado actual después de la inserción.
    if (minHeapMode) {
      elements = minHeap.toList();
    } else {
      elements = maxHeap.toList();
    }

    draw();
  }

  /**
   * Elimina la raíz del montón (el elemento mínimo o máximo según el modo) y
   * actualiza la visualización. Si el montón está vacío, muestra un mensaje de
   * estado y no intenta eliminar nada.
   *
   * @return true si se eliminó un elemento, false si el montón estaba vacío.
   */
  public boolean deleteRoot() {
    // Si el montón está vacío, no se puede eliminar nada. Muestro un mensaje de
    // estado y retorno false.
    if (elements.isEmpty()) {
      showMessage("El monton esta vacio.");
      return false;
    }

    Integer deleted;

    // Elimino la raíz del montón correspondiente según el modo seleccionado. Si el
    // montón está vacío, el método delete() devolverá null, lo cual manejo para
    // mostrar un mensaje de estado adecuado.
    if (minHeapMode) {
      deleted = minHeap.delete();
    } else {
      deleted = maxHeap.delete();
    }

    // Si el método delete() devolvió null, significa que el montón estaba vacío.
    // Aunque esto no debería ocurrir porque ya verifiqué que la lista visual no
    // está vacía, lo manejo por seguridad para mostrar un mensaje de estado
    // adecuado.
    if (deleted == null) {
      showMessage("El monton esta vacio.");
      return false;
    }

    // Actualizo la lista visual desde la estructura real del montón para asegurarme
    // de que refleja el estado actual después de la eliminación.
    if (minHeapMode) {
      elements = minHeap.toList();
    } else {
      elements = maxHeap.toList();
    }

    draw();
    return true;
  }

  /**
   * Limpia el montón y la visualización. Este método borra todos los elementos
   * del
   * montón, reinicia la estructura de datos correspondiente (mínimo o máximo) y
   * limpia la visualización para mostrar un montón vacío.
   */
  public void clear() {
    elements.clear();

    // Reinicio la estructura de datos del montón según el modo seleccionado para
    // asegurarme de que esté vacía y lista para nuevas inserciones.
    if (minHeapMode) {
      minHeap = new MinHeapTree<>();
    } else {
      maxHeap = new MaxHeapTree<>();
    }

    visualizer.clear();
  }

  /**
   * Método auxiliar para actualizar la visualización del montón. Este método
   * toma la lista actual de elementos y la pasa al visualizador para que dibuje
   * el estado actual del montón.
   */
  private void draw() {
    visualizer.drawHeap(new ArrayList<>(elements));
  }

  /** Método auxiliar para mostrar mensajes de estado. Este método se puede usar para
   * mostrar mensajes al usuario, como cuando se intenta eliminar la raíz de un
   * montón vacío.
   *
   * @param msg mensaje a mostrar.
   */
  public void showMessage(String msg) {
    System.out.println(msg);
  }
}
