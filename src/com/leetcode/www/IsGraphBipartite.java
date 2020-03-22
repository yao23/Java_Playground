import java.util.HashSet;
import java.util.Set;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        Set<Integer> blue = new HashSet<>();
        Set<Integer> red = new HashSet<>();
        int row = graph.length;
        int col = graph[0].length;
        for (int i = 0; i < row; i++) {
            if (isValid(blue, red, true, i)) {
                blue.add(i);
            } else {
                return false;
            }
            for (int j = 0; j < col; j++) {
                if (isValid(blue, red, false, i)) {
                    red.add(i);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(Set<Integer> blue, Set<Integer> red, boolean shouldBeBlue, int index) {
        boolean inBlue = blue.contains(index);
        boolean inRed = red.contains(index);

        if (!inBlue && inRed) {
            return !shouldBeBlue;
        } else if (inBlue && !inRed) {
            return shouldBeBlue;
        } else {
            return !inBlue && !inRed;
        }
    }
}
