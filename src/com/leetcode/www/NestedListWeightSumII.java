package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII { // LC 364
    public int depthSumInverse(List<NestedInteger> nestedList) { // beats 24.94%
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger()) {
                    unweighted += ni.getInteger();
                } else {
                    nextLevel.addAll(ni.getList());
                }
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }
}

// [[1,1],2,[1,1]] => 8 (four 1's at depth 1, one 2 at depth 2)
// [1,[4,[6]]] => 17 (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)