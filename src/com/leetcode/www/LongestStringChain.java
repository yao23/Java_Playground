import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain { // 1048 [Google]
    /**
     * DP HashMap Solution
     *
     * @param words
     * @return
     */
    public int longestStrChain(String[] words) {
        int max = 1;
        Map<String, Integer> map = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());

        for(int i=0; i<words.length; i++) {
            map.put(words[i], 1);
        }

        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                String small = words[i];
                String big = words[j];
                if(big.length() - small.length() == 0) continue;
                if(big.length() - small.length() > 1) break;
                if(!oneCharDiff(small, big)) continue;

                map.put(words[j], Math.max(map.get(big), map.get(small)+1));
                max = Math.max(max, map.get(big));
            }
        }

        return max;
    }

    private boolean oneCharDiff(String small, String big) {
        for(int i=0; i<small.length(); i++) {
            if(small.charAt(i) != big.charAt(i)) {
                return small.substring(i).equals(big.substring(i+1));
            }
        }

        return true;
    }


    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> memo = new HashMap<>();

    /**
     * Firstly, we add all the words to Hashmap for quick checking.
     * Then we start from a word and start deleting the letters to form a word with 1 less character and continue our
     * recursion if we find the new word in our hashmap.
     *
     * @param words
     * @return
     */
    public int longestStrChainV1(String[] words) {
        for (String word : words) {
            map.put(word, 1);
        }

        int res = Integer.MIN_VALUE;
        for (String word : words) {
            res = Math.max(res, 1 + dfs(word));
        }
        return res;
    }

    public int dfs(String sb) {
        if (sb.length() == 0) {
            return 0;
        }
        if (memo.containsKey(sb)) return memo.get(sb);
        int max = 0;
        for (int i = 0; i < sb.length(); i++) {
            StringBuilder tmp = new StringBuilder(sb);
            tmp.deleteCharAt(i);
            int res = 0;
            if (map.containsKey(tmp.toString())) {
                res += 1 + dfs(tmp.toString());
            }
            max = Math.max(max, res);
        }
        memo.put(sb, max);
        return max;
    }
}

/**
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it
 * equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2,
 * word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 *
 *
 * Note:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 */
