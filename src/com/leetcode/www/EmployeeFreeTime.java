package com.leetcode.www;

import java.util.*;

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

    /**
     * Runtime: 40 ms, faster than 54.99% of Java online submissions for Employee Free Time.
     * Memory Usage: 48 MB, less than 47.92% of Java online submissions for Employee Free Time.
     *
     * https://leetcode.com/problems/employee-free-time/discuss/287346/Java-sorting-solution
     *
     * @param schedule
     * @return
     */
    public int[][] employeeFreeTimeV1(int[][][] schedule) {
        List<int[]> intervals = new ArrayList();
        List<int[]> res = new ArrayList();

        for (int[][] employee : schedule) {
            for (int[] interval : employee) {
                intervals.add(interval);
            }
        }

        Collections.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int end = intervals.get(0)[1];
        for (int[] interval : intervals) {
            if (interval[0] > end) {
                res.add(new int[]{end, interval[0]});
            }

            end = Math.max(end, interval[1]);
        }

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /**
     * Runtime: 40 ms, faster than 54.99% of Java online submissions for Employee Free Time.
     * Memory Usage: 48 MB, less than 47.85% of Java online submissions for Employee Free Time.
     *
     * https://leetcode.com/problems/employee-free-time/discuss/289293/Java-PriorityQueue-detailed-comments
     *
     * @param schedule
     * @return
     */
    public int[][] employeeFreeTimeV0(int[][][] schedule) {
        // Invariant: pq contains list with postive length
        PriorityQueue<Deque<Interval>> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.peek().start));

        for (int[][] intervals : schedule) {
            Deque<Interval> queue = new ArrayDeque<>();
            for (int[] interval: intervals) {
                queue.add(new Interval(interval[0], interval[1]));
            }

            pq.add(queue);
        }

        List<Interval> ans = new ArrayList<>();
        Interval prev = new Interval(0, pq.peek().peek().start);

        while (pq.size() > 0) {
            Deque<Interval> temp = pq.remove();
            Interval next = temp.remove();
            if (temp.size() > 0) pq.add(temp);

            if (prev.end < next.start) {
                ans.add(new Interval(prev.end, next.start));
                prev = next;
            } else {
                prev.end = Math.max(prev.end, next.end);
            }
        }

        int[][] ret = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            ret[i][0] = ans.get(i).start;
            ret[i][1] = ans.get(i).end;
        }
        return ret;
    }
}
