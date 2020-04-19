import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= testCase; i++) {
            solve(input, i);
        }
    }

    private static void solve(Scanner scanner, int c) {
        String s = scanner.nextLine();
        String[] splited = s.split("\\s+");
        int N = Integer.parseInt(splited[0]), D = Integer.parseInt(splited[1]);
        int res = 0;
        if (N == 1) {
            int x = Integer.parseInt(scanner.nextLine());
            if (D == 1) {
                res = 1;
            } else {
                res = process(x, D, 0);
            }
        } else {
            String str = scanner.nextLine();
            splited = str.split("\\s+");
            int pre = Integer.parseInt(splited[N - 1]), cur = Integer.parseInt(splited[N - 2]);
            int processedPre = process(pre, D, 0), processedCur = process(cur, D, 0);

            for (int i = N - 2; i >= 0; i--) {
                int offset = 1;
                while (processedCur != cur && processedPre < processedCur) {
                    processedCur = process(cur, D, offset++);
                }

                if (i >= 1) {
                    cur = Integer.parseInt(splited[i - 1]);
                    processedPre = processedCur;
                    processedCur = process(cur, D, 0);
                } else {
                    res = processedCur;
                }
            }
        }

        System.out.println("Case #" + c + ": " + res);
    }

    private static int process(int x, int D, int offset) {
        return (D / x - offset) * x;
    }
}