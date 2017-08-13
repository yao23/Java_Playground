package com.leetcode.www; /**
 * Created by liyao on 6/10/17.
 */
import java.util.*;

public class FourSum { // LC 18
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

                int k = j + 1, l = len -1;

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

        return result;
    }

    // [], 0 => []
    // [1,0,-1,0,-2,2], 0 => [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    // [-1,0,1,2,-1,-4], -1 => [[-4,0,1,2],[-1,-1,0,1]]
    // [-498,-492,-473,-455,-441,-412,-390,-378,-365,-359,-358,-326,-311,-305,-277,-265,-264,-256,-254,-240,-237,-234,-222,-211,-203,-201,-187,-172,-164,-134,-131,-91,-84,-55,-54,-52,-50,-27,-23,-4,0,4,20,39,45,53,53,55,60,82,88,89,89,98,101,111,134,136,209,214,220,221,224,254,281,288,289,301,304,308,318,321,342,348,354,360,383,388,410,423,442,455,457,471,488,488], -2808

    // beats 28.60%
}
