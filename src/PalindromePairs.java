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
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            map.put(words[i], i);
        }

        for (int j = 0; j < words.length; j++) {
            String word = words[j];System.out.println("word: " + word);
            for (int i = 0; i <= word.length(); i++) {
                String left = (i == 0) ? "" : word.substring(0,i);
                String right = (i == word.length()) ? "" : word.substring(i);
                System.out.println(i + ": " + left + ", " + right);
                if (isPalindrome(right)) { // left part reverse append to end of right part
                    String leftReverse = new StringBuilder(left).reverse().toString();
                    System.out.println("left verse: " + leftReverse);
                    if (map.containsKey(leftReverse) && j != map.get(leftReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(j);
                        tuple.add(map.get(leftReverse));
                        System.out.println("tuple: " + j + ", " + map.get(leftReverse));
                        if (set.add(tuple)) {
                            result.add(tuple);
                        }
                    }
                }

                if (isPalindrome(left)) { // right part reverse add to beginning of left part
                    String rightReverse = new StringBuilder(right).reverse().toString();
                    System.out.println("right verse: " + rightReverse);
                    if (map.containsKey(rightReverse) && j != map.get(rightReverse)) {
                        List<Integer> tuple = new ArrayList<>();
                        tuple.add(map.get(rightReverse));
                        tuple.add(j);
                        System.out.println("tuple: " + map.get(rightReverse) + ", " + j);
                        if (set.add(tuple)) {
                            result.add(tuple);
                        }
                    }
                }
            }
        }

        return result;
    }
}
