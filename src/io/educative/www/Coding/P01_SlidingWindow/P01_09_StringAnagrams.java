package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_09_StringAnagrams {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N + M) where ‘N’ and ‘M’ are the number of characters
     * in the input string and the pattern respectively.
     *
     * Space Complexity
     * The space complexity of the algorithm is O(M) since in the worst case, the whole pattern can have distinct
     * characters which will go into the HashMap. In the worst case, we also need O(N) space for the result list,
     * this will happen when the pattern has only one character and the string contains only that character.
     *
     * @param str
     * @param pattern
     * @return
     */
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        List<Integer> resultIndices = new ArrayList<Integer>();
        // our goal is to match all the characters from the map with the current window
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            // decrement the frequency of the matched character
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0)
                    matched++;
            }

            if (matched == charFrequencyMap.size()) // have we found an anagram?
                resultIndices.add(windowStart);

            if (windowEnd >= pattern.length() - 1) { // shrink the window
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        System.out.println(P01_09_StringAnagrams.findStringAnagrams("ppqp", "pq"));
        System.out.println(P01_09_StringAnagrams.findStringAnagrams("abbcabc", "abc"));
    }
}