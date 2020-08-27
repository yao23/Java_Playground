import java.util.*;

public class RandomPickIndex { // LC 398 (Facebook)
    class Solution {
        Map<Integer, List<Integer>> map;
        public Solution(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];
                if (!map.containsKey(cur)) {
                    map.put(cur, new ArrayList<>());
                }
                map.get(cur).add(i);
            }
        }

        /**
         * Runtime: 69 ms, faster than 23.44% of Java online submissions for Random Pick Index.
         * Memory Usage: 52.8 MB, less than 88.24% of Java online submissions for Random Pick Index.
         *
         * @param target
         * @return
         */
        public int pick(int target) {
            if (map.containsKey(target)) {
                List<Integer> indexes = map.get(target);
                Random rand = new Random();
                int randomNum = rand.nextInt(indexes.size());
                return indexes.get(randomNum);
            } else {
                return -1;
            }
        }
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */

    class SolutionV1 {
        Map<Integer, List<Integer>> targets = new HashMap<>();
        Random r = new Random();
        public SolutionV1(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                targets.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            }
        }

        /**
         * Runtime: 69 ms, faster than 23.44% of Java online submissions for Random Pick Index.
         * Memory Usage: 48.8 MB, less than 100.00% of Java online submissions for Random Pick Index.
         *
         * @param target
         * @return
         */
        public int pick(int target) {
            List<Integer> list = targets.get(target);
            return list.get(r.nextInt(list.size()));
        }
    }

    class SolutionV2 {
        int[] nums;
        Random random;
        public SolutionV2(int[] nums) {
            this.nums = nums;
            this.random = new Random();
        }

        /**
         * Runtime: 48 ms, faster than 76.32% of Java online submissions for Random Pick Index.
         * Memory Usage: 47.8 MB, less than 100.00% of Java online submissions for Random Pick Index.
         *
         * @param target
         * @return
         */
        public int pick(int target) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target)
                    list.add(i);
            }
            return list.get(random.nextInt(list.size()));
        }
    }
}
