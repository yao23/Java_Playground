package com.leetcode.www;

import java.util.LinkedList;
import java.util.List;

public class NestedListWeightSum { // LC 339
    // BFS
    public int depthSum(List<NestedInteger> nestedList) { // beats 13.72%
        int level = 1, total = 0;
        while (nestedList.size() != 0){
            List<NestedInteger> next = new LinkedList<>();
            for (NestedInteger nInt : nestedList){
                if (nInt.isInteger()) {
                    total += nInt.getInteger() * level;
                } else {
                    next.addAll(nInt.getList());
                }
            }
            level++;
            nestedList = next;
        }
        return total;
    }

    // DFS
    public int depthSumV1(List<NestedInteger> nestedList) { // beats 60.44%
        return helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> list, int depth)    {
        int ret = 0;
        for (NestedInteger e: list) {
            ret += e.isInteger()? e.getInteger() * depth: helper(e.getList(), depth + 1);
        }
        return ret;
    }

    private int sum = 0;
    private int depth = 1;

    public int depthSumV0(List<NestedInteger> nestedList) { // beats 2.02%
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
