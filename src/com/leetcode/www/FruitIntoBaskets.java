package com.leetcode.www;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    /**
     * Runtime: 50 ms, faster than 50.42% of Java online submissions for Fruit Into Baskets.
     * Memory Usage: 50.5 MB, less than 53.31% of Java online submissions for Fruit Into Baskets.
     *
     * https://leetcode.com/problems/fruit-into-baskets/discuss/296768/JAVA-Sliding-window-approach
     *
     * Have two baskets (map of size two which say the type of fruit to the last index where the fruit was found)
     * A) Keep adding to the the appropriate baskets when :
     *
     * the map has empty baskets (size < 2)
     * the fruit to be added already exists in one of the baskets
     * B) when the 2 baskets already contain fruits and the fruit to be added is not of types found in either basket
     *
     * empty the basket in which there is a fruit that was not added recently
     * set the start to the next index
     * put the new fruit into that basket with its index
     * At every point compute the distance between the start and current index. Reset max as needed.
     *
     * start, max are set to 0 when beginning
     * start gets updated when a fruit is removed (B.2).
     *
     * @param tree
     * @return
     */
    public int totalFruitV0(int[] tree) {
        Map<Integer, Integer> typeToLastInd = new HashMap<>(2);
        int curStart = 0;
        int max = 0;

        for (int i = 0; i < tree.length; i++) {
            if (!typeToLastInd.containsKey(tree[i]) && typeToLastInd.size() == 2) {
                Iterator<Integer> it = typeToLastInd.values().iterator();
                curStart = Math.min(it.next(), it.next()) + 1;
                typeToLastInd.remove(tree[curStart - 1]);
            }

            typeToLastInd.put(tree[i], i);
            max = Math.max(max, i - curStart + 1);
        }

        return max;
    }
}
