package com.ds.application.core.structures;

public class SimpleList<T> {
  private T[] elements;
  private int size;
  private final int DEFAULT_CAPACITY = 1;

  @SuppressWarnings("unchecked")
  public SimpleList() {
    this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
  }

  public void add(T element) {
    if (size == elements.length) {
      resize();
    }
    elements[size++] = element;
  }

  public void resize() {
    int newCapacity = elements.length * 2;
    @SuppressWarnings("unchecked")
    T[] newElements = (T[]) new Object[newCapacity];
    System.arraycopy(elements, 0, newElements, 0, size);
    elements = newElements;
  }

  public T get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    return elements[index];
  }

  public void remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }
    elements[--size] = null;
  }

  public int size() {
    return size;
  }
}
