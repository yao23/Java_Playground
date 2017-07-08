/**
 * Created by liyao on 7/8/17.
 */
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> tmpRes = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, tmpRes, result); // O(2 ^ nums.length), each num could be or not in result (0/1)
        return result;
    }

    private void helper(int depth, int[] nums, List<Integer> tmpRes, List<List<Integer>> result) {
        // add copy of tmpRes into result, otherwise result will be empty since tmpRes is clear finally
        result.add(new ArrayList<Integer>(tmpRes));

        if (depth == nums.length) {
            return;
        } else {
            for (int i = depth; i < nums.length; i++) {
                tmpRes.add(nums[i]);
                helper(i + 1, nums, tmpRes, result);
                tmpRes.remove(tmpRes.size() - 1);
            }
        }
    }

    // [1,2,3] => [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]

    // beats 28.38%
}
