public class MissingElementInSortedArray { // 1060
    /**
     * Runtime: 1 ms, faster than 41.21% of Java online submissions for Missing Element in Sorted Array.
     * Memory Usage: 46.3 MB, less than 100.00% of Java online submissions for Missing Element in Sorted Array.
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int diff = nums[i] - nums[i - 1] - 1;
            if (diff >= k) {
                return nums[i - 1] + k;
            } else {
                k -= diff;
            }
        }
        return nums[len - 1] + k;
    }
}
