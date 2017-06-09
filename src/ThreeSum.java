/**
 * Created by liyao on 6/8/17.
 */
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) { // Brute Force: Time Limit Exceeded
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            while (i > 0 && i < nums.length - 2 && nums[i-1] == nums[i]) {
                i++;
            }

            int j = i + 1, k = nums.length -1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> tmpResult = Arrays.asList(nums[i], nums[j], nums[k]);
                    result.add(tmpResult);

                    j++;
                    while (j < nums.length-1 && nums[j-1] == nums[j]) {
                        j++;
                    }

                    k--;
                    while (k > j && nums[k] == nums[k+1]) {
                        k--;
                    }
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }

        return result;
    }

    public List<List<Integer>> threeSumV0(int[] nums) { // Brute Force: Time Limit Exceeded
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Set<List<Integer>> resultSet = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tmpResult = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(tmpResult);
                        if (resultSet.add(tmpResult)) {
                            result.add(tmpResult);
                        }
                    }
                }
            }
        }

        return result;
    }

    // []
    // [1]
    // [1,2]
    // [1,2,3]
    // [-1,0,1,2,-1,-4]
    // [-1,-1,-1,2,2,2]
    // [2,5,5,8,-7,-9,5,-1,-4,2,8,4,-6,-2,-2,9,-2,13,1,0,9,9,4,-13,13,3,-14,11,-5,-13,3,4,7,-15,-11,7,13,1,13,-14,11,-1,5,-10,12,11,14,-13,1,-8,3,-4,-14,14,-10,-15,-6,-9,3,-4,-7,-8,-15,8,-8,12,-8,13,-2,-9,14,-6,5,-3,-9,-6,-7,-10,-3,9,-2,7,-10,-9,-2,-5,13,7,-6,2,-12,-6,1,10,9,0,7,-13,-2,-9,-7,-2,-8,5,10,-1,6,-12,4,10,12,9,2,10,8,-15,12,6,-1,-9,-7,2]
}
