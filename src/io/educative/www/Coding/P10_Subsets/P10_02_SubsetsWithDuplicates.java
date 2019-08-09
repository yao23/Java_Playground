package io.educative.www.Coding.P10_Subsets;

import java.util.*;

public class P10_02_SubsetsWithDuplicates {
    /**
     * Time complexity
     * Since, in each step, the number of subsets could double (if not duplicate) as we add each element to all the
     * existing subsets, the time complexity of the above algorithm is O(2^N), where ‘N’ is the total number of elements
     * in the input set. This also means that, in the end, we will have a total of O(2^N) subsets at the most.
     *
     * Space complexity
     * All the additional space used by our algorithm is for the output list. Since at most we will have a total of
     * O(2^N) subsets, the space complexity of our algorithm is also O(2^N).
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        // sort the numbers to handle duplicates
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int startIndex = 0, endIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            startIndex = 0;
            // if current and the previous elements are same, create new subsets only from the subsets
            // added in the previous step
            if (i > 0 && nums[i] == nums[i - 1])
                startIndex = endIndex + 1;
            endIndex = subsets.size() - 1;
            for (int j = startIndex; j <= endIndex; j++) {
                // create a new subset from the existing subset and add the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(j));
                set.add(nums[i]);
                subsets.add(set);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = P10_02_SubsetsWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = P10_02_SubsetsWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
