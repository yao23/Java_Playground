import java.util.Comparator;
import java.util.PriorityQueue;

public class KthClosestPoints {
    public int[] closest(int[] a, int[] b, int[] c, int k) {
        int lenA = a.length, lenB = b.length, lenC = c.length;
        PriorityQueue<Triple> minHeap = new PriorityQueue<>(k, new MyComparator());
        boolean[][][] visited = new boolean[lenA][lenB][lenC];
        minHeap.offer(new Triple(a[0], b[0], c[0], 0, 0, 0));
        visited[0][0][0] = true;
        int[] res = new int[3];
        while (k > 0) {
            Triple cur = minHeap.poll();
            res[0] = cur.a; res[1] = cur.b; res[2] = cur.c;
            int indexA = cur.indexA, indexB = cur.indexB, indexC = cur.indexC;

            if (indexA + 1 < lenA && !visited[indexA + 1][indexB][indexC]) {
                minHeap.offer(new Triple(a[indexA + 1], b[indexB], c[indexC], indexA + 1, indexB, indexC));
                visited[indexA + 1][indexB][indexC] = true;
            }

            if (indexB + 1 < lenB && !visited[indexA][indexB + 1][indexC]) {
                minHeap.offer(new Triple(a[indexA], b[indexB + 1], c[indexC], indexA, indexB + 1, indexC));
                visited[indexA][indexB + 1][indexC] = true;
            }

            if (indexC + 1 < lenC && !visited[indexA][indexB][indexC + 1]) {
                minHeap.offer(new Triple(a[indexA], b[indexB], c[indexC + 1], indexA, indexB, indexC + 1));
                visited[indexA][indexB][indexC + 1] = true;
            }

            k--;
        }

        return res;
    }

    class Triple {
        int a;
        int b;
        int c;
        int indexA;
        int indexB;
        int indexC;
        public Triple(int a, int b, int c, int indexA, int indexB, int indexC) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.indexA = indexA;
            this.indexB = indexB;
            this.indexC = indexC;
        }
    }

    class MyComparator implements Comparator<Triple> {
        @Override
        public int compare(Triple o1, Triple o2) {
            long dis1 = o1.a * o1.a + o1.b * o1.b + o1.c * o1.c;
            long dis2 = o2.a * o2.a + o2.b * o2.b + o2.c * o2.c;
            if (dis1 == dis2) {
                return 0;
            } else if (dis1 < dis2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
