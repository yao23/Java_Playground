/**
 * Created by liyao on 6/11/17.
 */
import java.util.List;
import java.util.ArrayList;

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) { // test case 4
            result.add(newInterval);
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

        if (tmpInterval != null) { // test case 5
            result.add(tmpInterval);
        }

        return result;
    }

    // [[]], [] => [[]]
    // [[1,3],[6,9]], [2,5] => [[1,5],[6,9]]
    // [1,2],[3,5],[6,7],[8,10],[12,16]], [4,9] => [[1,2],[3,10],[12,16]]
    // [[]], [2,3] => [[2,3]]
    // [[1,5]], [2,3] => [[1,5]]

    // beats 53.69%
}
