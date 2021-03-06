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

    /**
     * https://leetcode.com/problems/intersection-of-two-arrays/solution/
     *
     * Facebook interview - O(n) time and O(1) space (the resulting array of intersections is not taken into consideration)
     * You are told the lists are sorted.
     *
     * Cases to take into consideration include:
     * duplicates, negative values, single value lists, 0's, and empty list arguments.
     * Other considerations might include sparse arrays.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionV2(int[] nums1, int[] nums2) {
        Set<Integer> set;
        if (nums1.length <= nums2.length) {
            set = getIntersectionWithoutSet(nums1, nums2);
        } else {
            set = getIntersectionWithoutSet(nums2, nums1);
        }

        int [] res = new int[set.size()];
        int idx = 0;
        for (int s : set) {
            res[idx++] = s;
        }
        return res;
    }

    private Set<Integer> getIntersectionWithoutSet(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        Set<Integer> set = new HashSet<>();
        int p1 = 0, p2 = 0;
        while (p1 < len1 && p2 < len2) {
            while (p1 + 1 < len1 && nums1[p1 + 1] == nums1[p1]) {
                p1++;
            }
            while (p2 + 1 < len2 && nums2[p2 + 1] == nums2[p2]) {
                p2++;
            }
            if (nums1[p1] == nums2[p2]) {
                set.add(nums1[p1]);
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        return set;
    }
}

// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]

// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]

// Input: [], [1,1]
// Output: [1]
// Expected: []
