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
        String[] splited = s.split("\\s+");
        int r = Integer.parseInt(splited[0]), c = Integer.parseInt(splited[1]);
        String res = "IMPOSSIBLE";
        if ((r + c) % 2 != 0) {
            List<String> tmp = new ArrayList<>();
            if (r % 2 != 0) {
                tmp.add("E");
                if (process(1, 0, r, c, tmp, 1)) {
                    res = String.join("", tmp);
                } else {
                    tmp.clear();
                    tmp.add("W");
                    if (process(-1, 0, r, c, tmp, 1)) {
                        res = String.join("", tmp);
                    }
                }
            } else {
                tmp.add("N");
                if (process(0, 1, r, c, tmp, 1)) {
                    res = String.join("", tmp);
                } else {
                    tmp.clear();
                    tmp.add("S");
                    if (process(0, -1, r, c, tmp, 1)) {
                        res = String.join("", tmp);
                    }
                }
            }
        }
        System.out.println("Case #" + tc +": " + res);
    }

    private static boolean process(int x, int y, int r, int c, List<String> tmp, int depth) {
        if (x == r && y == c) {
            return true;
        } else {
            int step = (int) Math.pow(2, depth);
            if (x != r) {
                if (x + step <= r) {
                    tmp.add("E");
                    if (process(x + step, y, r, c, tmp, depth + 1)) {
                        return true;
                    }
                    tmp.remove(tmp.size() - 1);
                }
                if (x - step >= r) {
                    tmp.add("W");
                    if (process(x - step, y, r, c, tmp, depth + 1)) {
                        return true;
                    }
                    tmp.remove(tmp.size() - 1);
                }
            }
            if (y != c) {
                if (y + step <= c) {
                    tmp.add("N");
                    if (process(x, y + step, r, c, tmp, depth + 1)) {
                        return true;
                    }
                    tmp.remove(tmp.size() - 1);
                }
                if (y - step >= c) {
                    tmp.add("S");
                    if (process(x, y - step, r, c, tmp, depth + 1)) {
                        return true;
                    }
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
        return false;
    }
}