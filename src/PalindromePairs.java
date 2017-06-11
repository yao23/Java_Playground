/**
 * Created by liyao on 6/10/17.
 */
import java.util.*;

public class PalindromePairs {
    private boolean isPalindrome(String str) {
        int len = str.length();
        if (len <= 1) {
            return true;
        } else {
            int l = 0, r = len - 1;
            char[] chars = str.toCharArray();

            while (l < r) {
                if (chars[l++] != chars[r--]) {
                    return false;
                }
            }

            return true;
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        int len = words.length;
        if (len == 0) {
            return result;
        }

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            map.put(words[i], i);
        }

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                // left part reverse append to end of right part
                String left = word.substring(0,i);
                String right = word.substring(i);
                if (isPalindrome(right)) {
                    String leftReverse = new StringBuilder(left).reverse().toString();
                    if (map.containsKey(leftReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(i);
                        tuple.add(map.get(leftReverse));
                        result.add(tuple);
                    }
                }

                // right part reverse add to beginning of left part
                if (isPalindrome(left)) {
                    String rightReverse = new StringBuilder(right).reverse().toString();
                    if (map.containsKey(rightReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(map.get(rightReverse));
                        tuple.add(i);
                        result.add(tuple);
                    }
                }
            }
        }

        return result;
    }
}
