import java.util.ArrayList;
import java.util.List;

class RangeModule {
    private List<int[]> ranges;

    public RangeModule() {
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