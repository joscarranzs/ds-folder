package com.ds.application.core.structures.aux;

/**
 * Functional interface for comparing two objects.
 */
public interface AuxComparator<T> {
    int compare(T a, T b);
}
