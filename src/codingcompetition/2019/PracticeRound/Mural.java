package PracticeRound;

import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = input.nextInt();

        for (int i = 0; i < testNum; i++) {
            int score = expand(input);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int expand(Scanner input) {
        int len = input.nextInt();

        if (len == 1) {
            return input.nextInt();
        } else if (len == 2) {
            return Math.max(input.nextInt(), input.nextInt());
        } else {
            String digits = input.next();
            int max = 0;
            int radius = len / 2 == 0 ? len / 2 : (len + 1) / 2;
            for (int i = 0; i < radius; i++) {
                max += digits.charAt(i) - '0';
            }
            int sum = max;
            for (int i = radius; i < len; i++) {
                sum += (digits.charAt(i) - digits.charAt(i - radius));
                if (sum > max) {
                    max = sum;
                }
            }

            return max;
        }
    }
}
