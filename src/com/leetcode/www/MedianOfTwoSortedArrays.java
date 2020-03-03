package com.leetcode.www;

public class MedianOfTwoSortedArrays { // LC 4
    /**
     * Binary Search Tree - Cut Rule and Half Range Control
     *
     * Recursion is easy to handle left and right parts
     *
     * a[mid] < b[mid], then half of a and b numbers < b[mid], so ((m+n)/2 + 1) numbers < b[mid],
     * move to b's left and a's right for next level recursion
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            return Integer.MIN_VALUE / 1.0;
        }
        int len = nums1.length + nums2.length;
        if ((len & 1) == 1) { // odd
            return findKthSmallest(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, len / 2 + 1) / 1.0;
        } else { // even
            return (findKthSmallest(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, len / 2) +
                        findKthSmallest(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, len / 2 + 1)) / 2.0;
        }
    }

    private int findKthSmallest(int[] a, int[] b, int aStart, int aEnd, int bStart, int bEnd, int k) {
        // corner case
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
        int aVal = (aMid >= a.length) ? Integer.MAX_VALUE : a[aMid];
        int bVal = (bMid >= b.length) ? Integer.MAX_VALUE : b[bMid];

        if (aVal <= bVal) {
            return findKthSmallest(a, b, aMid + 1, aEnd, bStart, bMid - 1, k - k / 2);
        } else {
            return findKthSmallest(a, b, aStart, aMid - 1, bMid + 1, bEnd, k - k / 2);
        }
    }
}

// nums1 = [1, 3], nums2 = [2] => The median is 2.0
// nums1 = [1, 2], nums2 = [3, 4] => The median is (2 + 3)/2 = 2.5

// beats 69.74%