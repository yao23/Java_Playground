import java.util.TreeMap;

public class FindRightInterval {
    public int[] findRightInterval(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        } else {
            int[] res = new int[intervals.length];
            // hashmap in order (key)
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();
            for (int i = 0; i < intervals.length; i++) {
                treeMap.put(intervals[i].start, i);
            }
            for (int i = 0; i < intervals.length; i++) {
                Integer ceilingIdx = treeMap.ceilingKey(intervals[i].end);
                res[i] = (ceilingIdx == null) ? -1 : treeMap.get(ceilingIdx);
            }
            return res;
        }
    }
}

// [[1,2]] => [-1]
// [[3,4], [2,3], [1,2]] => [-1, 0, 1]
// [[1,4], [2,3], [3,4]] => [-1, 2, -1]

// beats 29.48%