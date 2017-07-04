/**
 * Created by liyao on 7/3/17.
 */
import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int results[] = new int[row];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == row - 1) { // last row
                    results[j] = triangle.get(i).get(j);
                } else { // upper rows
                    results[j] += (triangle.get(i).get(j) + Math.min(results[j], results[j + 1]));
                }
            }
        }

        return results[0];
    }
}
