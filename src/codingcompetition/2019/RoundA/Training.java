package RoundA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Training {
    /**
     * Sort in descending order and each contiguous subarray could be target ones
     *
     * First is max in subarray and potential min num is sum(S[i] - S[j]) where j = i to i + P - 1
     *
     * sum(S[i] - S[j]) = P * S[i] - sum(S[j]) -> O(N)
     *
     * precompute sum(s[j]) as sums[j] in following code for each subarray -> O(N)
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        for (int i = 1; i <= caseNum; i++) {
            System.out.println(String.format("Case #%d: %s", i, solve(scanner)));
        }
    }

    private static String solve(Scanner scanner) {
        int N = scanner.nextInt();
        int P = scanner.nextInt();
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int[] sums = new int[N - P + 1];
        for (int i = 0; i < N; i++) {
            if (i < P) {
                sums[0] += arr[i];
            } else {
                sums[i - P + 1] = sums[i - P] + arr[i] - arr[i - P];
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N - P + 1; i++) {
            min = Math.min(min, (P * arr[i] - sums[i]));
        }

        return String.valueOf(min);
    }
}
