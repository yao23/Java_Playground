package com.leetcode.www; /**
 * Created by liyao on 6/26/17.
 */
import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix { // LC 378
    public int kthSmallest(int[][] matrix, int k) { // binary search
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }
        if (k <= row * col) {
            int left = matrix[0][0], right = matrix[row - 1][col - 1];

            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = 0, j = col - 1;
                for (int i = 0; i < row; i++) {
                    // get number of elements smaller or equal to mid in current row
                    while (j >= 0 && matrix[i][j] > mid) {
                        j--;
                    }

                    count += (j + 1);
                }
                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        } else {
            return matrix[row - 1][col - 1];
        }
    }

    public int kthSmallestV1(int[][] matrix, int k) { // min heap
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>();
        for (int j = 0; j <= n - 1; j++) {
            pq.offer(new Tuple(0, j, matrix[0][j]));
        }
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            if (t.x == n - 1) {
                continue;
            }
            pq.offer(new Tuple(t. x + 1, t.y, matrix[t.x + 1][t.y])); // next smallest
        }
        return pq.poll().val;
    }

    class Tuple implements Comparable<Tuple> {
        int x, y, val;
        public Tuple (int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo (Tuple that) {
            return this.val - that.val;
        }
    }

    // [[]] => 0
    // [[1]], 1 => 1
    // [[1,5],[10,11]],1 => 1
    // [[1,5],[10,11]],2 => 5
    // [[1,5],[10,11]],3 => 10
    // [[1,5],[10,11]],4 => 11
    // [[1,5,9],[10,11,13],[12,13,15]],8 => 13
    // [[1,2],[1,3]],2 => 1

    // beats 71.88%
}
