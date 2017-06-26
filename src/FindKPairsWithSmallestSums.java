/**
 * Created by liyao on 6/25/17.
 */
import java.util.*;

public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return result;
        } else {
            PriorityQueue<SumBundle> maxHeap = new PriorityQueue<>();
            if (len1 < len2) {
                for (int i = 0; i < Math.min(len1, k); i++) {
                    for (int j = 0; j < Math.min(len2, k); j++) {
                        maxHeap.offer(new SumBundle(nums1[i] + nums2[j], nums1[i], nums2[j]));
                    }
                }
            } else {
                for (int j = 0; j < Math.min(len2, k); j++) {
                    for (int i = 0; i < Math.min(len1, k); i++) {
                        maxHeap.offer(new SumBundle(nums1[i] + nums2[j], nums1[i], nums2[j]));
                    }
                }
            }

            while (!maxHeap.isEmpty()) {
                SumBundle sb = maxHeap.poll();
                result.add(sb.getPair());
            }

            Collections.reverse(result);

            return result;
        }
    }

    class SumBundle implements Comparable<SumBundle> {
        private int sum;
        private int val0;
        private int val1;

        SumBundle(int sum, int val0, int val1) {
            this.sum = sum;
            this.val0 = val0;
            this.val1 = val1;
        }

        public int[] getPair() {
            return (new int[]{val0, val1});
        }

        @Override
        public int compareTo(SumBundle sb) {
            if (this.sum < sb.sum) {
                return -1;
            } else if (this.sum == sb.sum) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
