import java.util.*;

public class Solution {

    private static boolean found = false;
    private static List<List<Integer>> result = new ArrayList<>();

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
        int n = Integer.parseInt(splited[0]);
        int trace = Integer.parseInt(splited[1]);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> perm = permute(nums);
        helper(perm, trace, n);
        String res = found ? "POSSIBLE" : "IMPOSSIBLE";
        System.out.println("Case #" + tc + ": " + res);
        if (found) {
            printResult(n);
        }
        found = false;
        result = null;
    }

    private static void printResult(int n) {
        int r = result.size();
        for (int i = 0; i < r; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(result.get(i).get(j) + " ");
            }
            System.out.println(sb.toString());
        }
    }

    private static void helper(List<List<Integer>> perm, int trace, int n) {
        int offset = getOffset(n);
        helper(perm, 0, n, trace, offset, new HashSet<>(), new HashSet<>(), new ArrayList<>());
    }

    private static void helper(List<List<Integer>> perm, int k, int n, int trace, int offset, Set<Integer> added, Set<Integer> skip, List<List<Integer>> res) {
        if (k == n) {
            if (isValid(res, n)) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += res.get(i).get(i);
                }
                if (sum == trace) {
                    found = true;
                    result = new ArrayList<>(res);
                }
            }
        } else {
            if (found) {
                return;
            }

            int permSize = perm.size();
            for (int i = 0; i < permSize; i++) {
                List<Integer> l = perm.get(i);
                Integer curNum = l.get(0);
                if (!added.contains(curNum) && !skip.contains(curNum)) {
                    res.add(l);
                    if (isValid(res, n)) {
                        added.add(curNum);
                        helper(perm, k + 1, n, trace, offset, added, skip, res);
                        added.remove(curNum);
                    }
                    res.remove(res.size() - 1);

                    skip.add(curNum);
                    helper(perm, k, n, trace, offset, added, skip, res);
                    skip.remove(curNum);
                } else {
                    i = (i / offset + 1) * offset;
                }
            }
        }
    }

    private static int getOffset(int n) {
        int res = 1;
        for (int i = 1; i < n; i++) {
            res *= i;
        }
        return res;
    }

    private static boolean isValid(List<List<Integer>> res, int n) {
        int r = res.size();
        for (int i = 1; i < r; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < n; k++) {
                    if (res.get(j).get(k).equals(res.get(i).get(k))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>();
        helper(nums, res, list, 0);
        return res;
    }

    private static void helper(int[] nums, List<List<Integer>> res, List<Integer> list, int pos) {
        if (pos == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            list.add(nums[i]);
            swap(nums, pos, i);
            helper(nums, res, list, pos + 1); // pos + 1 (pos: cur level)
            swap(nums, pos, i);
            list.remove(list.size() - 1);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}