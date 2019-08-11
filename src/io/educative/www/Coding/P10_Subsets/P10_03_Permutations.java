package io.educative.www.Coding.P10_Subsets;

import java.util.*;

public class P10_03_Permutations {
    /**
     * Time complexity
     * We know that there are a total of N! permutations of a set with ‘N’ numbers. In the algorithm above, we are
     * iterating through the given set. In each iteration, we go through all the current permutations to insert a new
     * number in them. Since we have to push the remaining elements ahead while inserting a number to an array, this
     * means that line 17 will take O(N), which makes the overall time complexity of our algorithm O(N*N!).
     *
     * Space complexity
     * All the additional space used by our algorithm is for the result list and the queue to store the intermediate
     * permutations. If you see closely, at any time, we don’t have more than N! permutations between the result list
     * and the queue. Therefore the overall space complexity of our algorithm is O(N!).
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());
        for (int currentNumber : nums) {
            // we will take all existing permutations and add the current number to create new permutations
            int n = permutations.size();
            for (int i = 0; i < n; i++) {
                List<Integer> oldPermutation = permutations.poll();
                // create a new permutation by adding the current number at every position
                for (int j = 0; j <= oldPermutation.size(); j++) {
                    List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
                    newPermutation.add(j, currentNumber);
                    if (newPermutation.size() == nums.length)
                        result.add(newPermutation);
                    else
                        permutations.add(newPermutation);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> generatePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generatePermutationsRecursive(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private static void generatePermutationsRecursive(int[] nums, int index, List<Integer> currentPermutation,
                                                      List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(currentPermutation);
        } else {
            // create a new permutation by adding the current number at every position
            for (int i = 0; i <= currentPermutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<Integer>(currentPermutation);
                newPermutation.add(i, nums[index]);
                generatePermutationsRecursive(nums, index + 1, newPermutation, result);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = P10_03_Permutations.findPermutations(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }
}
