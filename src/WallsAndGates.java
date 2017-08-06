import java.util.ArrayDeque;
import java.util.Deque;

public class WallsAndGates {
    private int INF = Integer.MAX_VALUE; // (2^31 - 1) = 2147483647

    public void wallsAndGates(int[][] rooms) { // beats 58.29%
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] >= 0) { // gate or room
                    bfsHelper(rooms, i, j);
                }
            }
        }
    }

    private void bfsHelper(int[][] rooms, int i, int j) {
        int row = rooms.length;
        int col = rooms[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i,j});
        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            int[] dx = new int[]{-1, 1, 0, 0};
            int[] dy = new int[]{0, 0, -1, 1};
            for (int k = 0; k < 4; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];

                if (isValid(x, y, newX, newY, row, col, rooms) && rooms[newX][newY] > rooms[x][y] + 1) {
                    rooms[newX][newY] = rooms[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }

    // only update for rooms, INF + 1 = Integer.MIN_VALUE (overflow)
    private boolean isValid(int x, int y, int newX, int newY, int row, int col, int[][] rooms) {
        return newX >= 0 && newY >= 0 && newX < row && newY < col && rooms[newX][newY] > 0 && rooms[x][y] != INF;
    }
}
