package com.ds.application.core.structures.aux;

/**
 * Cola de prioridad mínima implementada mediante un montículo (binary heap).
 * <p>
 * Esta implementación es ligera y no es segura para uso concurrente.
 * Proporciona las operaciones básicas necesarias: {@code offer}, {@code poll},
 * {@code peek}, {@code size} e {@code isEmpty}.
 *
 * @param <T> tipo de elementos almacenados
 */
public class AuxPriorityQueue<T> implements AuxQueue<T> {
    private Object[] heap;
    private int size;
    private AuxComparator<T> comparator;

    private static final int DEFAULT_CAPACITY = 16;

    /**
     * Crea una nueva cola de prioridad usando el comparador proporcionado.
     *
     * @param comparator comparador que define el orden entre elementos
     */
    public AuxPriorityQueue(AuxComparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    /**
     * Devuelve el elemento almacenado en la posición interna del heap.
     * Método interno; lanza ClassCastException si el tipo no coincide.
     *
     * @param idx índice dentro del array de heap
     * @return elemento en la posición indicada
     */
    private T heapAt(int idx) {
        return (T) heap[idx];
    }

    /**
     * Asegura que el array interno tenga capacidad para al menos un elemento
     * más; si no la tiene, duplica la capacidad.
     */
    private void ensureCapacity() {
        if (size >= heap.length) {
            Object[] newHeap = new Object[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    /**
     * Inserta un elemento en la cola con la prioridad definida por el
     * comparador. El elemento se sitúa en la posición adecuada para
     * mantener la propiedad del heap.
     *
     * @param item elemento a insertar
     * @return true si la inserción se realizó correctamente
     */
    @Override
    public boolean offer(T item) {
        ensureCapacity();
        heap[size] = item;
        siftUp(size++);
        return true;
    }

    /**
     * Extrae y devuelve el elemento con la prioridad más alta (mínima
     * según el comparador). Devuelve null si la cola está vacía.
     *
     * @return elemento extraído o null si no hay elementos
     */
    @Override
    public T poll() {
        if (size == 0) return null;
        T result = heapAt(0);
        heap[0] = heap[--size];
        heap[size] = null;
        siftDown(0);
        return result;
    }

    /**
     * Consulta el elemento con prioridad más alta sin extraerlo.
     *
     * @return elemento en la cima del heap o null si la cola está vacía
     */
    @Override
    public T peek() {
        return size == 0 ? null : heapAt(0);
    }

    /**
     * Número de elementos actualmente almacenados en la cola.
     *
     * @return tamaño actual
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Indica si la cola no contiene elementos.
     *
     * @return true si está vacía
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ajusta hacia arriba el elemento en la posición indicada para mantener
     * la propiedad de heap (heapify-up).
     *
     * @param idx índice del elemento a ajustar
     */
    @SuppressWarnings("unchecked")
    private void siftUp(int idx) {
        int child = idx;
        while (child > 0) {
            int parent = (child - 1) / 2;
            T childVal = (T) heap[child];
            T parentVal = (T) heap[parent];
            if (comparator.compare(childVal, parentVal) < 0) {
                heap[child] = parentVal;
                heap[parent] = childVal;
                child = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Ajusta hacia abajo el elemento en la posición indicada para mantener
     * la propiedad de heap (heapify-down).
     *
     * @param idx índice desde el que comenzar el ajuste
     */
    @SuppressWarnings("unchecked")
    private void siftDown(int idx) {
        int parent = idx;
        while (true) {
            int left = parent * 2 + 1;
            int right = left + 1;
            if (left >= size) break;
            int smallest = left;
            if (right < size && comparator.compare((T) heap[right], (T) heap[left]) < 0) {
                smallest = right;
            }
            if (comparator.compare((T) heap[smallest], (T) heap[parent]) < 0) {
                Object tmp = heap[parent];
                heap[parent] = heap[smallest];
                heap[smallest] = tmp;
                parent = smallest;
            } else {
                break;
            }
        }
    }
}
