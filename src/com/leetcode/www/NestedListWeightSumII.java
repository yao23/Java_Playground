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
