package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_08_PermutationInAString {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N + M) where ‘N’ and ‘M’ are the number of characters
     * in the input string and the pattern respectively.
     *
     * Space Complexity
     * The space complexity of the algorithm is O(M) since in the worst case, the whole pattern can have distinct
     * characters which will go into the HashMap.
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Permutation exist: " + P01_08_PermutationInAString.findPermutation("oidbcaf", "abc"));
        System.out.println("Permutation exist: " + P01_08_PermutationInAString.findPermutation("odicf", "dc"));
        System.out.println("Permutation exist: " + P01_08_PermutationInAString.findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: " + P01_08_PermutationInAString.findPermutation("aaacb", "abc"));
    }
}