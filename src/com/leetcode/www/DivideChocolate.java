public class DivideChocolate { // 1231
    /**
     * https://leetcode.com/problems/divide-chocolate/discuss/556374/Faster-than-50-JAVA
     */
    class Solution {
        public int maximizeSweetness(int[] nums, int k) {
            int l = Integer.MAX_VALUE, r = 0, m = -1, ans = -1;
            for(int i=0; i<nums.length; i++){   // find sum and min num
                r+=nums[i];
                l = Math.min(nums[i], l);
            }

            while(l<=r){
                m = l+(r-l)/2;
                boolean valid = splitPossible(m, nums, k);
                if(valid){
                    ans = m;
                    l = m+1;
                }
                else{
                    r = m-1;
                }
            }
            return ans;

        }
        private boolean splitPossible(int m, int[] nums, int k){
            int sum = 0, i = 0;
            k = k+1; //since we actually need k+1 subarrays
            while(i<nums.length){
                if(sum+nums[i] < m){
                    sum+=nums[i];
                }
                else{
                    k--;
                    sum = 0;
                }
                i++;
                if(k==0 && i<=nums.length) return true;
            }
            return false;
        }
    }

    /****
     Intuition
     Breaking down the problem:
     1) Imagine you have a function that tells if it's possible to divide
     the chocolate bar among K people whereby each person would receive
     a piece of chocolate with at least S total sweetness.
     2) How would you use the function to solve the problem?
     i) Try all possible total sweetness starting from 1,2,3... all
     the way to the highest total sweetness (sum of sweetness). // O(m) where m
     is the sum of sweetness
     ii) Since we are trying in ascending order, can we do a binary search instead?
     For example:
     lowest total sweetness = 1, highest total sweetness = 10
     mid total sweetness = 4,
     If it's possible to divide the chocolate bar with K people
     using function stated in 1 with total sweetness 4, then we want to know
     if it's possible to divide the chocolate using a higher total sweetness
     (since the question asks us to find the max total sweetness)
     We do this by assigning mid total sweetness to lowest total sweetness
     and repeat the binary search process again. ('4' could as well be our final
     answer but we want to know if we can do better thus we must
     include 4 as part of the search)
     On the other hand, if it's impossible to divde, we assign mid total sweetness - 1
     to highest total sweetness and repeat the process. (here we know for sure '4' is not
     our final answer thus we exclude it from our binary search)
     // O(log m) where m is the sum of sweetness
     3) Now think about how you would implement function in 1).
     Since the question says each piece of chocolate divided must consist of
     some consecutive chunks, we can iterate through the array sweetness, keeping a
     running sum of the total sweetness along the way.
     Once the sum is >= to the total sweetness we desire, we increment a counter variable
     and once the counter is equal to the number of people we want to share the
     chocolate bar with, we know it is possible, else it's not. // O(n) where n is the length
     of sweetness array
     4) Using binary search approach, we call the function O(log m) times and each
     time the function takes O(n) time to run. Therefore overall time complexity is
     O(nlog(m)) while using linear search would take O(n^2).
     ****/

    /**
     * https://leetcode.com/problems/divide-chocolate/discuss/555554/Java-with-explanation-binary-search
     */
    class SolutionV1 {
        public int maximizeSweetness(int[] sweetness, int K) {

            // get the sum of sweetness to use as our high pointer for binary search
            int sum = 0;
            for (int i : sweetness) {
                sum += i;
            }

            // perform binary search to get max sweetness
            int low = 1;
            int high = sum;
            while (low < high) {
                int mid = low + (high - low + 1) / 2; // a trick to move the low pointer forward
                if (canDivide(sweetness, K + 1, mid)) { // divide among K+1 people
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            return low;
        }

        private boolean canDivide(int[] sweetness, int numOfPeople, int totalSweetness) {
            int count = 0;
            int sum = 0;
            for (int i = 0; i < sweetness.length; i++) {
                sum += sweetness[i];
                if (sum >= totalSweetness) {
                    count++;
                    if (count == numOfPeople) {
                        return true;
                    }
                    sum = 0;
                }
            }

            return false;
        }
    }

    /**
     * https://leetcode.com/problems/divide-chocolate/discuss/553369/Clean-and-Easy-to-understand-JAVA-Binary-Search-solution
     */
    public int maximizeSweetness(int[] sweetness, int K) {
        int left = Integer.MAX_VALUE;
        int right = 0;
        for (int i : sweetness) {
            left = Math.min(left, i);
            right += i;
        }

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canSplit(sweetness, K + 1, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canSplit(int[] sweetness, int total, int min) {
        int sum = 0;
        int count = 0;
        for (int i : sweetness) {
            sum += i;
            if (sum >= min) {
                sum = 0;
                count++;
            }
        }
        return count >= total;
    }
}

/**
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array sweetness.
 *
 * You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces using K cuts,
 * each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * Example 2:
 *
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * Example 3:
 *
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 *
 * Constraints:
 *
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 */
