package com.leetcode.www; /**
 * Created by liyao on 7/1/17.
 */

import java.util.*;

public class MeetingRoomsII { // LC 253
    /**
     * Runtime: 2 ms, faster than 100.00% of Java online submissions for Meeting Rooms II.
     * Memory Usage: 35 MB, less than 91.13% of Java online submissions for Meeting Rooms II.
     *
     * https://leetcode.com/problems/meeting-rooms-ii/discuss/293855/Java%3A-2D-Array-Input-2ms.-Faster-than-100.-%40magicyuli-algorithm
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        int[] startTime = new int[intervals.length];
        int[] endTime = new int[intervals.length];

        for (int i = 0; i < intervals.length ; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }

        Arrays.sort(startTime);
        Arrays.sort(endTime);

        int noOfRooms = 0, endPtr = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (startTime[i] < endTime[endPtr]) {
                noOfRooms++;
            } else {
                endPtr++;
            }
        }

        return noOfRooms;
    }

    /**
     * Runtime: 9 ms, faster than 47.71% of Java online submissions for Meeting Rooms II.
     * Memory Usage: 36.6 MB, less than 77.33% of Java online submissions for Meeting Rooms II.
     *
     * https://leetcode.com/problems/meeting-rooms-ii/discuss/293745/Easy-to-understand-Java-solution-using-heap
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        int count = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1[0] == p2[0]) {
                return p1[1] - p2[1];
            } else {
                return p1[0] - p2[0];
            }
        });
        for (int[] interval : intervals) {
            pq.offer(new int[]{interval[0], 1});
            pq.offer(new int[]{interval[1], -1});
        }

        int min = 0;

        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            count += p[1];
            min = Math.max(count, min);
        }
        return min;
    }

    /**
     * Runtime: 38 ms, faster than 24.07% of Java online submissions for Meeting Rooms II.
     * Memory Usage: 35.5 MB, less than 90.30% of Java online submissions for Meeting Rooms II.
     *
     * https://leetcode.com/problems/meeting-rooms-ii/discuss/294029/Easy-to-understand-Java-solution
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[0]));

        int ans = 0;

        // priority queue keeps track of intervals for which room is assigned in order of their ending times
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));

        for (int i = 0; i < intervals.length; i++) {
            int[] a = intervals[i];
            if (q.isEmpty()) {
                q.add(a);
                ans++;
            } else if (a[0] < q.peek()[1]) {  // overlap means we need to assign a new room for current interval
                ans++;
                q.add(a);
            } else { // no overlap means the previous room is freed. So, we will remove previous interval from queue and add current interval
                q.remove();
                q.add(a);
            }
        }

        return ans;
    }

    /**
     * Runtime: 39 ms, faster than 17.24% of Java online submissions for Meeting Rooms II.
     * Memory Usage: 35 MB, less than 91.06% of Java online submissions for Meeting Rooms II.
     *
     * https://leetcode.com/problems/meeting-rooms-ii/discuss/67857/AC-Java-solution-using-min-heap
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV1(int[][] intervals) {
        Queue<Integer> queue = new PriorityQueue<>();
        if (intervals.length > 0) {
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            queue.add(intervals[0][1]);
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] >= queue.peek()) {
                    queue.remove();
                }
                queue.add(intervals[i][1]);
            }
        }
        return queue.size();
    }

    /**
     * Runtime: 39 ms, faster than 17.24% of Java online submissions for Meeting Rooms II.
     * Memory Usage: 36.4 MB, less than 78.98% of Java online submissions for Meeting Rooms II.
     *
     * @param intervals
     * @return
     */
    public int minMeetingRoomsV0(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int count = 1;
        queue.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < queue.peek()) {
                count++;
            } else {
                queue.poll();
            }

            queue.offer(intervals[i][1]); // append end time for next update
        }

        return count;
    }

    public int minMeetingRooms(Interval[] intervals) { // beats 44.57%
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int count = 1;
        queue.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < queue.peek()) {
                count++;
            } else {
                queue.poll();
            }

            queue.offer(intervals[i].end); // append end time for next update
        }

        return count;
    }

    public int minMeetingRoomsV1(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        List<TimeElement> timeList = new ArrayList<>();

        for (Interval interval : intervals) {
            timeList.add(new TimeElement(interval.start, 0));
            timeList.add(new TimeElement(interval.end, 1));
        }

        Collections.sort(timeList);

        int counter = 0;
        int max = 0;
        for (TimeElement timeElement : timeList) {
            if (timeElement.mode == 0) { // start
                counter++;
            } else { // end
                counter--;
            }
            if (counter > max) {
                max = counter;
            }
        }

        return max;
    }

    class TimeElement implements Comparable<TimeElement> {
        int time;
        int mode; // 0 for start, 1 for end
        TimeElement(int time, int mode) {
            this.time = time;
            this.mode = mode;
        }

        public int compareTo(TimeElement that) {
            if (this.time < that.time) {
                return -1;
            } else if (this.time == that.time) {
                if (this.mode == 1) { // end time
                    return -1;
                } else { // start time
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }
}
