/**
 * Created by liyao on 6/9/17.
 */
import java.lang.*;

public class TrappingRainWater {
    public int trap(int[] height) {
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
}
