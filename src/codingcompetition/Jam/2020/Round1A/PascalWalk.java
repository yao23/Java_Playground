import java.util.*;

public class Solution {
    private static int[] dx = new int[]{-1, -1, 0, 0, 1, 1};
    private static int[] dy = new int[]{-1, 0, -1, 1, 0, 1};
    private static int level = 500;
    private static List<List<Integer>> nums;

    public static void main(String[] args) {
        nums = construct();
        Scanner input = new Scanner(System.in);
        int testCase = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= testCase; i++) {
            solve(input, i);
        }
    }

    private static void solve(Scanner scanner, int c) {
        int n = Integer.parseInt(scanner.nextLine());
        Set<int[]> res = new HashSet<>();
        res.add(new int[]{0, 0});
        print(c, res, 0, 0, n - 1);
    }

    private static boolean print(int tc, Set<int[]> res, int x, int y, int sum) {
        if (sum == 0) {
            print(res, tc);
            return true;
        } else {
            int directionNum = 6;
            for (int i = 0; i < directionNum; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                int[] cur = new int[]{newX, newY};
                if (newX >= 0 && newY >= 0 && newY < newX + 1 && sum >= nums.get(newX).get(newY) && !res.contains(cur)) {
                    res.add(cur);
                    if (print(tc, res, newX, newY, sum - nums.get(newX).get(newY))) {
                        return true;
                    }
                    res.remove(cur);
                }
            }
            return false;
        }
    }

    private static void print(Set<int[]> res, int tc) {
        System.out.println("Case #" + tc +":");
        for (int[] row : res) {
            System.out.println((row[0] + 1) + " " + (row[1] + 1));
        }
    }

    private static List<List<Integer>> construct() {
        List<List<Integer>> res = new ArrayList<>(level);
        for (int i = 0; i < level; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i - 1) {
                    tmp.add(1);
                } else {
                    tmp.add(res.get(i - 1).get(j) + res.get(i - 1).get(j));
                }
            }
        }
        return res;
    }
}