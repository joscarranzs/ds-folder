package com.ds.application.core.utils;

/**
 * Representa una pareja clave-valor utilizada en estructuras auxiliares.
 *
 * @param <K> tipo de la clave
 * @param <V> tipo del valor
 */
public class Entry<K, V> {
  private K key;
  private V value;

  /**
   * Crea una nueva entrada con clave y valor.
   *
   * @param key clave de la entrada
   * @param value valor asociado a la clave
   */
  public Entry(K key, V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Obtiene la clave de la entrada.
   *
   * @return la clave almacenada
   */
  public K getKey() {
    return key;
  }

  /**
   * Obtiene el valor asociado a la clave.
   *
   * @return el valor almacenado
   */
  public V getValue() {
    return value;
  }
}
