package com.leetcode.www;

import java.util.List;

public class NestedListWeightSum { // LC 339
    private int sum = 0;
    private int depth = 1;

    public int depthSum(List<NestedInteger> nestedList) { // beats 2.02%
        for (int i = 0; i < nestedList.size(); i++) {
            NestedInteger cur = nestedList.get(i);
            if (cur.isInteger()) {
                sum += (depth * cur.getInteger());
            } else {
                depth++;
                depthSum(cur.getList());
                depth--;
            }
        }
        return sum;
    }
}
