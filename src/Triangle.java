/**
 * Created by liyao on 7/3/17.
 */
import java.util.List;

public class Triangle { // LC 120
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int results[] = new int[row];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == row - 1) { // last row
                    results[j] = triangle.get(i).get(j);
                } else { // upper rows
                    results[j] = (triangle.get(i).get(j) + Math.min(results[j], results[j + 1]));
                }
            }
        }

        return results[0];
    }

    // [[-10]] => -10
    // [[2],[3,4],[6,5,7],[4,1,8,3]] => 11

    // beats 67.79%
}
