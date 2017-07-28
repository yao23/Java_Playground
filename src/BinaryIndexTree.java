public class BinaryIndexTree {
    class NumArray {
        int[] nums;
        int[] bit; // Binary Index Tree

        public NumArray(int[] nums) {
            this.nums = nums;
            this.bit = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                init(i, nums[i]);
            }
        }

        public void update(int i, int val) {
            int diff = val - nums[i];
            nums[i] = val;
            init(i, diff);
        }

        public int sumRange(int i, int j) {
            return getSum(j) - getSum(i - 1);
        }

        private void init(int i, int val) {
            for (int j = i + 1; j <= nums.length; j += j & (-j)) { // update value as well (move right)
                bit[j] += val;
            }
        }

        private int getSum(int i) {
            int sum = 0;

            for (int j = i + 1; j > 0; j -= j & (-j)) { // update range sum as well (move left)
                sum += bit[j];
            }

            return sum;
        }
    }
}

// balance time complexity between update and access in range

// update(2, 0)
// sumRange(1, 5)

// length to move left or right to find ancestor
// i & (-i)
// 1 & (-1) == 1
// 2 & (-2) == 2
// 3 & (-3) == 1
// 4 & (-4) == 4
// 5 & (-5) == 1
// 6 & (-6) == 2
// 7 & (-7) == 1
// 8 & (-8) == 8
