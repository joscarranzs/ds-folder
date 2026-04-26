package com.ds.application.core.structures.aux;

/**
 * Implementación minimalista de una lista dinámica (array-backed).
 *
 * Soporta operaciones básicas: add, get, set, remove y size. Está
 * destinada a ser una estructura auxiliar sencilla para el módulo core.
 *
 * @param <T> tipo de elementos almacenados
 */
public class SimpleList<T> {
  private T[] elements;
  private int size;
  private final int DEFAULT_CAPACITY = 1;

  @SuppressWarnings("unchecked")
  public SimpleList() {
    this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  /** Añade un elemento al final de la lista, expandiendo la capacidad si es necesario. */
  public void add(T element) {
    if (size == elements.length) {
      resize();
    }
    elements[size++] = element;
  }

  /** Duplica la capacidad interna cuando se alcanza el límite. */
  public void resize() {
    int newCapacity = elements.length * 2;
    @SuppressWarnings("unchecked")
    T[] newElements = (T[]) new Object[newCapacity];
    System.arraycopy(elements, 0, newElements, 0, size);
    elements = newElements;
  }

  /** Obtiene el elemento en la posición indicada. */
  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    return elements[index];
  }

  /** Reemplaza el elemento en la posición indicada. */
  public void set(int index, T element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    elements[index] = element;
  }

  /** Elimina el elemento en la posición indicada y desplaza los elementos siguientes. */
  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    elements[--size] = null;
  }

  /** Devuelve el número de elementos almacenados. */
  public int size() {
    return size;
  }
}
