import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/range-module/solutions/1286342/java-more-than-a-solution-with-treeset/
 */
class RangeModule { // LC 715, Runtime 105ms Beats 5.60%, Memory 51.8 MB Beats 10.33%
    TreeSet<int[]> ts;
    public RangeModule() {
        this.ts = new TreeSet<>((a,b) -> a[0] - b[0]);
    }

    public void addRange(int left, int right) {
        Iterator<int[]> iter = ts.headSet(new int[]{right, 0}, true).iterator();
        while (iter.hasNext()) {
            int[] temp = iter.next();
            if (temp[1] < left) continue;

            left = Math.min(left, temp[0]);
            right = Math.max(right, temp[1]);
            iter.remove();
        }

        ts.add(new int[]{left, right});
    }

    public boolean queryRange(int left, int right) {
        int[] floor = ts.floor(new int[]{left, 0});
        return floor != null && floor[1] >= right;
    }

    public void removeRange(int left, int right) {
        Iterator<int[]> iter = ts.headSet(new int[]{right, 0}, false).iterator();

        int[] front = null;
        int[] back = null;

        while (iter.hasNext()) {
            int[] temp = iter.next();
            if (temp[1] < left) continue;

            if (temp[0] < left) front = new int[]{temp[0], left};
            if (right < temp[1]) back = new int[]{right, temp[1]};
            iter.remove();
        }

        if (front != null) ts.add(front);
        if (back != null) ts.add(back);
    }
}

class RangeModule0 {
    private List<int[]> ranges;

    public RangeModule0() { // beat 5%, Runtime: 199 ms Memory Usage: 54.6 MB
        ranges = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        List<int[]> mergedRanges = new ArrayList<>();
        boolean inserted = false;

        for (int[] range : ranges) {
            if (range[1] < left) {
                mergedRanges.add(range);
            } else if (range[0] > right) {
                if (!inserted) {
                    mergedRanges.add(new int[]{left, right});
                    inserted = true;
                }
                mergedRanges.add(range);
            } else {
                left = Math.min(left, range[0]);
                right = Math.max(right, range[1]);
            }
        }

        if (!inserted) {
            mergedRanges.add(new int[]{left, right});
        }

        ranges = mergedRanges;
    }

    public boolean queryRange(int left, int right) {
        for (int[] range : ranges) {
            if (range[0] <= left && range[1] >= right) {
                return true;
            }
        }

        return false;
    }

    public void removeRange(int left, int right) {
        List<int[]> updatedRanges = new ArrayList<>();

        for (int[] range : ranges) {
            if (range[1] < left || range[0] > right) {
                updatedRanges.add(range);
            } else {
                if (range[0] < left) {
                    updatedRanges.add(new int[]{range[0], left});
                }
                if (range[1] > right) {
                    updatedRanges.add(new int[]{right, range[1]});
                }
            }
        }

        ranges = updatedRanges;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */