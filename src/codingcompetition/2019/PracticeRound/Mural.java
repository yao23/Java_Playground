package PracticeRound;

import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = Integer.parseInt(input.nextLine());

        for (int i = 0; i < testNum; i++) {
            int N = Integer.parseInt(input.nextLine());

            String str = input.nextLine();
            String[] digits = str.split("\\s+");

            int score = expand(digits, N);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int expand(String[] digits, int len) {
        if (len == 1) {
            return Integer.parseInt(digits[0]);
        } else if (len == 2) {
            return Math.max(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
        } else {
            int max = 0;
            int radius = len / 2 == 0 ? len / 2 : (len + 1) / 2;
            for (int i = 0; i < radius; i++) {
                max += Integer.parseInt(digits[i]);
            }
            int tmp;
            for (int i = radius, j = 0; i < len; i++, j++) {
                tmp = max - Integer.parseInt(digits[j]) + Integer.parseInt(digits[i]);
                if (tmp > max) {
                    max = tmp;
                }
            }

            return max;
        }
    }
}
