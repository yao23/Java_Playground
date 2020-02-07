package PracticeRound;

import java.util.Arrays;
import java.util.Scanner;

public class Mural {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testNum = Integer.parseInt(input.nextLine());

        for (int i = 0; i < testNum; i++) {
            int N = Integer.parseInt(input.nextLine());

            String str = input.nextLine();
            String[] digits = str.split("\\s+");

            int score = 0, max = 0, maxIdx = 0;
            for (int j = 0; j < N; j++) {
                int cur = Integer.parseInt(digits[j]);
                if (cur > max) {
                    max = cur;
                    maxIdx = j;
                }
            }

            score += max;
            score += expand(maxIdx, digits);

            System.out.println("Case #" + i + ": " + score);
        }
    }

    private static int expand(int maxIdx, String[] digits) {
        int len = digits.length;
        if (maxIdx == 0 || maxIdx >= len - 1) {
            return 0;
        }
        if (len == 1) {
            return Integer.parseInt(digits[0]);
        } else if (len == 2) {
            return Math.max(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
        } else {
            int score = 0, left = 0, right = 0;
            if (maxIdx > 0) {
                left = Integer.parseInt(digits[maxIdx - 1]);
            }
            if (maxIdx + 1 < len) {
                right = Integer.parseInt(digits[maxIdx + 1]);
            }

            if (maxIdx == 0) {
                score += right;
                score += expand(0, Arrays.copyOfRange(digits, 1, len));
            } else if (maxIdx == len - 1) {
                score += left;
                score += expand(len - 2, Arrays.copyOfRange(digits, 0, len - 1));
            } else {
                int start = Integer.parseInt(digits[0]), end = Integer.parseInt(digits[len - 1]);
                if (left > right) { // move left
                    score += left;
                    if (maxIdx - 1 > 0) {
                        if (start < end) {
                            score += expand(maxIdx, Arrays.copyOfRange(digits, 1, len)); // cut start
                        } else {
                            score += expand(maxIdx - 1, Arrays.copyOfRange(digits, 0, len - 1)); // cut end
                        }
                    } else {
                        score += expand(maxIdx - 1, Arrays.copyOfRange(digits, 0, len - 1)); // cut end
                    }
                } else { // move right
                    score += right;
                    if (maxIdx + 1 < len - 1) {
                        if (start < end) {
                            score += expand(maxIdx + 2, Arrays.copyOfRange(digits, 1, len)); // cut start
                        } else {
                            score += expand(maxIdx + 1, Arrays.copyOfRange(digits, 0, len - 1)); // cut end
                        }
                    } else {
                        score += expand(maxIdx + 2, Arrays.copyOfRange(digits, 1, len)); // cut start
                    }
                }
            }

            return score;
        }
    }

    private static int expand2(int maxIdx, String[] digits) {
        int len = digits.length;
        if (maxIdx == 0 || maxIdx >= len - 1) {
            return 0;
        }
        if (len == 1) {
            return Integer.parseInt(digits[0]);
        } else if (len == 2) {
            return Math.max(Integer.parseInt(digits[0]), Integer.parseInt(digits[1]));
        } else {
            int radius = len / 2 == 0 ? len / 2 : (len + 1) / 2;
            int left = 0, right = 0;
            if (maxIdx - (radius - 1) >= 0) {
                for (int i = maxIdx - 1; i >= maxIdx - (radius - 1); i--) {
                    left += Integer.parseInt(digits[i]);
                }
            }
            if (maxIdx + (radius - 1) < len) {
                for (int i = maxIdx + 1; i <= maxIdx + (radius - 1); i++) {
                    right += Integer.parseInt(digits[i]);
                }
            }

            return Math.max(left, right);
        }
    }
}
