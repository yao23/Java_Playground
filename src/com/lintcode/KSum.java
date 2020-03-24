public class Solution {
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     *
     * https://www.lintcode.com/problem/k-sum/note/211317
     */
    public int  kSum(int A[], int k, int target) {
        int n = A.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        for (int i = 0; i < n + 1; i++) {
            f[i][0][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    f[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        f[i][j][t] = f[i - 1][j - 1][t - A[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t];
                } // for t
            } // for j
        } // for i
        return f[n][k][target];
    }

    /**
     * https://www.jiuzhang.com/solution/k-sum/
     *
     * 3+7+4+9=23
     * 3+5+6+9=23
     * 1+7+6+9=23
     * 1+5+7+10=23
     * 0+7+6+10=23
     * 3+4+6+10=23
     * 1+3+9+10=23
     * 0+4+9+10=23
     * 0+3+7+13=23
     * 1+5+4+13=23
     * 1+3+6+13=23
     * 0+4+6+13=23
     * 1+0+9+13=23
     */
    public static void main(String []args){
        int[] arr = new int[]{1, 0, 3, 5, 7, 4, 6, 9, 10, 13};
        int k = 4;
        int target = 23;
        kSum(arr, k, target);
    }

    static int[] res;
    static int tot;
    static int[] A;
    static int K;
    static int[][][] f;

    private static void printAnswer(int i, int j, int k) {
        if (j == 0) {
            for (int h = 0; h < K; ++h) {
                System.out.print(res[h]);
                if (h == K - 1) {
                    System.out.println("=" + tot);
                }
                else {
                    System.out.print("+");
                }
            }

            return;
        }

        if (f[i - 1][j][k] > 0) {
            printAnswer(i - 1, j, k);
        }

        if (j > 0 && k >= A[i - 1] && f[i - 1][j - 1][k - A[i - 1]] > 0) {
            res[j - 1] = A[i - 1];
            printAnswer(i - 1, j - 1, k - A[i - 1]);
        }
    }

    public static int kSum(int[] AA, int KK, int T) {
        K = KK;
        A = AA;
        int n = A.length;
        res = new int[K];
        tot = T;
        f = new int[n + 1][K + 1][T + 1];
        int i, j, k;
        for (j = 0; j <= K; ++j) {
            for (k = 0; k <= T; ++k) {
                f[0][j][k] = 0;
            }
        }

        f[0][0][0] = 1;
        for (i = 1; i <= n; ++i) {
            for (j = 0; j <= K; ++j) {
                for (k = 0; k <= T; ++k) {
                    // not using A[i - 1]
                    f[i][j][k] = f[i - 1][j][k];

                    // using A[i - 1]
                    if (j > 0 && k >= A[i - 1]) {
                        f[i][j][k] += f[i - 1][j - 1][k - A[i - 1]];
                    }
                }
            }
        }

        printAnswer(n, K, T);

        return f[n][K][T];
    }
}
