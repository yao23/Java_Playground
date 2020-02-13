package RoundA;

import java.util.*;

public class Parcels {
    /**
     *
     * https://w630.cc/kickstart-2019-A-Parcels/
     *
     * https://xiaozhuanlan.com/topic/4862135709
     * https://www.cnblogs.com/SGCollin/p/9636955.html
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = input.nextInt();
        for (int i = 1; i <= testCase; i++) {
            System.out.println(String.format("Case #%d: %s", i, solve(input)));
        }

    }

    private static String solve(Scanner scanner) {
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        boolean[][] off = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String s = scanner.next();
            for (int j = 0; j < s.length(); j++) {
                off[i][j] = s.charAt(j) == '1';
            }
        }
        boolean[][] used = new boolean[r][c];
        int[][] len = new int[r][c];
        Queue<Pair> q = new ArrayDeque<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (off[i][j]) {
                    used[i][j] = true;
                    q.add(new Pair(i, j));
                }
            }
        }
        while (!q.isEmpty()) {
            Pair office = q.poll();
            int x = office.x;
            int y = office.y;
            for (int i = 0; i < dx.length; i++) {
                int x1 = x + dx[i];
                int y1 = y + dy[i];
                if (check(x1, y1, r, c, used)) {
                    used[x1][y1] = true;
                    len[x1][y1] = len[x][y] + 1;
                    q.add(new Pair(x1, y1));
                }
            }
        }
        int max = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                max = Math.max(max, len[i][j]);
            }
        }
        int min = -1;
        while (min + 1 < max) {
            int m = (min + max) / 2;
            int min1 = Integer.MAX_VALUE;
            int max1 = Integer.MIN_VALUE;
            int min2 = Integer.MAX_VALUE;
            int max2 = Integer.MIN_VALUE;
            // dist((x1, y1), (x2, y2)) = max(abs(x1 + y1 - (x2 + y2)), abs(x1 - y1 - (x2 - y2)))
            // max{x1-x2+y1-y2, x1-x2+y2-y1, x2-x1+y1-y2, x2-x1+y2-y1}
            // max(|(x1+y1)-(x2+y2)|, |(x1-y1)-(x2-y2)|)
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (len[i][j] > m) {
                        min1 = Math.min(min1, i + j);
                        max1 = Math.max(max1, i + j);
                        min2 = Math.min(min2, i - j);
                        max2 = Math.max(max2, i - j);
                    }
                }
            }
            int d1 = Math.max(max1 - min1, max2 - min2);
            int d2 = Math.min(max1 - min1, max2 - min2);
            int l = d1 / 2;
            if (d1 % 2 == 1) {
                l++;
            }
            if (d1 % 2 == 0 && d2 % 2 == 0 && d1 == d2 && (min1 + min2) % 2 == 1) {
                l++;
            }
            if (l <= m) { // left in binary search
                max = m;
            } else { // right in binary search
                min = m;
            }
        }
        long ans = max;
        return String.valueOf(ans);
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean check(int x, int y, int r, int c, boolean[][] used) {
        return 0 <= x && x < r && 0 <= y && y < c && !used[x][y];
    }
}
