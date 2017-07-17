/**
 * Created by liyao on 7/3/17.
 */
public class PaintHouse {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        } else {
            int row = costs.length;

            for (int i = 1; i < row; i++) {
                costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
                costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
                costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
            }

            return Math.min(costs[row - 1][0], Math.min(costs[row - 1][1], costs[row - 1][2]));
        }
    }

    // beats 51.97%
}
