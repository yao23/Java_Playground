public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int len = nums.length;
        int[] min = new int[len];
        min[0] = 0;
        int curIdx = 0;
        for (int i = 1; i < len; i++) {
            for (int j = curIdx; j < i; j++) {
                if (nums[j] + j >= i) {
                    min[i] = min[j] + 1;
                    curIdx = j;
                    break; // stop at the most left index
                }
            }
        }
        return min[len - 1];
    }
}

// time: O(n), space: O(n)

// [2,3,1,1,4] => 2 (2 -> 3 -> 4)

// beats 89.56%

