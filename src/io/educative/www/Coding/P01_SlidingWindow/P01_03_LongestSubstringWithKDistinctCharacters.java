package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_03_LongestSubstringWithKDistinctCharacters {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N) where ‘N’ is the number of characters in the input string.
     * The outer for loop runs for all characters and the inner while loop processes each character only once,
     * therefore the time complexity of the algorithm will be O(N+N) which is asymptotically equivalent to O(N).
     *
     * Space Complexity
     * The space complexity of the algorithm is O(K), as we will be storing a maximum of ‘K+1’ characters in the HashMap.
     *
     * @param str
     * @param k
     * @return
     */
    public static int findLength(String str, int k) {
        if (str == null || str.length() == 0 || str.length() < k)
            throw new IllegalArgumentException();

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        // in the following loop we'll try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            charFrequencyMap.put(rightChar, charFrequencyMap.getOrDefault(rightChar, 0) + 1);
            // shrink the sliding window, until we are left with 'k' distinct characters in the frequency map
            while (charFrequencyMap.size() > k) {
                char leftChar = str.charAt(windowStart);
                charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) - 1);
                if (charFrequencyMap.get(leftChar) == 0) {
                    charFrequencyMap.remove(leftChar);
                }
                windowStart++; // shrink the window
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + P01_03_LongestSubstringWithKDistinctCharacters.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + P01_03_LongestSubstringWithKDistinctCharacters.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + P01_03_LongestSubstringWithKDistinctCharacters.findLength("cbbebi", 3));
    }
}