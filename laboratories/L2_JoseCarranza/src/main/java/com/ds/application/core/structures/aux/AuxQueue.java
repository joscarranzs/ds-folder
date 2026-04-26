package com.ds.application.core.structures.aux;

/**
 * Minimal queue interface used by auxiliary structures.
 */
public interface AuxQueue<T> {
    boolean offer(T item);
    T poll();
    T peek();
    int size();
    boolean isEmpty();
}
