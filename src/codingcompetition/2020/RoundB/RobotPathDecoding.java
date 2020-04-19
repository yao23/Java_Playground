import java.util.*;

public class Solution {
    private static int modNum = (int) Math.pow(10, 9);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= testCase; i++) {
            solve(input, i);
        }
    }

    private static void solve(Scanner scanner, int tc) {
        String s = scanner.nextLine();
        int c = 1, pC = 0, cV = 0, cH = 0, len = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            switch (cur) {
                case 'S':
                    cV += c;
                    break;
                case 'N':
                    cV -= c;
                    break;
                case 'E':
                    cH += c;
                    break;
                case 'W':
                    cH -= c;
                    break;
                case '(':
                    pC++;
                    break;
                case ')':
                    pC--;
                    c /= stack.pop();
                    break;
                default:
                    stack.push(cur - '0');
                    c *= (cur - '0');
                    break;
            }
        }

        int[] res = process(cV, cH);
        System.out.println("Case #" + tc + ": " + res[0] + " " + res[1]);
    }

    private static int[] process(int cV, int cH) {
        int[] res = new int[2];
        res[0] = getRes(cH + 1);
        res[1] = getRes(cV + 1);
        return res;
    }

    private static int getRes(int x) {
        if (x == 0) {
            return modNum;
        } else if (x < 0) {
            return x + modNum;
        } else {
            return x % modNum;
        }
    }
}