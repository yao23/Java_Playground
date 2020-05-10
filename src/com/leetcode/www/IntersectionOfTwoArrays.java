import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays { // LC 349 (FB)
    /**
     * Runtime: 2 ms, faster than 99.35% of Java online submissions for Intersection of Two Arrays.
     * Memory Usage: 39.4 MB, less than 6.75% of Java online submissions for Intersection of Two Arrays.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        Set<Integer> res;
        if (len1 <= len2) {
            res = getIntersection(nums1, nums2);
        } else {
            res = getIntersection(nums2, nums1);
        }
        int[] resArr = new int[res.size()];
        int i = 0;
        for (Integer num : res) {
            resArr[i] = num;
            i++;
        }
        return resArr;
    }

    private Set<Integer> getIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int value : nums1) {
            set1.add(value);
        }
        for (int cur : nums2) {
            if (set2.add(cur)) {
                if (!set1.add(cur)) {
                    res.add(cur);
                }
            }
        }
        return res;
    }

    /**
     * Built-in Set Intersection
     *
     * Time complexity : O(n+m) in the average case and O(n×m) in the worst case when load factor is high enough.
     *
     * Space complexity : O(n+m) in the worst case when all elements in the arrays are different.
     *
     * Runtime: 2 ms, faster than 99.35% of Java online submissions for Intersection of Two Arrays.
     * Memory Usage: 40.1 MB, less than 6.75% of Java online submissions for Intersection of Two Arrays.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionV1(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }
}

// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]

// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]

// Input: [], [1,1]
// Output: [1]
// Expected: []
