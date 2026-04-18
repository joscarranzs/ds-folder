package com.ds.application.core.utils;

/**
 * Implementa una lista simple basada en un array dinámico.
 * Esta clase sirve como contenedor auxiliar para otros componentes del laboratorio.
 *
 * @param <T> tipo de los elementos almacenados
 */
public class SimpleList<T> {
  private T[] elements;
  private int size;
  private final int DEFAULT_CAPACITY = 1;

  /**
   * Crea una lista vacía con capacidad inicial.
   */
  @SuppressWarnings("unchecked")
  public SimpleList() {
    this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  /**
   * Duplica la capacidad interna del arreglo cuando está lleno.
   */
  public void resize() {
    int newCapacity = elements.length * 2;
    @SuppressWarnings("unchecked")
    T[] newElements = (T[]) new Object[newCapacity];
    System.arraycopy(elements, 0, newElements, 0, size);
    elements = newElements;
  }

  /**
   * Agrega un elemento al final de la lista.
   *
   * @param element elemento a agregar
   */
  public void add(T element) {
    if (size == elements.length) {
      resize();
    }
    elements[size] = element;
    size++;
  }

  /**
   * Obtiene el elemento en la posición indicada.
   *
   * @param index índice del elemento
   * @return elemento en la posición solicitada
   * @throws IndexOutOfBoundsException si el índice es inválido
   */
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    return elements[index];
  }

  /**
   * Devuelve un arreglo con todos los elementos almacenados.
   *
   * @return arreglo con los elementos actuales
   */
  public T[] getAll() {
    @SuppressWarnings("unchecked")
    T[] result = (T[]) new Object[size];
    System.arraycopy(elements, 0, result, 0, size);
    return result;
  }

  /**
   * Retorna el número de elementos almacenados en la lista.
   *
   * @return tamaño actual de la lista
   */
  public int size() {
    return size;
  }
}
