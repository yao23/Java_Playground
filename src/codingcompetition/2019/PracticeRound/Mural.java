package PracticeRound;

import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = input.nextInt();

        for (int i = 0; i < testNum; i++) {
            int N = input.nextInt();

            int score = expand(input, N);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int expand(Scanner input, int len) {
        if (len == 1) {
            return input.nextInt();
        } else if (len == 2) {
            return Math.max(input.nextInt(), input.nextInt());
        } else {
            int max = 0;
            int radius = len / 2 == 0 ? len / 2 : (len + 1) / 2;
            int[] digitArr = new int[radius];
            for (int i = 0; i < radius; i++) {
                digitArr[i] = input.nextInt();
                max += digitArr[i];
            }
            int sum = max;
            for (int i = radius, j = 0; i < len; i++, j++) {
                sum += (input.nextInt() - digitArr[j]);
                if (sum > max) {
                    max = sum;
                }
            }

            return max;
        }
    }
}
