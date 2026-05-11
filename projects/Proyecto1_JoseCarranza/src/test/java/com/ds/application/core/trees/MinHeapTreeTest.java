package com.ds.application.core.trees;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MinHeapTreeTest {

    private boolean isMinHeap(List<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < n && arr.get(i) > arr.get(left)) return false;
            if (right < n && arr.get(i) > arr.get(right)) return false;
        }
        return true;
    }

    @Test
    public void testInsertMaintainsMinHeap() {
        MinHeapTree<Integer> heap = new MinHeapTree<>();
        int[] values = {5, 3, 8, 1, 6, 2, 7};

        for (int v : values) {
            heap.insert(v);
            assertTrue("After insert, heap must satisfy min-heap property: " + heap.toList(), isMinHeap(heap.toList()));
        }
    }

    @Test
    public void testDeleteMaintainsMinHeap() {
        MinHeapTree<Integer> heap = new MinHeapTree<>();
        List<Integer> values = Arrays.asList(9, 4, 10, 1, 8, 3, 2);

        for (int v : values) heap.insert(v);

        while (!heap.isEmpty()) {
            assertTrue("Before delete, must be min-heap: " + heap.toList(), isMinHeap(heap.toList()));
            heap.delete();
        }
    }
}
