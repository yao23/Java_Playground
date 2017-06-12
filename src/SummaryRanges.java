/**
 * Created by liyao on 6/11/17.
 */
import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> result = new ArrayList<>();
            int len = nums.length;
            if (len == 0) {
                return result;
            } else if (len == 1) {
                result.add(Integer.toString(nums[0]));
                return result;
            } else {
                boolean isConsecutive = false;
                int start = 0;
                for (int i = 1; i < len; i++) {
                    if (nums[i-1]+1 == nums[i]) { // consecutive
                        if (!isConsecutive) {
                            isConsecutive = true;
                        }
                    } else { // not consecutive
                        if (isConsecutive) { // pre list is consecutive
                            result.add(nums[start] + "->" + nums[i-1]);
                            isConsecutive = false;
                        } else {
                            result.add(Integer.toString(nums[i-1]));
                        }

                        start = i;
                    }
                }

                if (isConsecutive) { // pre list is consecutive
                    result.add(nums[start] + "->" + nums[len-1]);
                } else {
                    result.add(Integer.toString(nums[len-1]));
                }

                return result;
            }
        }
    }
}
