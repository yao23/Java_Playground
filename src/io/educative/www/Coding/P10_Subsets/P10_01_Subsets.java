package io.educative.www.Coding.P10_Subsets;

import java.util.*;

public class P10_01_Subsets {
    /**
     * Time complexity
     * Since, in each step, the number of subsets doubles as we add each element to all the existing subsets, the time
     * complexity of the above algorithm is O(2^N), where ‘N’ is the total number of elements in the input set. This
     * also means that, in the end, we will have a total of O(2^N) subsets.
     *
     * Space complexity
     * All the additional space used by our algorithm is for the output list. Since we will have a total of O(2^N)
     * subsets, the space complexity of our algorithm is also O(2^N).
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        // start by adding the empty subset
        subsets.add(new ArrayList<>());
        for (int currentNumber : nums) {
            // we will take all existing subsets and insert the current number in them to create new subsets
            int n = subsets.size();
            for (int i = 0; i < n; i++) {
                // create a new subset from the existing subset and insert the current element to it
                List<Integer> set = new ArrayList<>(subsets.get(i));
                set.add(currentNumber);
                subsets.add(set);
            }
        }
        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = P10_01_Subsets.findSubsets(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = P10_01_Subsets.findSubsets(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}
