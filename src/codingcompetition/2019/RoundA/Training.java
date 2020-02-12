package RoundA;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Training {
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
//        System.out.println("Arr:");
//        for (int i = 0; i < N; i++) {
//            System.out.print(arr[i] + ", ");
//        }
//        System.out.println();
        Arrays.sort(arr, Collections.reverseOrder());
//        System.out.println("Sorted Arr:");
//        for (int i = 0; i < N; i++) {
//            System.out.print(arr[i] + ", ");
//        }
//        System.out.println();

        int[] sums = new int[N - P + 1];
        for (int i = 0; i < N; i++) {
            if (i < P) {
                sums[0] += arr[i];
            } else {
                sums[i - P + 1] = sums[i - P] + arr[i] - arr[i - P];
            }
        }
//        System.out.println("Sums: ");
//        for (int i = 0; i < sums.length; i++) {
//            System.out.print(sums[i] + ", ");
//        }
//        System.out.println();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N - P + 1; i++) {
//            System.out.print((P * arr[i] - sums[i]) + ", ");
            min = Math.min(min, (P * arr[i] - sums[i]));
        }

        return String.valueOf(min);
    }
}
