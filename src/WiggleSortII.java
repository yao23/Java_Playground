/**
 * Created by liyao on 6/25/17.
 */
import java.util.Random;

public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        while (i <= right) {
            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    private int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            final int j = partition(nums, lo, hi);
            if(j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    private void shuffle(int a[]) {

        final Random random = new Random();
        for (int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            swap(a, ind, r);
        }
    }

    private int partition(int[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;
        while(true) {
            while(i < hi && less(a[++i], a[lo]));
            while(j > lo && less(a[lo], a[--j]));
            if(i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        final int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    private void findIndex(int i, int[] nums, int len, int left, int right) {
        if (i % 2 == 0) { // even index, should less than left and right values
            if (i > 0) {
                if (nums[i - 1] <= nums[i] || i + 1 < len && nums[i] >= nums[1]) {
                    left = i;
                } else {
//                    continue;
                }
            } else { // i == 0
                if (i + 1 < len && nums[i] >= nums[1]) {
                    left = i;
                } else {
//                    continue;
                }
            }
        } else { // odd index, should greater than left and right values

        }
    }

    public void wiggleSortV0(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        while (left < right) {

        }
    }

    // brute force: sort, merge right half into left half

    // [1,5,1,1,6,4] => [1,6,1,5,1,4] ([1,5,1,6,1,4])
}
