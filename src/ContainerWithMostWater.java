/**
 * Created by liyao on 6/10/17.
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int result = 0;
        int len = height.length;

        for (int i = 0; i < len - 1; i++) {
            int minHeight = height[i];

            for (int j = 0; j < len; j++) {
                int curHeight = height[j];
                if (curHeight < minHeight) {
                    minHeight = curHeight;
                }
                int curArea = (j - i + 1) * minHeight;
                if (curArea > result) {
                    result = curArea;
                }
            }
        }

        return result;
    }
}
