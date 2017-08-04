public class GraphValidTree { // LC 261
    private int[] arr;
    public boolean validTree(int n, int[][] edges) {
        if (edges.length == n - 1) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i;
            }

            for (int i = 0; i < edges.length; i++) {
                int first = edges[i][0], second = edges[i][1];
                if (find(first) != find(second)) {
                    union(first, second);
                } else {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private int find(int elem) {
        int parent = arr[elem];

        while (parent != arr[parent]) {
            parent = arr[parent];
        }

        return parent;
    }

    private void union(int first, int second) {
        int firstParent = compressedFind(first);
        int secondParent = compressedFind(second);
        if (firstParent != secondParent) {
            if (first < second) {
                arr[second] = firstParent;
            } else {
                arr[first] = secondParent; // test case 3
            }
        }
    }

    private int compressedFind(int elem) {
        int parent = arr[elem];

        // find common parent
        while (parent != arr[parent]) {
            parent = arr[parent];
        }

        int p = arr[elem];
        // update parent for immediate nodes
        while (p != arr[p]) {
            int tmp = arr[p];
            arr[p] = parent;
            p = tmp;
        }

        arr[elem] = parent;
        return parent;
    }
}

// 5, [[0,1],[0,2],[2,3],[2,4]] => true
// 5, [[0,1],[1,2],[2,3],[1,3],[1,4]] => false
// 5, [[0,1],[2,1],[2,0],[2,4]] => false

// beats 61.23%