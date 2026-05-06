package com.ds.application.core.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de un montón mínimo (min-heap) usando un ArrayList como respaldo.
 *
 * <p>La representación es la típica para heaps: el elemento raíz (mínimo) está en
 * el índice 0; para un nodo en índice i, el hijo izquierdo está en 2*i+1 y el
 * hijo derecho en 2*i+2. Los métodos públicos están nombrados en español conforme
 * a los requisitos: insertar, eliminar y remontar.</p>
 *
 * @param <T> tipo de elementos almacenados; debe implementar Comparable para poder
 *            comparar valores y mantener la propiedad del montón
 */
public class MinHeapTree<T extends Comparable<T>> {
    /**
     * Almacenamiento interno del montón. Mantiene los elementos en orden de nivel.
     */
    private final List<T> heap = new ArrayList<>();

    /**
     * Construye un montón mínimo vacío.
     */
    public MinHeapTree() {
    }

    /**
     * Devuelve una copia de la representación interna del montón como lista en
     * orden de niveles. Útil para dibujar o inspeccionar el estado del heap
     * sin exponer la estructura interna mutable.
     *
     * @return nueva ArrayList con los elementos del montón en orden de niveles
     */
    public List<T> toList() {
        return new ArrayList<>(heap);
    }

    /**
     * Inserta un elemento en el montón.
     *
     * <p>Agrega el elemento al final de la estructura subyacente (ArrayList) y
     * luego llama a {@link #siftUp(int)} para remontarlo hasta que la propiedad
     * de heap mínimo se cumpla: cada padre es menor o igual que sus hijos.</p>
     *
     * @param value elemento a insertar; no se realiza una comprobación explícita de null
     */
    public void insert(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    /**
     * Elimina y devuelve la raíz del montón (el elemento mínimo).
     *
     * <p>Si el montón está vacío, devuelve null. Si hay al menos un elemento, se
     * sustituye la raíz por el último elemento en la lista y se aplica
     * {@link #siftDown(int)} desde la raíz para restaurar la propiedad de heap.
     * Esto garantiza que después de la operación el resto de elementos sigan
     * cumpliendo la invariante del montón mínimo.</p>
     *
     * @return el elemento mínimo originalmente en la raíz, o null si el montón está vacío
     */
    public T delete() {
        if (heap.isEmpty()) return null;
        T root = heap.get(0);
        int last = heap.size() - 1;
        if (last == 0) {
            heap.remove(last);
            return root;
        }
        heap.set(0, heap.get(last));
        heap.remove(last);
        siftDown(0);
        return root;
    }

    /**
     * Remonta el elemento en el índice indicado para restaurar la propiedad de heap.
     *
     * <p>Esta operación es útil cuando el valor de un elemento ha cambiado fuera de
     * los métodos estándar (por ejemplo, si un cliente modifica un elemento ya
     * almacenado). Se aplica primero {@link #siftUp(int)} por si el elemento debe
     * subir (es menor que sus ancestros) y luego {@link #siftDown(int)} por si debe
     * bajar (es mayor que sus descendientes). Haciendo ambas se cubren ambos casos
     * sin necesidad de conocer cómo cambió el valor.</p>
     *
     * @param index índice del elemento a remontar; si está fuera de rango, no hace nada
     */
    public void remont(int index) {
        if (index < 0 || index >= heap.size()) return;
        siftUp(index);
        siftDown(index);
    }

    /**
     * Devuelve el número de elementos en el montón.
     *
     * @return tamaño actual del montón
     */
    public int size() {
        return heap.size();
    }

    /**
     * Indica si el montón está vacío.
     *
     * @return true si no contiene elementos, false en caso contrario
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Devuelve el elemento mínimo (raíz) sin eliminarlo.
     *
     * @return la raíz del montón o null si está vacío
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /**
     * Sube el elemento en el índice especificado hasta que la propiedad de heap
     * mínimo se cumpla respecto a sus ancestros.
     *
     * <p>Algoritmo: mientras el índice no sea la raíz, comparar el elemento con
     * su padre; si el padre es mayor, intercambiarlos y continuar con el índice
     * del padre. Esto asegura que el camino desde el índice original hasta la
     * raíz quede ordenado correctamente.</p>
     *
     * @param idx índice del elemento a subir
     */
    private void siftUp(int idx) {
        int i = idx;
        while (i > 0) {
            int parent = (i - 1) / 2;
            T current = heap.get(i);
            T p = heap.get(parent);
            if (p.compareTo(current) > 0) {
                heap.set(i, p);
                heap.set(parent, current);
                i = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Baja el elemento en el índice especificado hasta que la propiedad de heap
     * mínimo se cumpla respecto a sus descendientes.
     *
     * <p>Algoritmo: comparar el elemento con sus hijos izquierdo y derecho; si
     * alguno de los hijos es menor que el elemento, intercambiar con el hijo más
     * pequeño y continuar. Repetir hasta que el elemento sea menor o igual que
     * ambos hijos o hasta que alcance una hoja.</p>
     *
     * @param idx índice del elemento a bajar
     */
    private void siftDown(int idx) {
        int n = heap.size();
        int i = idx;
        while (true) {
            int left = 2 * i + 1;
            int right = left + 1;
            int smallest = i;
            if (left < n && heap.get(left).compareTo(heap.get(smallest)) < 0) smallest = left;
            if (right < n && heap.get(right).compareTo(heap.get(smallest)) < 0) smallest = right;
            if (smallest == i) break;
            T tmp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, tmp);
            i = smallest;
        }
    }
}
