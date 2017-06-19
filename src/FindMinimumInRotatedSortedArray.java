/**
 * Created by liyao on 6/18/17.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        int min = Integer.MIN_VALUE;
        int len = nums.length;
        if (len == 0) {
            return min;
        } else if (len == 1) {
            return nums[0];
        } else {
            min = Integer.MAX_VALUE;
            int start = 0, end = len - 1;
            if (nums[start] <= nums[end]) { // in order
                return nums[start];
            } else { // rotated
                while (start + 1 < end) {
                    int mid = start + (end - start) / 2;
                    if (nums[start] <= nums[mid] && nums[mid] <= nums[end]) { // in order
                        return Math.min(nums[start], min);
                    } else {
                        if (nums[start] < nums[mid]) { // left part is in order
                            min = Math.min(nums[start], min);
                            start = mid;
                        } else { // right part is in order
                            min = Math.min(nums[mid], min);
                            end = mid;
                        }
                    }
                }

                int tmpMin = Math.min(nums[start], nums[start + 1]);
                if (tmpMin < min) {
                    return tmpMin;
                } else {
                    return min;
                }
            }
        }
    }

    // [] =>
    // [1] => 1
    // [6,7,0,1,2,3,4,5] => 0
    // [5,6,7,0,1,2,3,4] => 0
    // [4,5,6,7,8,9,0,1,2] => 0

    // beats 3.88%
}
