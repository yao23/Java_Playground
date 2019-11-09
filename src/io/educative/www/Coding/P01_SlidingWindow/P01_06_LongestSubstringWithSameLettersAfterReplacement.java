package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_06_LongestSubstringWithSameLettersAfterReplacement {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N) where ‘N’ is the number of letters in the input string.
     *
     * Space Complexity
     * As we are expecting only the lower case letters in the input string, we can conclude that the space complexity
     * will be O(26), to store each letter’s frequency in the HashMap, which is asymptotically equal to O(1).
     *
     * @param str
     * @param k
     * @return
     */
    public static int findLength(String str, int k) {
        int windowStart = 0, maxLength = 0, maxRepeatLetterCount = 0;
        Map<Character, Integer> letterFrequencyMap = new HashMap<>();
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            letterFrequencyMap.put(rightChar, letterFrequencyMap.getOrDefault(rightChar, 0) + 1);
            maxRepeatLetterCount = Math.max(maxRepeatLetterCount, letterFrequencyMap.get(rightChar));

            // current window size is from windowStart to windowEnd, overall we have a letter which is
            // repeating 'maxRepeatLetterCount' times, this means we can have a window which has one letter 
            // repeating 'maxRepeatLetterCount' times and the remaining letters we should replace.
            // if the remaining letters are more than 'k', it is the time to shrink the window as we
            // are not allowed to replace more than 'k' letters
            if (windowEnd - windowStart + 1 - maxRepeatLetterCount > k) {
                char leftChar = str.charAt(windowStart);
                letterFrequencyMap.put(leftChar, letterFrequencyMap.get(leftChar) - 1);
                windowStart++;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(P01_06_LongestSubstringWithSameLettersAfterReplacement.findLength("aabccbb", 2));
        System.out.println(P01_06_LongestSubstringWithSameLettersAfterReplacement.findLength("abbcb", 1));
        System.out.println(P01_06_LongestSubstringWithSameLettersAfterReplacement.findLength("abccde", 1));
    }
}