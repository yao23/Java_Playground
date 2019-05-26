package com.leetcode.www;

public class FruitIntoBaskets { // LC 904
    /**
     * Runtime: 7 ms, faster than 90.10% of Java online submissions for Fruit Into Baskets.
     * Memory Usage: 45 MB, less than 98.86% of Java online submissions for Fruit Into Baskets.
     *
     *https://leetcode.com/problems/fruit-into-baskets/discuss/298563/Java-Minimum-Sliding-Window
     *
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        int[] fruits = new int[tree.length + 1];

        int uniqueFruitNum = 0;

        int left = 0;
        int right = 0;

        int maxNumFruit = 0;

        while (right < tree.length) {
            if (fruits[tree[right]]++ == 0) {
                uniqueFruitNum++;
            }

            while (uniqueFruitNum > 2) {
                if (--fruits[tree[left++]] == 0) {
                    uniqueFruitNum--;
                }
            }

            maxNumFruit = Math.max(maxNumFruit, right - left + 1);
            right++;
        }

        return maxNumFruit;
    }
}
