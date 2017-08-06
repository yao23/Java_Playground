import java.util.HashSet;
import java.util.Set;

public class FriendCircles {
    private int[] parents;
    private Set<Integer> circles;

    public int findCircleNum(int[][] M) {
        circles = new HashSet<>();
        int N = M.length;
        if (N == 0) {
            return circles.size();
        }
        initParents(N);
        updateParents(N, M);
        updateCircles(N);

        return circles.size();
    }

    private void updateCircles(int N) {
        for (int i = 0; i < N; i++) {
            circles.add(parents[i]);
        }
    }

    private void updateParents(int N, int[][] M) {
        for (int r = 0; r < N; r++) {
            for (int c = r; c < N; c++) {
                if (M[r][c] == 1) {
                    if (find(r) != find(c)) {
                        union(r, c);
                    }
                }
            }
        }
    }

    private void initParents(int N) {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    private int compressedFind(int idx) {
        int parent = parents[idx];

        while (parent != parents[parent]) {
            parent = parents[parent];
        }

        int curParent = parents[idx];
        while (curParent != parents[curParent]) {
            int tmp = parents[curParent];
            parents[curParent] = parent; // update to top parent
            curParent = tmp;
        }

        return parent;
    }

    private int find(int idx) {
        int parent = parents[idx];

        while (parent != parents[parent]) {
            parent = parents[parent];
        }

        return parent;
    }

    private void union(int idx1, int idx2) {
        int parent1 = compressedFind(idx1);
        int parent2 = compressedFind(idx2);
        if (parent1 != parent2) {
            if (parent1 < parent2) {
                parents[idx2] = parent1;
            } else {
                parents[idx1] = parent2;
            }
        }
    }
}