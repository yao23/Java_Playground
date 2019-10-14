package io.educative.www.DynamicProgramming.P03_FibonacciNumbers;

class P03_04_MinimumJumpsToReachTheEnd {

    public int countMinJumps(int[] jumps) {
        return this.countMinJumpsRecursive(jumps, 0);
    }

    /**
     * The time complexity of the above algorithm is O(2^n), where ‘n’ is the size of the input array. The ‘while loop’
     * can execute a maximum of ‘n’ times (for the case where we can jump to all the steps ahead) and since in each
     * iteration, the function recursively calls itself, therefore, the time complexity is O(2^n). The space complexity
     * is O(n) which is used to store the recursion stack.
     *
     * @param jumps
     * @param currentIndex
     * @return
     */
    private int countMinJumpsRecursive(int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if( currentIndex == jumps.length - 1)
            return 0;

        if (jumps[currentIndex] == 0)
            return Integer.MAX_VALUE;

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        while(start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsRecursive(jumps, start++);
            if(minJumps != Integer.MAX_VALUE)
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }
        return totalJumps;
    }

    /**
     * Top-down Dynamic Programming with Memoization
     *
     * @param jumps
     * @return
     */
    public int countMinJumpsV1(int[] jumps) {
        int dp[] = new int[jumps.length];
        return this.countMinJumpsRecursiveV1(dp, jumps, 0);
    }

    private int countMinJumpsRecursiveV1(int[] dp, int[] jumps, int currentIndex) {
        // if we have reached the last index, we don't need any more jumps
        if( currentIndex == jumps.length - 1)
            return 0;

        // If an element is 0, then we cannot move through that element
        if (jumps[currentIndex] == 0)
            return Integer.MAX_VALUE;

        // if we have already solved this problem, return the result
        if(dp[currentIndex] != 0)
            return dp[currentIndex];

        int totalJumps = Integer.MAX_VALUE;
        int start = currentIndex + 1;
        int end = currentIndex + jumps[currentIndex];
        while(start < jumps.length && start <= end) {
            // jump one step and recurse for the remaining array
            int minJumps = countMinJumpsRecursive(dp, jumps, start++);
            if(minJumps != Integer.MAX_VALUE)
                totalJumps = Math.min(totalJumps, minJumps + 1);
        }
        dp[currentIndex] = totalJumps;
        return dp[currentIndex];
    }

    /**
     * time complexity of O(n^2) (because of the two for loops) and space complexity of O(n) to store dp[].
     * @param jumps
     * @return
     */
    public int countMinJumpsV2(int[] jumps) {
        int[] dp = new int[jumps.length];

        //initialize with infinity, except the first index which should be zero as we start from there
        for(int i=1; i<jumps.length; i++)
            dp[i] = Integer.MAX_VALUE;

        for(int start=0; start < jumps.length-1; start++) {
            for(int end=start+1; end <= start+jumps[start] && end < jumps.length; end++)
                dp[end] = Math.min(dp[end], dp[start]+1);
        }

        return dp[jumps.length-1];
    }

    public static void main(String[] args) {
        P03_04_MinimumJumpsToReachTheEnd aj = new P03_04_MinimumJumpsToReachTheEnd();
        int[] jumps = {2, 1, 1, 1, 4};
        System.out.println(aj.countMinJumps(jumps));
        jumps = new int[]{1, 1, 3, 6, 9, 3, 0, 1, 3};
        System.out.println(aj.countMinJumps(jumps));
    }
}