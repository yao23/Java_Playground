package PracticeRound;

import java.util.Scanner;

public class KickstartAlarm {
    /**
     * https://codingcompetitions.withgoogle.com/kickstart/round/0000000000051060/0000000000058a56
     *
     * https://www.topcoder.com/problem-of-the-week-google-kickstart-alarm/
     * https://blog.csdn.net/IceTeaSet/article/details/80472573
     * https://blog.csdn.net/ihsin/article/details/81839635
     * https://github.com/amylmy/KickStart-GoogleCompetition/blob/master/2019_PracticeRound_KickstartAlarm.java
     */
    private static int mod = 1000000007;
    private static long[] arr = new long[1000001];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %s", i, solve(scanner)));
        }
    }

    private static String solve(Scanner scanner) {
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        long x1 = scanner.nextInt();
        long y1 = scanner.nextInt();
        int C = scanner.nextInt();
        int D = scanner.nextInt();
        int E1 = scanner.nextInt();
        int E2 = scanner.nextInt();
        int F = scanner.nextInt();

        int i;
        long x, y;
        arr[1] = (x1 + y1) % F;
        for (i = 2; i <= N; i++) {
            x = (C * x1 + D * y1 + E1) % F;
            y = (D * x1 + C * y1 + E2) % F;
            arr[i] = (x + y) % F;
            x1 = x;
            y1 = y;
        }
        long ans = 0;
        long la = K;
        for (i = 2; i <= N + 1; i++) {
            ans = (ans + la * (long)(N + 2 - i) % mod * arr[i - 1] % mod) % mod;
            x = (power(i,K + 1) - 1) * power(i - 1,mod - 2) % mod; // if P is prime, (1 / a) % P = a ^ (P - 2) => (1 / (i - 1) ) % mod = power (i - 1, mod - 2)
            x--;
            if (x < 0) {
                x += mod;
            }
            la += x;
            la %= mod;
        }


        return String.valueOf(ans);
    }

    private static long power(long x,long y) {
        long t = 1;
        while (y != 0) {
            if (y % 2 == 1) {
                t = t * x % mod;
            }
            x= x * x % mod;
            y /= 2;
        }
        return t;
    }
}
