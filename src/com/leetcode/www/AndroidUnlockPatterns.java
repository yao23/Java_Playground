package com.leetcode.www;

public class AndroidUnlockPatterns { // LC 351
    private int[][] skipTable = new int[10][10];
    private boolean[] visited = new boolean[10];

    public int numberOfPatterns(int m, int n) {
        initSkipTable();

        int res = 0;

        visited[1] = true;
        res += (calculate(1, 1, m, n) * 4); // 1, 3, 7, 9
        visited[1] = false;

        visited[2] = true;
        res += (calculate(2, 1, m, n) * 4); // 2, 4, 6, 8
        visited[2] = false;

        visited[5] = true;
        res += calculate(5, 1, m, n);
        visited[5] = false;

        return res;
    }

    private int calculate(int cur, int num, int m, int n) {
        int res = 0;

        if (num >= m && num <= n) {
            res = 1;
            if (num == n) {
                return res;
            }
        } else {
            return 0;
        }

        for (int i = 1; i <= 9; i++) {
            // no key between cur and i, or key between cur and i is visited
            if (!visited[i] && (skipTable[cur][i] == 0 || visited[skipTable[cur][i]])) {
                visited[i] = true;
                res += calculate(cur, num + 1, m, n);
                visited[i] = false;
            }
        }

        return res;
    }

    private void initSkipTable() {
        // init top row
        skipTable[1][3] = 2;
        skipTable[3][1] = 2;
        // init bottom row
        skipTable[7][9] = 8;
        skipTable[9][7] = 8;
        // init left column
        skipTable[1][7] = 4;
        skipTable[7][1] = 4;
        // init right column
        skipTable[3][9] = 6;
        skipTable[9][3] = 6;
        // init diagonal
        skipTable[3][7] = 5;
        skipTable[7][3] = 5;
        // init anti-diagonal
        skipTable[1][9] = 5;
        skipTable[9][1] = 5;
    }
}

// m = 1, n = 1 => 9 (1 ~ 9)
// m = 1, n = 2 => 65
