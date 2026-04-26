package com.ds.application.core.structures.aux;

/**
 * Comparador funcional auxiliar.
 *
 * Se utiliza en lugar de {@code java.util.Comparator} para mantener la
 * independencia respecto a colecciones estándar de Java en el código core.
 */
public interface AuxComparator<T> {
    /**
     * Compara dos elementos.
     *
     * @param a elemento izquierdo
     * @param b elemento derecho
     * @return valor negativo si {@code a < b}, cero si son iguales, positivo si {@code a > b}
     */
    int compare(T a, T b);
}
