import java.util.*;

public class ShortestWayToFormString { // 1055 [Google]
    /**
     * https://leetcode.com/problems/shortest-way-to-form-string/discuss/555308/Short-and-Simple-JAVA-code
     *
     * @param source
     * @param target
     * @return
     */
    public int shortestWay(String source, String target) {
        Set<Character> set = new HashSet<>();
        for (char c : source.toCharArray()) set.add(c);
        for (char c : target.toCharArray())
            if (!set.contains(c)) return -1;

        int j = 0, count = 0;;
        while (j < target.length()) {
            for (int i = 0; i < source.length(); i++) {
                if (j < target.length() && source.charAt(i) == target.charAt(j)) j++;
            }
            count++;
        }
        return count;
    }

    /**
     * when we encounter a char at target string, we try to find that same char in source string with the smallest
     * possible index.
     * https://leetcode.com/problems/shortest-way-to-form-string/discuss/554968/Concise-JAVA-binary-search-solution
     *
     */
    public int shortestWayV1(String source, String target) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (!map.containsKey(c)) map.put(c, new ArrayList<>());
            map.get(c).add(i);
        }
        int count = 1;
        int prev = -1;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (!map.containsKey(c)) return -1;
            if (prev == -1) {
                prev = map.get(c).get(0);
            } else {
                int pos = Collections.binarySearch(map.get(c), prev);
                if (pos >= 0) pos++;
                else pos = - (pos + 1);
                if (pos >= map.get(c).size()) {
                    prev = map.get(c).get(0);
                    count++;
                } else {
                    prev = map.get(c).get(pos);
                }
            }
        }
        return count;
    }
}

/**
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation
 * equals target. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
 * Example 3:
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 *
 * Constraints:
 *
 * Both the source and target strings consist of only lowercase English letters from "a"-"z".
 * The lengths of source and target string are between 1 and 1000.
 *
 */
