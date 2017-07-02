/**
 * Created by liyao on 7/1/17.
 */

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class FindNextFirstLargerElement {
    public int[] findNextFirstLargerElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        } else {
            int[] result = new int[nums.length];
            Arrays.fill(result, -1);
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(nums[0]);
            int i = 0;
            while (i < nums.length) {
                while (true) {
                    if (stack.isEmpty() || nums[i] < stack.peek()) {
                        break;
                    } else {
                        result[i] = stack.pop();
                    }
                }

                stack.push(nums[i]);
                i++;
            }

            return result;
        }
    }
}
