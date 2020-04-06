public class ContinuousSubarraySum {
    /**
     * Wrong Answer
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySumV0(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 && k != 0) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i > 0) {
                if (isKMultiple(sum, k)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                if (isKMultiple(sum, k)) {
                    return true;
                }
            }
            sum -= nums[i];
        }
        return false;
    }

    private boolean isKMultiple(int num, int k) {
        if (k == 0) {
            return num == 0;
        } else {
            return num % k == 0;
        }
    }
}
