package PracticeRound;

import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = input.nextInt();

        for (int i = 1; i <= testNum; i++) {
            int score = solve(input);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int solve(Scanner input) {
        int N = input.nextInt();
        String scores = input.next();
        int max = 0;
        int resLen = N / 2 == 0 ? N / 2 : (N + 1) / 2;
        for (int i = 0; i < resLen; i++) {
            max += scores.charAt(i) - '0';
        }
        int sum = max;
        for (int i = resLen; i < N; i++) {
            sum += (scores.charAt(i) - scores.charAt(i - resLen));
            if (sum > max) {
                max = sum;
            }
        }

        return max;
    }
}
