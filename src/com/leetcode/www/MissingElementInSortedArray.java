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

    /**
     * Wrong test case
     *
     * @param nums
     * @param k
     * @return
     */
    public int missingElementV1(int[] nums, int k) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int m = (r - l) / 2 + l;
            int diff = 0, idxDiff = 0;
            if (m > l) {
                diff = nums[m] - nums[l] - 1;
                idxDiff = m - l;
            } else {
                if (l == 0) {
                    return nums[l] + k;
                } else {
                    diff = nums[l] - nums[l - 1] - 1;
                    idxDiff = 1;
                }
            }

            if (diff >= k) {
                if (idxDiff == 1) {
                    if (m > l) {
                        return nums[l] + k;
                    } else {
                        return nums[l - 1] + k;
                    }
                } else {
                    r = m - 1;
                }
            } else {
                if (idxDiff > 1) {
                    diff -= (idxDiff - 1);
                }
                k -= diff;
                l = m + 1;
            }

        }
        return nums[len - 1] + k;
    }
}
