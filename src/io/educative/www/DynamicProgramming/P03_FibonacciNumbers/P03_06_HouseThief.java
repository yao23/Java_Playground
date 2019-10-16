package io.educative.www.DynamicProgramming.P03_FibonacciNumbers;

class P03_06_HouseThief {

    public int findMaxSteal(int[] wealth) {
        return findMaxStealRecursive(wealth, 0);
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^n). The space complexity is O(n) which is used to
     * store the recursion stack.
     *
     * @param wealth
     * @param currentIndex
     * @return
     */
    private int findMaxStealRecursive(int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        // steal from current house and skip one to steal from the next house
        int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(wealth, currentIndex + 2);
        // skip current house to steel from the adjacent house
        int skipCurrent = findMaxStealRecursive(wealth, currentIndex + 1);

        return Math.max(stealCurrent, skipCurrent);
    }

    public int findMaxStealV1(int[] wealth) {
        int dp[] = new int[wealth.length];
        return findMaxStealRecursiveV1(dp, wealth, 0);
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param dp
     * @param wealth
     * @param currentIndex
     * @return
     */
    private int findMaxStealRecursiveV1(int[] dp, int[] wealth, int currentIndex) {
        if( currentIndex >= wealth.length)
            return 0;

        if(dp[currentIndex] == 0) {
            // steal from current house and skip one to steal next
            int stealCurrent = wealth[currentIndex] + findMaxStealRecursive(dp, wealth, currentIndex + 2);
            // skip current house to steel from the adjacent house
            int skipCurrent = findMaxStealRecursive(dp, wealth, currentIndex + 1);

            dp[currentIndex] = Math.max(stealCurrent, skipCurrent);
        }
        return dp[currentIndex];
    }

    /**
     * Bottom-up Dynamic Programming
     *
     * time and space complexity of O(n).
     *
     * @param wealth
     * @return
     */
    public int findMaxStealV2(int[] wealth) {
        if(wealth.length == 0) return 0;
        int dp[] = new int[wealth.length+1]; // '+1' to handle the zero house
        dp[0] = 0; // if there are no houses, the thief can't steal anything
        dp[1] = wealth[0]; // only one house, so the thief have to steal from it

        // please note that dp[] has one extra element to handle zero house
        for(int i=1; i < wealth.length; i++)
            dp[i+1] = Math.max(wealth[i] + dp[i-1], dp[i]);

        return dp[wealth.length];
    }

    /**
     * Memory optimization
     *
     * time complexity of O(n) and a constant space complexity O(1)
     *
     * @param wealth
     * @return
     */
    public int findMaxStealV3(int[] wealth) {
        if(wealth.length == 0) return 0;
        int n1=0, n2=wealth[0], temp;
        for(int i=1; i < wealth.length; i++) {
            temp = Math.max(n1 + wealth[i], n2);
            n1 = n2;
            n2 = temp;
        }
        return n2;
    }

    public static void main(String[] args) {
        P03_06_HouseThief ht = new P03_06_HouseThief();
        int[] wealth = {2, 5, 1, 3, 6, 2, 4};
        System.out.println(ht.findMaxSteal(wealth));
        wealth = new int[]{2, 10, 14, 8, 1};
        System.out.println(ht.findMaxSteal(wealth));
    }
}

