package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateIII { // LC 220
    /**
     * As a followup question, it naturally also requires maintaining a window of size k. When t == 0, it reduces to the
     * previous question so we just reuse the solution.
     * Since there is now a constraint on the range of the values of the elements to be considered duplicates, it reminds
     * us of doing a range check which is implemented in tree data structure and would take O(LogN) if a balanced tree
     * structure is used, or doing a bucket check which is constant time. We shall just discuss the idea using bucket here.
     * Bucketing means we map a range of values to the a bucket. For example, if the bucket size is 3, we consider 0, 1,
     * 2 all map to the same bucket. However, if t == 3, (0, 3) is a considered duplicates but does not map to the same
     * bucket. This is fine since we are checking the buckets immediately before and after as well. So, as a rule of thumb,
     * just make sure the size of the bucket is reasonable such that elements having the same bucket is immediately
     * considered duplicates or duplicates must lie within adjacent buckets. So this actually gives us a range of
     * possible bucket size, i.e. t and t + 1. We just choose it to be t and a bucket mapping to be num / t.
     * Another complication is that negative ints are allowed. A simple num / t just shrinks everything towards 0.
     * Therefore, we can just reposition every element to start from Integer.MIN_VALUE.
     *
     * beats 84.42%
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicateV0(int[] nums, int k, int t) { // beats 70.46%
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            if (d.containsKey(m)) {
                return true;
            }
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w) {
                return true;
            }
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w) {
                return true;
            }
            d.put(m, (long)nums[i]);
            if (i >= k) {
                d.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    private long getID(long i, long w) {
        return i < 0 ? (i + 1) / w - 1 : i / w;
    }
}

// [], 0, 0 => false
// [-2147483648,-2147483647], 3, 3 => true
// [-1, 2147483647], 1, 2147483647 => false