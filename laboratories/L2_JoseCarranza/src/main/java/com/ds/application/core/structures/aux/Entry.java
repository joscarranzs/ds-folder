package com.ds.application.core.structures.aux;

/**
 * Entrada auxiliar clave-valor utilizada por {@link AuxMap}.
 *
 * @param <K> tipo de la clave
 * @param <V> tipo del valor
 */
public class Entry<K, V> {
  private K key;
  private V value;

  /** Crea una nueva entrada con clave y valor. */
  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public void setValue(V value) {
    this.value = value;
  }
}
