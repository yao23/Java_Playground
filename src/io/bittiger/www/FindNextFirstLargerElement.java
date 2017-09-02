package io.bittiger.www; /**
 * Created by liyao on 7/1/17.
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class FindNextFirstLargerElement {
    public int[] findNextFirstLargerElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        } else {
            int[] result = new int[nums.length];
            Deque<NumClass> stack = new ArrayDeque<>();
            stack.push(new NumClass(nums[0], 0));

            for (int i = 1; i < nums.length; i++) {
                if (stack.peek() != null) {
                    while (true) {
                        if (stack.isEmpty() || nums[i] < stack.peek().num) {
                            break;
                        } else { // stack is not empty and stack top num is less than cur num (nums[i])
                            NumClass topNum = stack.pop();
                            result[topNum.index] = nums[i];
                        }
                    }

                    stack.push(new NumClass(nums[i], i));
                }
            }

            while (!stack.isEmpty()) {
                NumClass topNum = stack.pop();
                result[topNum.index] = -1;
            }

            return result;
        }
    }


    class NumClass {
        int num;
        int index;
        NumClass(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }

    // [] => []
    // [1,3,2,4,1] => [3,4,4,-1,-1]
    // [40,50,11,32,55,68,75] => [50,55,32,55,68,75]
}
