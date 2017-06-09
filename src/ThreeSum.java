/**
 * Created by liyao on 6/8/17.
 */
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Set<List<Integer>> resultSet = new HashSet<List<Integer>>();

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
}
