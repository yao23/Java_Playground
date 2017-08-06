public class JumpGame {
    public boolean canJump(int[] nums) { // beats 35.25%
        if (nums == null || nums.length <= 1) {
            return true;
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
            // cannot reach current index
            if (min[i] == 0) {
                return false;
            }
        }

        return (min[len - 1] != 0);
    }
}

// A = [2,3,1,1,4], return true.
// A = [3,2,1,0,4], return false.
// A = [0,2,3], return false.