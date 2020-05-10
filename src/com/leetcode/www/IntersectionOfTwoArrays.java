import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays { // LC 349 (FB)
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
        // return Arrays.stream(list).mapToInt(i->i).toArray();

        // return list.stream().toArray(Integer[]::new);
    }

    /**
     * Not work for test case 1
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private Set<Integer> getIntersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (!set.add(nums2[i])) {
                res.add(nums2[i]);
            }
        }
        return res;
    }
}

// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2]

// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [9,4]

// Input: [], [1,1]
// Output: [1]
// Expected: []
