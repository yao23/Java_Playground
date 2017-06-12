/**
 * Created by liyao on 6/11/17.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int start, int end) {
        List<String> result = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            result.add("0->99");
            return result;
        } else if (len == 1) {
            int elem = nums[0];
            if (start <= elem && elem <= end) {
                if (start == elem) {
                    result.add((start+1) + "->" + end);
                } else if (end == elem) {
                    result.add(start + "->" + (end-1));
                } else {
                    result.add(start + "->" + (elem-1));
                    result.add((elem+1) + "->" + end);
                }
            } else {
                result.add(start + "->" + end);
            }
            return result;
        } else {
            Arrays.sort(nums);

            for (int i = 1; i < len; i++) {
                int pre = nums[i-1], cur = nums[i];
                if (pre + 1 == cur) { // consecutive
                    continue;
                } else { // not consecutive
                    int newStart = pre + 1, newEnd = cur -1;
                    if (newEnd < start || newStart > end) {
                        continue;
                    } else { // in the target range
                        if (newStart == newEnd) {
                            result.add(Integer.toString(newStart));
                        } else {
                            result.add(newStart + "->" + newEnd);
                        }
                    }
                }
            }

            return result;
        }
    }
}
