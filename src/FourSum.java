/**
 * Created by liyao on 6/10/17.
 */
import java.util.*;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 4) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < len - 3; i++) {
            while (i > 0 && i < len - 3 && nums[i-1] == nums[i]) { // prev same with cur in left side
                i++;
            }
            for (int j = i + 1; j < len - 2; j++) {
                while (j > 1 && j < len - 2 && nums[j-1] == nums[j] && j - i > 1) { // prev same with cur in left side
                    j++;
                }
                for (int k = j + 1; k < len - 1; k++) {
                    int l = len -1;
                    while (k < l) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum == target) {
                            List<Integer> tmpResult = new ArrayList<Integer>(Arrays.asList(nums[i],nums[j],nums[k],nums[l]));
                            result.add(tmpResult);

                            int left = nums[k], right = nums[l];
                            k++;
                            l--;

                            while (k < l && left == nums[k]) { // cur same with next in left side
                                k++;
                            }

                            while (l > k && right == nums[l]) { // prev same with cur in right side
                                l--;
                            }

                        } else if (sum < target) {
                            k++;
                        } else {
                            l--;
                        }
                    }
                }
            }
        }

        return result;
    }

    // [], 0 => []
    // [1,0,-1,0,-2,2], 0 => [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    // [-1,0,1,2,-1,-4], -1 => [[-4,0,1,2],[-1,-1,0,1]]
}
