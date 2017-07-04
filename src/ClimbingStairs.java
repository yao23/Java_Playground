/**
 * Created by liyao on 7/3/17.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        return climbV1(n);
    }

    private int climbV1(int n) {
        if (n <= 2) {
            return n;
        } else {
            int res[] = new int[n];
            res[0] = 1;
            res[1] = 2;

            for (int i = 2; i < n; i++) {
                res[i] = res[i - 1] + res[i - 2];
            }

            return res[n - 1];
        }
    }

    private int climb(int n) { // Time Limit Exceeded for n = 44
        if (n <= 2) {
            return n;
        } else {
            return climb(n - 1) + climb(n - 2);
        }
    }

    // 1 => 1
    // 2 => 2
    // 3 => 3
    // 4 => 5
    // 5 => 8
    // 44 => 1134903170

    // beats 12.92%
}
