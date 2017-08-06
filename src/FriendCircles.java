import java.util.HashSet;
import java.util.Set;

public class FriendCircles {
    private int[] parents;
    private Set<Integer> circles;

    public int findCircleNum(int[][] M) { // Union Find solution
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
            compressedFind(i);
        }
        for (int i = 0; i < N; i++) {
            circles.add(parents[i]);
        }
    }

    private void updateParents(int N, int[][] M) {
        for (int r = 0; r < N; r++) {
            for (int c = r; c < N; c++) {
                if (M[r][c] == 1) {
                    if (find(r) != find(c)) {
                        union(r, c, N);
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
        parents[curParent] = parent;

        return parent;
    }

    private int find(int idx) {
        int parent = parents[idx];

        while (parent != parents[parent]) {
            parent = parents[parent];
        }

        return parent;
    }

    private void union(int idx1, int idx2, int N) {
        int parent1 = compressedFind(idx1);
        int parent2 = compressedFind(idx2);
        if (parent1 != parent2) {
            if (parent1 < parent2) {
                // update top parent for all elements having parent2
                updateOldParents(N, parent2, parent1);
            } else {
                // update top parent for all elements having parent1
                updateOldParents(N, parent1, parent2);
            }
        }
    }

    // test case 4 (watch this condition, LC test samples in problem description are trap)
    private void updateOldParents(int N, int oldParent, int newParent) {
        for (int i = N - 1; i >= oldParent; i--) {
            if (parents[i] == oldParent) {
                parents[i] = newParent;
            }
        }
    }
}

// [[1,1,0],[1,1,0],[0,0,1]] => 2
// [[1,1,0],[1,1,1],[0,1,1]] => 1
// [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]] => 1

// [[1,1,0,0,0,0,0,1,0,0,0,0,0,0,0],[1,1,0,0,0,0,0,0,0,0,0,0,0,0,0],[0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],
//  [0,0,0,1,0,1,1,0,0,0,0,0,0,0,0],[0,0,0,0,1,0,0,0,0,1,1,0,0,0,0],[0,0,0,1,0,1,0,0,0,0,1,0,0,0,0],
//  [0,0,0,1,0,0,1,0,1,0,0,0,0,1,0],[1,0,0,0,0,0,0,1,1,0,0,0,0,0,0],[0,0,0,0,0,0,1,1,1,0,0,0,0,1,0],
//  [0,0,0,0,1,0,0,0,0,1,0,1,0,0,1],[0,0,0,0,1,1,0,0,0,0,1,1,0,0,0],[0,0,0,0,0,0,0,0,0,1,1,1,0,0,0],
//  [0,0,0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,1,0,1,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,1,0,0,0,0,1]] => 3

// beats 24.48%