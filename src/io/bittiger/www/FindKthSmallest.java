package io.bittiger.www;

public class FindKthSmallest {
    private int findKthSmallest(int[] a, int[] b, int aStart, int aEnd, int bStart, int bEnd, int k) {
        if (aStart >= a.length) {
            return b[bStart + k - 1];
        }
        if (bStart >= b.length) {
            return a[aStart + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }
        // throw the smaller half
        int aMid = aStart + k / 2 - 1;
        int bMid = bStart + k / 2 - 1;
        // do not throw the exceeding one
        int aVal = aMid >= a.length ? Integer.MAX_VALUE : a[aMid];
        int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];

        if (aVal <= bVal) {
            return findKthSmallest(a, b, aMid + 1, aEnd, bStart, bMid - 1, k - k / 2);
        } else {
            return findKthSmallest(a, b, aStart, aMid - 1, bMid + 1, bEnd, k - k / 2);
        }
    }
}
