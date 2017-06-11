/**
 * Created by liyao on 6/10/17.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int result = 0;
        int len = height.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i+1; j < len; j++) {
                int minHeight = Math.min(height[i], height[j]);
                int curArea = (j - i) * minHeight;
                if (curArea > result) {
                    result = curArea;
                }
            }
        }

        return result;
    }

    // [1,1] => 1
    // [1,2,4,3] => 4
    // [3,2,1,3] => 9
}
