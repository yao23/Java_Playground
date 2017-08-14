package com.clouds.www;

import java.util.Comparator;

public class HeapSort {
    /**
     * Max Haep
     *
     * 1. Heapify
     * 2. Swap head and tail
     * 3. siftUp & siftDown
     *
     * time: O(nlogn + n), space: O(n), stable: No
     *
     * based on selection
     */

    private int size;
    private E[] queue;
    private Comparator<E> comparator;

    private void heapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--) {
            siftDown(i, (E)(queue[i]));
        }
    }

    private void siftDown(int k, E x) {
        siftDownUsingComparator(k, x);
    }

    private void siftDownUsingComparator(int k, E x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    comparator.compare((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (E) c) <= 0) {
                break;
            }

            queue[k] = (E)c;
            k = child;
        }
        queue[k] = x;
    }

    class E {

    }
}
