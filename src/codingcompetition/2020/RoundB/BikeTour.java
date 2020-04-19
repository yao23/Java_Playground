import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int testCase = Integer.parseInt(input.nextLine());
        for (int i = 1; i <= testCase; i++) {
            solve(input, i);
        }
    }

    private static void solve(Scanner scanner, int c) {
        int n = Integer.parseInt(scanner.nextLine());
        String s = scanner.nextLine();
        String[] splited = s.split("\\s+");
        int count = 0;
        if (n > 2) {
            int pre = Integer.parseInt(splited[0]), cur = Integer.parseInt(splited[1]), next = Integer.parseInt(splited[2]);
            for (int i = 1; i < n - 1; i++) {
                if (pre < cur && cur > next) {
                    count++;
                }

                pre = cur;
                cur = next;
                next = i + 2 < n ? Integer.parseInt(splited[i + 2]) : 0;
            }
        }

        System.out.println("Case #" + c +": " + count);
    }
}