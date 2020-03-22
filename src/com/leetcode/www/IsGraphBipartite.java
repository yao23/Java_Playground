import java.util.HashSet;
import java.util.Set;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        Set<Integer> blue = new HashSet<>();
        Set<Integer> red = new HashSet<>();
        int row = graph.length;

        for (int i = 0; i < row; i++) {
            int col = graph[i].length;
            int index = 0;
            boolean shouldBeBlue = true;
            for (int j = 0; j < col; j++) {
                int curIdx = graph[i][j];
                if (blue.contains(curIdx)) {
                    index = curIdx;
                } else if (red.contains(curIdx)) {
                    shouldBeBlue = false;
                    index = curIdx;
                }
                break;
            }

            if (!solve(index, red, blue, shouldBeBlue, col)) {
                return false;
            }
        }
        return true;
    }

    private boolean solve(int index, Set<Integer> red, Set<Integer> blue, boolean shouldBeBlue, int len) {
        if (index < 0 || index == len) {
            return true;
        }
        if (blue.contains(index)) {
            if (shouldBeBlue) {
                return true;
            }
        } else if (red.contains(index)) {
            if (!shouldBeBlue) {
                return true;
            }
        }

        if (shouldBeBlue) {
            if (red.contains(index)) {
                return false;
            } else {
                blue.add(index);
            }
        } else {
            if (blue.contains(index)) {
                return false;
            } else {
                red.add(index);
            }
        }

        return solve(index - 1, red, blue, !shouldBeBlue, len) &&
                solve(index + 1, red, blue, !shouldBeBlue, len);
    }

    public boolean isBipartiteV1(int[][] graph) {
        Set<Integer> blue = new HashSet<>();
        Set<Integer> red = new HashSet<>();
        int row = graph.length;

        for (int i = 0; i < row; i++) {
            int col = graph[i].length;
            for (int j = 0; j < col; j++) {
                boolean shouldBeBlue = (j % 2 == 0);
                int num = graph[i][j];
                if (isValid(blue, red, shouldBeBlue, num)) {
                    if (shouldBeBlue) {
                        blue.add(num);
                    } else {
                        red.add(num);
                    }
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

        if (shouldBeBlue) {
            return !inRed;
        } else {
            return !inBlue;
        }
    }

    public boolean isBipartiteV0(int[][] graph) {
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
                if (isValidV0(blue, red, false, i)) {
                    red.add(i);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidV0(Set<Integer> blue, Set<Integer> red, boolean shouldBeBlue, int index) {
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
