package com.leetcode.www;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange { // LC 632
    /**
     * Runtime: 63 ms, faster than 45.76% of Java online submissions for Smallest Range.
     * Memory Usage: 49.8 MB, less than 80.73% of Java online submissions for Smallest Range.
     *
     * https://leetcode.com/problems/smallest-range/discuss/277474/Short-and-Simple-Java-code-using-a-PriorityQueue
     *
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        if (nums == null || nums.size() == 0) {
            return res;
        }

        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(l -> l.get(0)));

        int curMax = Integer.MIN_VALUE;

        for (List<Integer> subList: nums) {
            if (subList.isEmpty()) {
                return res;
            }
            minHeap.offer(subList);
            curMax = Math.max(subList.get(0), curMax);
        }

        int rangeDiff = Integer.MAX_VALUE;
        while (minHeap.size() == nums.size()) {
            if (curMax - minHeap.peek().get(0) < rangeDiff) {
                rangeDiff = curMax - minHeap.peek().get(0);
                res[0] = minHeap.peek().get(0);
                res[1] = curMax;
            }

            List<Integer> temp = minHeap.poll();
            temp.remove(0);
            if (temp.size() > 0) {
                curMax = Math.max(temp.get(0), curMax);
                minHeap.offer(temp);
            }
        }
        return res;
    }

    /**
     * Runtime: 59 ms, faster than 54.30% of Java online submissions for Smallest Range.
     * Memory Usage: 52.5 MB, less than 67.99% of Java online submissions for Smallest Range.
     *
     * https://leetcode.com/problems/smallest-range/discuss/284820/Java-PriorityQueue
     *
     * @param nums
     * @return
     */
    public int[] smallestRangeV0(List<List<Integer>> nums) {
        PriorityQueue<int[]> min=new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[] res = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int i = 0; i < nums.size(); i++) {
            min.add(new int[]{i, 0, nums.get(i).get(0)}); // {listIndex, smallestNumIndexInList, smallestNum}
            res[0] = Math.min(res[0], nums.get(i).get(0));
            res[1] = Math.max(res[1], nums.get(i).get(0));
        }
        int max = res[1];
        while (!min.isEmpty()) {
            int[] listWithSmallestNum = min.poll();
            int smallestNum = listWithSmallestNum[2];
            if (max - smallestNum < res[1] - res[0]) { // smaller range
                res[0] = smallestNum;
                res[1] = max;
            }
            int listIndex = listWithSmallestNum[0];
            int smallestNumIndexInList = listWithSmallestNum[1];
            if (smallestNumIndexInList + 1 < nums.get(listIndex).size()) {
                int newSmallest = nums.get(listIndex).get(smallestNumIndexInList + 1);
                min.add(new int[]{listIndex, smallestNumIndexInList + 1, newSmallest});
                max = Math.max(max, newSmallest);
            } else {
                break;
            }
        }
        return res;
    }
//    Example 1:
//    Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
//    Output: [20,24]
//    Explanation:
//    List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
//    List 2: [0, 9, 12, 20], 20 is in range [20,24].
//    List 3: [5, 18, 22, 30], 22 is in range [20,24].
}
