/**
 * Created by liyao on 6/11/17.
 */
import java.util.List;
import java.util.ArrayList;

public class InsertInterval {
    /**
     * Definition for an interval.
     */
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) {
            return result;
        }

        Interval tmpInterval = newInterval;
        for (Interval interval : intervals) {
            if (tmpInterval == null) {
                result.add(interval);
            } else {
                if (tmpInterval.end < interval.start) {
                    Interval insertedInterval = tmpInterval;
                    result.add(insertedInterval);
                    result.add(interval);

                    tmpInterval = null; // update for next round merge
                } else {
                    if (interval.end < tmpInterval.start) {
                        result.add(interval);
                    } else {
                        int start = Math.min(interval.start, tmpInterval.start);
                        int end = Math.max(interval.end, tmpInterval.end);
                        tmpInterval = new Interval(start, end);
                    }
                }
            }
        }

        return result;
    }
}
