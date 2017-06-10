/**
 * Created by liyao on 6/9/17.
 */
import java.lang.*;

public class TrappingRainWater {
    public int trap(int[] height) { // beats 60.02%
        int result = 0;
        int len = height.length;
        if (len <= 2) {
            return result;
        }

        int leftMax = height[0];
        int rightMax = height[len-1];
        int[] rightMaxValues = new int[len-2]; // cache

        for (int i = rightMaxValues.length - 1; i >= 0; i--) {
            int right = height[i+2]; // offset 2, rightLargeValues.length = height.length - 2
            if (right > rightMax) {
                rightMax = right;
            }

            rightMaxValues[i] = rightMax;
        }

        for (int i = 1; i < len - 1; i++) {
            int left = height[i-1]; // last element
            int cur = height[i];
            if (left > leftMax) {
                leftMax = left;
            }

            int minHeight = Math.min(leftMax, rightMaxValues[i-1]);

            if (minHeight > cur) {
                result += (minHeight - cur);
            }
        }

        return result;
    }

    public int trapV0(int[] height) { // beats 0.66%
        int result = 0;
        int len = height.length;
        if (len <= 1) {
            return result;
        }

        for (int i = 1; i < len - 1; i++) {
            int left = height[0];
            int right = height[len-1];
            for (int j = 1; j < i; j++) {
                if (height[j] > left) {
                    left = height[j];
                }
            }

            for (int j = len-1; j > i; j--) {
                if (height[j] > right) {
                    right = height[j];
                }
            }

            int minHeight = Math.min(left, right);
            if (minHeight > height[i]) {
                result += (minHeight - height[i]);
            }
        }

        return result;
    }

    // [] => 0
    // [0,1,0,2,1,0,1,3,2,1,2,1] => 6
    // [2,1,5,6,2,3] => 2
}
