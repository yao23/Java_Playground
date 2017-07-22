public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int len = nums.length, left = 0, right = len - 1;
        int[] results = new int[len];
        if (len == 0) {
            return results;
        } else if (len == 1) {
            results[0] = func(nums[0], a, b, c);
            return results;
        } else {
            if (a > 0) { // mid low, left and right high
                int cur = len - 1;
                while (left <= right) {
                    int leftVal = func(nums[left], a, b, c), rightVal = func(nums[right], a, b, c);
                    if (leftVal < rightVal) {
                        results[cur] = rightVal;
                        right--;
                    } else {
                        results[cur] = leftVal;
                        left++;
                    }
                    cur--;
                }
            } else { // mid high, left and right low
                int cur = 0;
                while (left <= right) {
                    int leftVal = func(nums[left], a, b, c), rightVal = func(nums[right], a, b, c);
                    if (leftVal < rightVal) {
                        results[cur] = leftVal;
                        left++;
                    } else {
                        results[cur] = rightVal;
                        right--;
                    }
                    cur++;
                }
            }
            return results;
        }
    }
    
    private int func(int x, int a , int b, int c) {
        return a * x * x + b * x + c;
    }

    // [-4, -2, 2, 4], a = 1, b = 3, c = 5 => [3, 9, 15, 33]
    // [-4, -2, 2, 4], a = -1, b = 3, c = 5 => [-23, -5, 1, 7]
}
