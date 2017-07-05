/**
 * Created by liyao on 7/1/17.
 */

import java.util.*;

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
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

        int result = 0;
        for (TimeElement timeElement : timeList) {
            if (timeElement.mode == 0) { // start
                result++;
            } else { // end
                result--;
            }
        }

        return result;
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
