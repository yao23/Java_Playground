package com.leetcode.www; /**
 * Created by liyao on 6/26/17.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class MergeIntervals { // LC 56
    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size();
        if (size < 2) {
            return intervals;
        } else {
            List<Interval> result = new ArrayList<>();

            // com.leetcode.www.Interval Comparator
            class IntervalComparator implements Comparator<Interval> {
                @Override
                public int compare(Interval i1, Interval i2) {
                    if (i1.start < i2.start) {
                        return -1;
                    } else if (i1.start == i2.start) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }

            // sort intervals
            Collections.sort(intervals, new IntervalComparator());

            // add first interval into result
            result.add(intervals.get(0));

            // compare cur and next intervals
            for (int i = 1; i < size; i++) {
                Interval pre = result.get(result.size() - 1), cur = intervals.get(i);
                if (pre.end >= cur.start) { // if pre end larger than cur start, then merge them together
                    result.set(result.size() - 1, new Interval(Math.min(pre.start, cur.start), Math.max(pre.end, cur.end)));
                } else { // otherwise add cur into result list
                    result.add(cur);
                }
            }

            return result;
        }
    }

    // [] => [[]]
    // [[2,3]] => [[2,3]]
    // [[1,5],[2,3]] => [[1,5]]
    // [[1,3],[2,5],[6,9]] => [[1,5],[6,9]]
    // [1,2],[3,5],[4,9],[6,7],[8,10],[12,16]] => [[1,2],[3,10],[12,16]]
    // [[1,3],[2,6],[8,10],[15,18]] => [[1,6],[8,10],[15,18]]

    // beats 48.02%
}
