import java.util.*;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) { // beats 1.46%
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
            List<Integer> tmpRes = new ArrayList<>(list);
            res.add(tmpRes);
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if (set.add(nums[i])) {
                list.add(nums[i]);
                swap(nums, pos, i);
                helper(nums, res, list, pos + 1); // pos + 1 (pos: cur level)
                swap(nums, pos, i);
                list.remove(list.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUniqueV1(int[] nums) { // beats 1.46%
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        helperV1(nums, res, list, 0, set);
        return res;
    }

    private void helperV1(int[] nums, List<List<Integer>> res, List<Integer> list, int pos, Set<List<Integer>> set) {
        if (pos == nums.length) {
            List<Integer> tmpRes = new ArrayList<>(list);
            if (set.add(tmpRes)) {
                res.add(tmpRes);
            }
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, pos, i);
            helperV1(nums, res, list, pos + 1, set); // pos + 1 (pos: cur level)
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public List<List<Integer>> permuteUniqueV0(int[] nums) { // beats 63.02%
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[nums.length];
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, used, list, res);
        return res;
    }

    public void dfs(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            dfs(nums, used, list, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}

// [1,1,2] => [[1,1,2],[1,2,1],[2,1,1]]