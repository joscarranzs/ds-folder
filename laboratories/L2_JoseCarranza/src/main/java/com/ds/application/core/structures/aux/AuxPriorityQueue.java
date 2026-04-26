package com.ds.application.core.structures.aux;

/**
 * Simple binary-heap priority queue implementation using AuxComparator.
 * Not thread-safe. Minimal API: offer, poll, peek, size, isEmpty.
 */
public class AuxPriorityQueue<T> implements AuxQueue<T> {
    private Object[] heap;
    private int size;
    private AuxComparator<T> comparator;

    private static final int DEFAULT_CAPACITY = 16;

    public AuxPriorityQueue(AuxComparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private T heapAt(int idx) {
        return (T) heap[idx];
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            Object[] newHeap = new Object[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    @Override
    public boolean offer(T item) {
        ensureCapacity();
        heap[size] = item;
        siftUp(size++);
        return true;
    }

    @Override
    public T poll() {
        if (size == 0) return null;
        T result = heapAt(0);
        heap[0] = heap[--size];
        heap[size] = null;
        siftDown(0);
        return result;
    }

    @Override
    public T peek() {
        return size == 0 ? null : heapAt(0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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
