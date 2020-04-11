import java.util.*;

public class CampusBikes { // 1057
    /**
     * O(M*N log(M*N)) - No Fancy sort
     * https://leetcode.com/problems/campus-bikes/discuss/559321/Java-Straightforward-O(M*N-log(M*N))-No-Fancy-sort
     *
     * @param workers
     * @param bikes
     * @return
     */
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        ArrayList<int[]> sorted = new ArrayList<>();

        for (int i = 0; i < workers.length; ++i) {
            for (int j = 0; j < bikes.length; ++j) {
                sorted.add(new int[] {i, j, Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1])});
            }
        }

        Collections.sort(sorted, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                //0->worker, 1->bike, 2->Manhattan distance

                if (a[2] != b[2]) {
                    return a[2] - b[2];
                } else {
                    if (a[0] != b[0]) {
                        return a[0] - b[0];
                    } else {
                        return a[1] - b[1];
                    }
                }
            }
        });

        HashSet<Integer> usedBikes = new HashSet<>();
        int unassignedCount = workers.length;
        int[] result = new int[workers.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < sorted.size() && unassignedCount > 0; ++i) {
            int[] entry = sorted.get(i);
            if (!usedBikes.contains(entry[1]) && result[entry[0]] == -1) {
                result[entry[0]] = entry[1];
                usedBikes.add(entry[1]);
                unassignedCount--;
            }
        }
        return result;
    }

    /**
     * Bucket Sort + optimization (93% Runtime/100% Space)
     * https://leetcode.com/problems/campus-bikes/discuss/549533/Java-Bucket-Sort-%2B-optimization-(93-Runtime100-Space)
     *
     * @param workers
     * @param bikes
     * @return
     */
    public int[] assignBikesV1(int[][] workers, int[][] bikes) {
        final int N = workers.length;
        final int M = bikes.length;
        // 1~2000 Manhattan distance
        List<int[]>[] list = new ArrayList[2001]; // bucket sort
        // put into bucket: index - distance; every List - linked (ordered) by bike index [j] at same distance
        for (int i = 0; i < N; i++) {
            int[] worker = workers[i]; // [0] x [1] y
            for (int j = 0; j < M; j++) {
                int[] bike = bikes[j]; // [0] x [1] y
                int dist = Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
                if (list[dist] == null) {
                    list[dist] = new ArrayList<int[]>();
                }
                list[dist].add(new int[]{i, j}); // [0]: worker index [1]: bike index
            }
        }
        // save to res[] index: worker index ; corresponding value: bike index
        int[] res = new int[N];
        Arrays.fill(res, -1);
        Set<Integer> usedBikes = new HashSet<>();
        // dist ascending order, [i] - dist
        for (int i = 0; i < list.length; i++) { // < 2001
            if (list[i] == null) {
                continue;
            }
            int size = list[i].size();

            for (int j = 0; j < size; j++) {
                int[] workerAndBike = list[i].get(j);
                if (res[workerAndBike[0]] == -1 && !usedBikes.contains(workerAndBike[1])) {
                    res[workerAndBike[0]] = workerAndBike[1];
                    usedBikes.add(workerAndBike[1]);
                }
                if (usedBikes.size() == N) {
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * O(mn) bucket sort
     * https://leetcode.com/problems/campus-bikes/discuss/554652/Simple-and-Clean-JAVA-solution-O(mn)-bucket-sort
     *
     * @param workers
     * @param bikes
     * @return
     */
    public int[] assignBikesV2(int[][] workers, int[][] bikes) {
        int minDist = Integer.MAX_VALUE;
        int maxDist = Integer.MIN_VALUE;
        Map<Integer, List<int[]>> map = new HashMap<>();
        int[] res = new int[workers.length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int dist = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                if (!map.containsKey(dist)) map.put(dist, new ArrayList<>());
                map.get(dist).add(new int[]{i, j});
                minDist = Math.min(minDist, dist);
                maxDist = Math.max(maxDist, dist);
            }
        }

        Set<Integer> removedWorkers = new HashSet<>();
        Set<Integer> removedBikes = new HashSet<>();
        for (int i = minDist; i <= maxDist; i++) {
            if (!map.containsKey(i)) continue;
            for (int[] pair : map.get(i)) {
                if (removedWorkers.contains(pair[0]) || removedBikes.contains(pair[1])) continue;
                removedWorkers.add(pair[0]);
                removedBikes.add(pair[1]);
                res[pair[0]] = pair[1];
            }
        }
        return res;
    }
}

/**
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker and bike is a 2D coordinate on this grid.
 *
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the (worker, bike) pair
 * with the shortest Manhattan distance between each other, and assign the bike to that worker. (If there are multiple
 * (worker, bike) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index;
 * if there are multiple ways to do that, we choose the pair with the smallest bike index). We repeat this process until
 * there are no available workers.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th worker is assigned to.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation:
 * Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 * Example 2:
 *
 *
 *
 * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * Output: [0,2,1]
 * Explanation:
 * Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is assigned to
 * Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
 *
 *
 * Note:
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * All worker and bike locations are distinct.
 * 1 <= workers.length <= bikes.length <= 1000
 */