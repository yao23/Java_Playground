package io.educative.www.Coding.P01_SlidingWindow;

import java.util.*;

class P01_11_WordsConcatenation {
    /**
     * Time Complexity
     * The time complexity of the above algorithm will be O(N * M * Len) where ‘N’ is the number of characters
     * in the given string, ‘M’ is the total number of words, and ‘Len’ is the length of a word.
     *
     * Space Complexity
     * The space complexity of the algorithm is O(M) since at most, we will be storing all the words in the two HashMaps.
     * In the worst case, we also need O(N) space for the resulting list. So, the overall space complexity of the
     * algorithm will be O(M+N).
     *
     * @param str
     * @param words
     * @return
     */
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words)
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);

        List<Integer> resultIndices = new ArrayList<Integer>();
        int wordsCount = words.length, wordLength = words[0].length();

        for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordsCount; j++) {
                int nextWordIndex = i + j * wordLength;
                // get the next word from the string
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if (!wordFrequencyMap.containsKey(word)) // break if we don't need this word
                    break;

                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1); // add the word to the 'wordsSeen' map

                // no need to process further if the word has higher frequency than required
                if (wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0))
                    break;

                if (j + 1 == wordsCount) // store index if we have found all the words
                    resultIndices.add(i);
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        List<Integer> result = P01_11_WordsConcatenation.findWordConcatenation("catfoxcat", new String[] { "cat", "fox" });
        System.out.println(result);
        result = P01_11_WordsConcatenation.findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" });
        System.out.println(result);
    }
}