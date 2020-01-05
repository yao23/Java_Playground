package io.educative.www.Coding.P04_MergeIntervals;

import java.util.*;

class Meeting {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class P04_05_MinimumMeetingRooms {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(N*logN), where ‘N’ is the total number of meetings.
     * This is due to the sorting that we did in the beginning. Also, while iterating the meetings we might need to
     * poll/offer meeting to the priority queue. Each of these operations can take O(logN).
     * Overall our algorithm will take O(NlogN).
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) which is required for sorting. Also, in the worst case
     * scenario, we’ll have to insert all the meetings into the Min Heap (when all meetings overlap) which will also
     * take O(N) space. The overall space complexity of our algorithm is O(N).
     *
     * @param meetings
     * @return
     */
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.size() == 0)
            return 0;

        // sort the meetings by start time
        Collections.sort(meetings, (a, b) -> Integer.compare(a.start, b.start));

        int minRooms = 0;
        PriorityQueue<Meeting> minHeap =
                new PriorityQueue<>(meetings.size(), (a, b) -> Integer.compare(a.end, b.end));
        for (Meeting meeting : meetings) {
            // remove all meetings that have ended
            while (!minHeap.isEmpty() && meeting.start >= minHeap.peek().end)
                minHeap.poll();
            // add the current meeting into the minHeap
            minHeap.offer(meeting);
            // all active meeting are in the minHeap, so we need rooms for all of them.
            minRooms = Math.max(minRooms, minHeap.size());
        }
        return minRooms;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = P04_05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = P04_05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = P04_05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = P04_05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = P04_05_MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }
}