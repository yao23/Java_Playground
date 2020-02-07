package PracticeRound;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = Integer.parseInt(input.nextLine());

        for (int i = 0; i < testNum; i++) {
            int N = Integer.parseInt(input.nextLine());

            String digits = input.nextLine();

            int score = expand(digits, N);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int expand(String digits, int len) {
        if (len == 1) {
            return Integer.parseInt(digits);
        } else if (len == 2) {
            String[] digitsArr = digits.split("\\s+");
            return Math.max(Integer.parseInt(digitsArr[0]), Integer.parseInt(digitsArr[1]));
        } else {
            int max = 0, numIdx = 0, sum = 0, sumStart = 0;
            int radius = len / 2 == 0 ? len / 2 : (len + 1) / 2;
            String tmp = "";
            List<Integer> nums = new ArrayList<>();
            for (char ch : digits.toCharArray()) {
                if (ch != ' ') {
                    tmp += ch;
                } else {
                    int cur = Integer.parseInt(tmp);
                    nums.add(cur);
                    if (numIdx < radius) {
                        sum += cur;
                    } else {
                        if (sum > max) {
                            max = sum;
                        }
                        sum += (cur - nums.get(sumStart));
                        sumStart++;
                    }
                    tmp = "";
                    numIdx++;
                }
            }

            if (sum > max) {
                max = sum;
            }

            return max;
        }
    }
}
