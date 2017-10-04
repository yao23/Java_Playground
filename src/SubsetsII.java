/**
 * Created by liyao on 7/8/17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII { // LC 90
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> tmpRes = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        helper(0, nums, tmpRes, result);

        return result;
    }

    private void helper(int depth, int[] nums, List<Integer> tmpRes, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(tmpRes));
        if (depth == nums.length) {
            return;
        } else {
            for (int i = depth; i < nums.length; i++) {
                tmpRes.add(nums[i]);
                helper(i + 1, nums, tmpRes, result);
                tmpRes.remove(tmpRes.size() - 1);

                // skip duplicates
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
    }

    // [1,2,2] => [[],[1],[1,2],[1,2,2],[2],[2,2]]

    // beats 29.56%
}
