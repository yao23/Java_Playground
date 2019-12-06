package io.educative.www.Coding.P04_MergeIntervals;

import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class P04_02_InsertInterval {
    /**
     * Time complexity
     * As we are iterating through all the intervals only once, the time complexity of the above algorithm is O(N),
     * where ‘N’ is the total number of intervals.
     *
     * Space complexity
     * The space complexity of the above algorithm will be O(N) as we need to return a list containing all the merged intervals.
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty())
            return Arrays.asList(newInterval);

        List<Interval> mergedIntervals = new ArrayList<>();

        int i = 0;
        // skip (and add to output) all intervals that come before the 'newInterval'
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            mergedIntervals.add(intervals.get(i++));

        // merge all intervals that overlap with 'newInterval'
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }

        // insert the newInterval
        mergedIntervals.add(newInterval);

        // add all the remaining intervals to the output
        while (i < intervals.size())
            mergedIntervals.add(intervals.get(i++));

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : P04_02_InsertInterval.insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : P04_02_InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("Intervals after inserting the new interval: ");
        for (Interval interval : P04_02_InsertInterval.insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}