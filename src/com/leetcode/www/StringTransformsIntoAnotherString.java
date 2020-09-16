import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringTransformsIntoAnotherString { // 1153 [Google]
    /**
     * O(N)
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> charsUsed = new HashSet<>();
        int numChars = str1.length();
        for (int i = 0; i < numChars; i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            charsUsed.add(c2);
            if (mapping.containsKey(c1)) {
                if (mapping.get(c1) != c2) return false;
            } else {
                mapping.put(c1, c2);
            }
        }
        return charsUsed.size() < 26 || str1.equals(str2);
    }

    /**
     * Map<Character, Character> root is used as conversion mapping.
     * int[] chars is used to track characters that did not appear in str2, we need this array because we can use a
     * character that did not appear in str2 as tmp character to resolve a conversion cycle every time we see one.
     * You can think of tmp character as tmp int when you try to swap two elements in a int array.
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean canConvertV1(String str1, String str2) {
        if (str1.equals(str2)) return true;

        int[] chars = new int[26];
        Map<Character, Character> root = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            chars[c2 - 'a']++;
            if (c1 == c2) continue;
            if (!root.containsKey(c1)) {
                root.put(c1, c2);
            } else {
                if (root.get(c1) != c2) return false;
            }
        }
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 0) count++;
        }
        return count > 0;
    }

    /**
     * class Solution:
     *     def canConvert(self, str1: str, str2: str) -> bool:
     *         # let's first consider a valid case:
     *         # a a b c c
     *         # c c d e e
     *
     * 		# You might look at this and say something like: "a goes to c, b goes to d, c goes to e"
     * 		# Hearing that sort of language in your head should immediately hint at framing as a graph, (or maybe chain of nodes like a linked list)
     * 		# That being said, let's consider the graph:
     *         # a -> c
     *         # b -> d
     *         # c -> e
     *
     *         # In this valid case, we notice our edges describe a directed acyclic graph with no forks
     *         # hypothesis: transformation is possible iff character edges form a DAG with no forks
     *
     *         # Let's test our hypothesis:
     *
     *         # Consider an invalid case:
     *         # l e e t c o d e
     *         # c o d e l e e t
     *
     *         # the edges are
     *         # l -> c
     *         # e -> o  |
     *         # e -> d  |-> fork
     *         # t -> e
     *         # c -> l   -> cycle (l -> c -> l)
     *         # o -> e  |
     *         # d -> e  |-> fork
     *         # e -> t
     *
     *         # in fact, an invalid transformation seems to produce a forking and cyclic graph
     *         # so far so good with respect to our hypothesis
     *
     *         # let's continue testing the hypothesis on the particular predicates:
     *         # we introduce a cycle to the valid case above to consider the cycle predicate of our hypothesis
     *         # i.e. aabcc -> ccdaa
     *         #                c -> z       a -> c      b -> d       z -> a
     *         # a a b c c -> a a b z z -> c c b z z -> c c d z z -> c c d a a
     *         # c c d a a
     *
     *         # hence, cycles are fine as long as there are characters available to use as temporary variables
     *
     *         # consider a very simple case of introducing a fork:
     *         # original: a a
     *         # target:   b c
     *         # This should make it pretty clear why a fork is *not* amenable with extra characters or any circumstances
     *
     *         # Now we can propose a theorem
     *         # Theorem: transformation is possible iff:
     *         #   * character edges form a linear directed graph (no forks)
     *         #   * there are characters in the lexicon not accounted for in the strings that can be used as temporary variables
     *
     *         # Before we begin with the clever part we just discovered, there's still low hanging fruit we can short circuit on:
     *         # are the strings same length?
     *         if len(str1) != len(str2):
     *             return False
     *
     *         # are we already the same string?
     *         if str1 == str2:
     *             return True
     *
     *         # Now let's express our theorem in code:
     *         # let's form our graph, we can represent it as a map describing the edges:
     *         edges = dict()
     *         for c1, c2 in zip(str1, str2):
     *             if c1 not in edges:        # This clause corresponds to simply discovering a new edge
     *                 edges[c1] = c2
     *             elif edges[c1] != c2:      # This clause corresponds to discovering a fork, which we concluded implies impossibility of transformation
     *                 return False
     *             # else: ...                  The implied `else` clause here just means re-discovering an identical edge so no operation necessary
     *
     *
     *         # `edges` now necessarily describes a non-forking directed graph (otherwise would have exited from the `return` clause)
     *         # It might however still have cycles, to quell failure from cycles we need temp variables available
     *         # Hence, finally, we check to see if our strings have exhausted the lexicon or not, if not then we can quell cycles
     *         # In our case, the lexicon is the english alphabet which has cardinality 26
     *         return len(set(str1)) < 26 or len(set(str2)) < 26
     */
}

/**
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero
 * or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 *
 *
 * Example 1:
 *
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * Example 2:
 *
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 *
 * Note:
 *
 * 1 <= str1.length == str2.length <= 10^4
 * Both str1 and str2 contain only lowercase English letters.
 *
 */
