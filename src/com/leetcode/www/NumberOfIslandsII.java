package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandsII { // LC 305
    private int[][] direction = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    /**
     * Runtime: 9 ms, faster than 91.57% of Java online submissions for Number of Islands II.
     * Memory Usage: 53.6 MB, less than 79.58% of Java online submissions for Number of Islands II.
     *
     * https://leetcode.com/problems/number-of-islands-ii/discuss/289575/It-seems-that-the-newly-test-case-would-make-high-votes-answers-not-pass...
     * https://leetcode.com/problems/number-of-islands-ii/discuss/286515/Java-Union-Find-9-ms-faster-than-93.31-51.5-MB-less-than-86.78
     *
     * @param m
     * @param n
     * @param positions
     * @return
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[] id = new int[m * n];
        int[] size = new int[m * n];
        UnionFind uf = new UnionFind(id, size);
        int count = 0;
        for (int i = 0; i < n * m; ++i) {
            id[i] = -1;
        }

        for (int[] p : positions) {
            int cur = p[0] * n + p[1];
            if (id[cur] != -1) {
                res.add(count);
                continue;
            }
            count++;
            id[cur] = cur;
            size[cur] = 1;
            for (int[] d : direction) {
                int i = p[0] + d[0];
                int j = p[1] + d[1];
                int next = i * n + j;
                if (isValid(i, j, m, n, size)) {
                    if (!uf.isConnected(cur, next)) { // check not visited implicitly
                        uf.union(cur, next);
                        count--;
                    }
                }
            }
            res.add(count);
        }

        return res;
    }

    private boolean isValid(int i, int j, int m, int n, int[] size) {
        return (i >= 0) && (i < m) && (j >= 0) && (j < n) && (size[i * n + j] != 0);
    }

    class UnionFind {
        private int[] id;
        private int[] size;
        public UnionFind(int[] id, int[] size) {
            this.id = id;
            this.size = size;
        }

        public int find(int i) {
            // Find with Path Compression
            while (i != id[i]) {
                id[i] = id[id[i]];
                i = id[i];
            }
            return i;
        }

        public void union(int p, int q) {
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            } else { // weighted union (always merge small one into large one)
                if (size[i] < size[j]) {
                    id[i] = id[j];
                    size[j] += size[i];
                } else {
                    id[j] = id[i];
                    size[i] += size[j];
                }
            }
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void union2(int p, int q) { // quick union
            int i = find(p);
            int j = find(q);
            if (i == j) {
                return;
            } else {
                id[i] = id[j];
                // count--
            }
        }
    }
}

// 3, 3, [[0,0],[0,1],[1,2],[2,1]] => [1,1,2,3]

// beats 72.34%