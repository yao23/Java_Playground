/**
 * Created by liyao on 6/19/17.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        } else if (len == 1) {
            return 0;
        } else {
            if (nums[0] > nums[1]) {
                return 0;
            } else if (nums[len -1] > nums[len - 2]) {
                return (len - 1);
            } else {
                for (int i = 1; i < len / 2; i++) {
                    if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
                        return i;
                    } else if (nums[len - i] < nums[len - 1 -i] && nums[len - 1 -i] > nums[len - 2 - i]) {
                        return (len - 1 -i);
                    } else {
                        continue;
                    }
                }

                if (len % 2 == 1) { // odd numbers
                    int mid = len / 2;
                    if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                        return mid;
                    } else {
                        return -1;
                    }
                } else { // even numbers, should not reach here
                    return -1;
                }
            }
        }
    }

    // [] => -1
    // [1] => 0
    // [1,2] => 1
    // [1,2,3] => 2
    // [1,2,3,2,1] => 2
    // [1,2,3,4,2,1] => 3
}
