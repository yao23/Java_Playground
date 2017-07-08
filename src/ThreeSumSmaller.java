import java.util.Arrays;

/**
 * Created by liyao on 7/8/17.
 */
public class ThreeSumSmaller {
    public static int threeSumSmaller(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        } else {
            Arrays.sort(nums);
            int result = 0;
            for (int i = 0; i < len - 2; i++) {
                int left = i + 1, right = len - 1;
                while (left < right) {
                    if (nums[i] + nums[left] + nums[right] >= target) {
                        right--;
                    } else {
                        result += (right - left); // [left + 1, right] as smaller range
                        break;
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-2, 0, 1, 3};
        int target = 2;
        System.out.println("Three sum smaller: " + threeSumSmaller(arr, target));
    }
}
