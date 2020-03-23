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
            Set<Integer> visited = new HashSet<>();
            for (int j = 0; j < col; j++) {
                int curIdx = graph[i][j];
                if (blue.contains(curIdx)) {
                    index = j;
                    break;
                } else if (red.contains(curIdx)) {
                    shouldBeBlue = false;
                    index = j;
                    break;
                }
            }

            if (!solve(i, index, red, blue, shouldBeBlue, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean solve(int row, int col, Set<Integer> red, Set<Integer> blue, boolean shouldBeBlue, int[][] graph, Set<Integer> visited) {
        int len = graph[row].length;
        if (col < 0 || col == len) {
            return true;
        }
        int num = graph[row][col];
        if (visited.contains(num)) {
            return true;
        }

        visited.add(num);
        if (shouldBeBlue) {
            if (red.contains(num)) {
                return false;
            } else {
                blue.add(num);
            }
        } else {
            if (blue.contains(num)) {
                return false;
            } else {
                red.add(num);
            }
        }

        return solve(row, col - 1, red, blue, !shouldBeBlue, graph, visited) &&
                solve(row, col + 1, red, blue, !shouldBeBlue, graph, visited);
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
