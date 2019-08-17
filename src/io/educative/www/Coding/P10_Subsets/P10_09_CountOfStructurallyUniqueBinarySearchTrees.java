package io.educative.www.Coding.P10_Subsets;

import java.util.HashMap;
import java.util.Map;

public class P10_09_CountOfStructurallyUniqueBinarySearchTrees {
    /**
     * Time complexity
     * The time complexity of this algorithm will be exponential and will be similar to Balanced Parentheses.
     * Estimated time complexity will be O(n*2^n) but the actual time complexity ( O(4^n/\sqrt{n}) ) is bounded by the
     * Catalan number and is beyond the scope of a coding interview. See more details here.
     *
     * Space complexity
     * The space complexity of this algorithm will be exponential too, estimated O(2^n) but the actual will be
     * ( O(4^n/\sqrt{n}) ).
     *
     * @param n
     * @return
     */
    public int countTrees(int n) {
        if (n <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTrees(i - 1);
            int countOfRightSubtrees = countTrees(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        return count;
    }

    Map<Integer, Integer> map = new HashMap<>();

    /**
     * The time complexity of the memoized algorithm will be O(n^2), since we are iterating from ‘1’ to ‘n’ and
     * ensuring that each sub-problem is evaluated only once. The space complexity will be O(n) for the memoization map.
     *
     * @param n
     * @return
     */
    public int countTreesV1(int n) {
        if (map.containsKey(n))
            return map.get(n);

        if (n <= 1)
            return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            // making 'i' root of the tree
            int countOfLeftSubtrees = countTreesV1(i - 1);
            int countOfRightSubtrees = countTreesV1(n - i);
            count += (countOfLeftSubtrees * countOfRightSubtrees);
        }
        map.put(n, count);
        return count;
    }

    public static void main(String[] args) {
        P10_09_CountOfStructurallyUniqueBinarySearchTrees ct = new P10_09_CountOfStructurallyUniqueBinarySearchTrees();
        int count = ct.countTrees(3);
        System.out.print("Total trees: " + count);
    }
}
