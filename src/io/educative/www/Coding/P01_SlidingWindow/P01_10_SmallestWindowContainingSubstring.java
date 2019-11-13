package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_10_SmallestWindowContainingSubstring {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N + M) where ‘N’ and ‘M’ are the number of characters
     * in the input string and the pattern respectively.
     *
     * Space Complexity
     * The space complexity of the algorithm is O(M) since in the worst case, the whole pattern can have distinct
     * characters which will go into the HashMap. In the worst case, we also need O(N) space for the resulting substring,
     * which will happen when the input string is a permutation of the pattern.
     *
     * @param str
     * @param pattern
     * @return
     */
    public static String findSubstring(String str, String pattern) {
        int windowStart = 0, matched = 0, minLength = str.length() + 1, subStrStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) >= 0) // count every matching of a character
                    matched++;
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length()) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }

                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--;
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }

    public static void main(String[] args) {
        System.out.println(P01_10_SmallestWindowContainingSubstring.findSubstring("aabdec", "abc"));
        System.out.println(P01_10_SmallestWindowContainingSubstring.findSubstring("abdabca", "abc"));
        System.out.println(P01_10_SmallestWindowContainingSubstring.findSubstring("adcad", "abc"));
    }
}