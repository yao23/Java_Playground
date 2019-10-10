package io.educative.www.DynamicProgramming.P02_UnboundedKnapsack;

class P02_05_MaximumRibbonCut {

    public int countRibbonPieces(int[] ribbonLengths, int total) {
        int maxPieces = this.countRibbonPiecesRecursive(ribbonLengths, total, 0);
        return maxPieces == Integer.MIN_VALUE ? -1 : maxPieces;
    }

    /**
     * The time complexity of the above algorithm is exponential O(2^{L+T}), where ‘L’ represents total ribbon lengths
     * and ‘N’ is the total length that we want to cut. The space complexity will be O(L+T).
     *
     * @param ribbonLengths
     * @param total
     * @param currentIndex
     * @return
     */
    private int countRibbonPiecesRecursive(int[] ribbonLengths, int total, int currentIndex) {
        // base check
        if (total == 0)
            return 0;

        if(ribbonLengths.length == 0 || currentIndex >= ribbonLengths.length)
            return Integer.MIN_VALUE;

        // recursive call after selecting the ribbon length at the currentIndex
        // if the ribbon length at the currentIndex exceeds the total, we shouldn't process this
        int c1 = Integer.MIN_VALUE;
        if( ribbonLengths[currentIndex] <= total ) {
            int result = countRibbonPiecesRecursive(
                    ribbonLengths, total - ribbonLengths[currentIndex], currentIndex);
            if(result != Integer.MIN_VALUE){
                c1 = result + 1;
            }
        }

        // recursive call after excluding the ribbon length at the currentIndex
        int c2 = countRibbonPiecesRecursive(ribbonLengths, total, currentIndex + 1);
        return Math.max(c1, c2);
    }

    /**
     * time and space complexity of O(L*N), where ‘L’ represents total ribbon lengths and ‘N’ is the total length that
     * we want to cut.
     * @param ribbonLengths
     * @param total
     * @return
     */
    public int countRibbonPiecesV2(int[] ribbonLengths, int total) {
        int n = ribbonLengths.length;
        int[][] dp = new int[n][total + 1];

        for(int i=0; i < n; i++)
            for(int j=0; j <= total; j++)
                dp[i][j] = Integer.MIN_VALUE;

        // populate the total=0 columns, as we don't need any ribbon to make zero total
        for(int i=0; i < n; i++)
            dp[i][0] = 0;

        for(int i=0; i < n; i++) {
            for(int t=1; t <= total; t++) {
                if(i > 0) //exclude the ribbon
                    dp[i][t] = dp[i-1][t];
                // include the ribbon and check if the remaining length can be cut into available lengths
                if(t >= ribbonLengths[i] && dp[i][t-ribbonLengths[i]] != Integer.MIN_VALUE)
                    dp[i][t] = Math.max(dp[i][t], dp[i][t-ribbonLengths[i]]+1);
            }
        }

        // total combinations will be at the bottom-right corner, return '-1' if cutting is not possible
        return (dp[n-1][total] == Integer.MIN_VALUE ? -1 : dp[n-1][total]);
    }

    public static void main(String[] args) {
        P02_05_MaximumRibbonCut cr = new P02_05_MaximumRibbonCut();
        int[] ribbonLengths = {2,3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
        ribbonLengths = new int[]{3,5,7};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
    }
}