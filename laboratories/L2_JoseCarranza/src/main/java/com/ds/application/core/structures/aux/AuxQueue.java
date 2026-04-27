package com.ds.application.core.structures.aux;

/**
 * Interfaz mínima de cola utilizada por las estructuras auxiliares.
 *
 * Define las operaciones básicas que se esperan de una cola de prioridad
 * o de filas simples empleadas en el módulo core.
 */
public interface AuxQueue<T> {
    /** Añade un elemento a la cola. */
    boolean offer(T item);

    /** Extrae y devuelve el elemento según la política de la cola; null si vacía. */
    T poll();

    /** Consulta el elemento siguiente sin extraerlo; null si vacía. */
    T peek();

    /** Número de elementos actualmente en la cola. */
    int size();

    /** True si la cola está vacía. */
    boolean isEmpty();
}
