package com.leetcode.www;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EmployeeFreeTime { // LC 759
    /**
     * Runtime: 43 ms, faster than 40.68% of Java online submissions for Employee Free Time.
     * Memory Usage: 47.4 MB, less than 73.01% of Java online submissions for Employee Free Time.
     *
     * similiar like Merge Interval
     *
     * @param schedule
     * @return
     */
    public int[][] employeeFreeTime(int[][][] schedule) {
        PriorityQueue<EmployeeInterval> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.interval[0]));
        for (int i = 0; i < schedule.length; i++) {
            minHeap.offer(new EmployeeInterval(schedule[i][0], i, 0));
        }
        List<int[]> list = new ArrayList<>();
        int[] preInterval = minHeap.peek().interval;
        while (!minHeap.isEmpty()) {
            EmployeeInterval topEmployee = minHeap.poll();
            if (topEmployee.interval[0] > preInterval[1]) { // no overlap
                list.add(new int[]{preInterval[1], topEmployee.interval[0]});
                preInterval = topEmployee.interval;
            } else {
                if (topEmployee.interval[1] > preInterval[1]) {
                    preInterval = topEmployee.interval;
                }
            }

            int nextIntervalIndex = topEmployee.intervalIndex + 1;
            if (nextIntervalIndex < schedule[topEmployee.employeeIndex].length) {
                minHeap.offer(new EmployeeInterval(schedule[topEmployee.employeeIndex][nextIntervalIndex], topEmployee.employeeIndex, nextIntervalIndex));
            }
        }

        int listSize = list.size();
        int[][] result = new int[listSize][2];
        for (int i = 0; i < listSize; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    class EmployeeInterval {
        int[] interval;
        int employeeIndex;
        int intervalIndex;
        public EmployeeInterval(int[] interval, int employeeIndex, int intervalIndex) {
            this.interval = interval;
            this.employeeIndex = employeeIndex;
            this.intervalIndex = intervalIndex;
        }
    }
}
