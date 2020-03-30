import java.util.*;

public class MinimumRemoveToMakeValidParentheses {
    /**
     * Runtime: 21 ms, faster than 29.68% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     * Memory Usage: 40.8 MB, less than 100.00% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     *
     * Time complexity : O(n), where nn is the length of the input string.
     * Space complexity : O(n), where nn is the length of the input string.
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        Set<Integer> indexesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        // Put any indexes remaining on stack into the set.
        while (!stack.isEmpty()) indexesToRemove.add(stack.pop());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indexesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * Runtime: 15 ms, faster than 78.86% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     * Memory Usage: 42.4 MB, less than 100.00% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValidV1(String s) {
        // Parse 1: Remove all invalid ")"
        StringBuilder sb = new StringBuilder();
        int openSeen = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openSeen++;
                balance++;
            } if (c == ')') {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }

        // Parse 2: Remove the rightmost "("
        StringBuilder result = new StringBuilder();
        int openToKeep = openSeen - balance;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                openToKeep--;
                if (openToKeep < 0) continue;
            }
            result.append(c);
        }

        return result.toString();
    }

    /**
     * Runtime: 22 ms, faster than 23.97% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     * Memory Usage: 40.1 MB, less than 100.00% of Java online submissions for Minimum Remove to Make Valid Parentheses.
     *
     * Time complexity : O(n), where nn is the length of the input string.
     * Space complexity : O(n), where nn is the length of the input string.
     *
     * @param s
     * @return
     */
    public String minRemoveToMakeValidV0(String s) {
        StringBuilder result = removeInvalidClosing(s, '(', ')');
        result = removeInvalidClosing(result.reverse(), ')', '(');
        return result.reverse().toString();
    }

    private StringBuilder removeInvalidClosing(CharSequence string, char open, char close) {
        StringBuilder sb = new StringBuilder();
        int balance = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == open) {
                balance++;
            } if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }
        return sb;
    }
}
