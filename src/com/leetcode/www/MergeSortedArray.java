public class MergeSortedArray { // LC 88 (Facebook)
    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Sorted Array.
     * Memory Usage: 39.3 MB, less than 5.94% of Java online submissions for Merge Sorted Array.
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int end = m + n - 1, end1 = m - 1, end2 = n - 1;

        while (end1 >= 0 && end2 >= 0) {
            if (nums1[end1] >= nums2[end2]) {
                nums1[end] = nums1[end1];
                end1--;
            } else {
                nums1[end] = nums2[end2];
                end2--;
            }
            end--;
        }

        while (end2 >= 0) {
            nums1[end] = nums2[end2];
            end2--;
            end--;
        }
    }
}
