import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, res, list, 0);
        return res;
    }

    private void helper(int[] nums, List<List<Integer>> res, List<Integer> list, int pos) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1]) {
                continue;
            }

            list.add(nums[i]);
            swap(nums, pos, i);
            helper(nums, res, list, pos + 1); // pos + 1 (pos: cur level)
            swap(nums, pos, i);
            list.remove(list.size() - 1);

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
