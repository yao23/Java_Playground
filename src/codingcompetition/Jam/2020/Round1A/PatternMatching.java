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
        String[] s = new String[n];
        String res = "";

        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextLine();
            if (i > 0) {
                String cur = s[i], pre = s[i - 1];
                if (!isStringMatch(cur, pre)) {
                    System.out.println("Case #" + c +": *");
                    return;
                } else {
                    res = trimStars(hasMoreChars(cur, pre));
                }
            }
        }

        System.out.println("Case #" + c +": " + res);
    }

    private static boolean isStringMatch(String s1, String s2) {
        if (s1 == null && s2 != null || s1 != null && s2 == null) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        int l1 = s1.length(), l2 = s2.length();
        if (l1 == 0 && l2 > 0 || l1 > 0 && l2 == 0) {
            return false;
        } else {
            if (l1 == 1 || l2 == 1) {
                return isCharMatch(s1.charAt(0), s2.charAt(0));
            } else {
                char left1 = s1.charAt(0), left2 = s2.charAt(0);
                char right1 = s1.charAt(l1 - 1), right2 = s2.charAt(l2 - 1);
                return isCharMatch(left1, left2) && isCharMatch(right1, right2);
            }
        }
    }

    private static boolean isCharMatch(char c1, char c2) {
        return c1 == '*' || c2 == '*' || c1 == c2;
    }

    private static String hasMoreChars(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        int lMax = Math.max(l1, l2);
        int c1 = 0, c2 = 0;
        for (int i = 0; i < lMax; i++) {
            if (i < l1) {
                if (s1.charAt(i) != '*') {
                    c1++;
                }
            }
            if (i < l2) {
                if (s2.charAt(i) != '*') {
                    c2++;
                }
            }
        }
        if (c1 < c2) {
            return s2;
        } else {
            return s1;
        }
    }

    private static String trimStars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '*') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
