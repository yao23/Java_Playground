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

    public static List<List<Integer>> subsetsV1(int[] nums) { // beats 5.98%
        List<Integer> tmpRes = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            int numIdx = 0, resIdx = i;
            while (resIdx > 0) {
                if (resIdx % 2 == 1) {
                    tmpRes.add(nums[numIdx]);
                }
                resIdx >>= 1; // right shift, divided by 2 (bit operation)
                numIdx++;
            }
            result.add(new ArrayList<>(tmpRes));
            tmpRes.clear();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        List<List<Integer>> subsets = subsetsV1(nums);
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }


    // [1,2,3] => [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]

    // beats 28.38%
}
