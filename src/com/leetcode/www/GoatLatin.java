import java.util.*;
import java.util.stream.Collectors;

public class GoatLatin { // LC 824 (Facebook)
    /**
     * Runtime: 4 ms, faster than 49.96% of Java online submissions for Goat Latin.
     * Memory Usage: 39.5 MB, less than 6.67% of Java online submissions for Goat Latin.
     *
     * Time Complexity: O(N^2), where N is the length of S. This represents the complexity of rotating the word and
     * adding extra "a" characters.
     *
     * Space Complexity: O(N^2), the space added to the answer by adding extra "a" characters.
     *
     * @param S
     * @return
     */
    public String toGoatLatinV1(String S) {
        Set<Character> vowel = new HashSet<>();
        for (char c: new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'})
            vowel.add(c);

        int t = 1;
        StringBuilder ans = new StringBuilder();
        for (String word: S.split(" ")) {
            char first = word.charAt(0);
            if (vowel.contains(first)) {
                ans.append(word);
            } else {
                ans.append(word.substring(1));
                ans.append(word.substring(0, 1));
            }
            ans.append("ma");
            for (int i = 0; i < t; i++)
                ans.append("a");
            t++;
            ans.append(" ");
        }

        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }

    private static char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};

    /**
     * Runtime: 21 ms, faster than 6.15% of Java online submissions for Goat Latin.
     * Memory Usage: 41.1 MB, less than 6.67% of Java online submissions for Goat Latin.
     *
     * @param S
     * @return
     */
    public String toGoatLatin(String S) {
        int len = S.length();
        if (S == null || len == 0) {
            return "";
        } else {
            char[] ch = S.toCharArray();
            StringBuilder sb = new StringBuilder();
            List<Character> str = new ArrayList<>();
            int wordNum = 0;
            for (int i = 0; i < len; i++) {
                char cur = ch[i];
                if (cur == ' ') {
                    if (str.isEmpty()) {
                        continue;
                    } else {
                        wordNum++;
                        String tmp = getString(str, wordNum);
                        sb.append(tmp);
                        sb.append(' ');
                        str.clear();
                    }
                } else {
                    str.add(cur);
                }
            }
            if (!str.isEmpty()) {
                wordNum++;
                String tmp = getString(str, wordNum);
                sb.append(tmp);
            }
            return sb.toString();
        }
    }

    private String getString(List<Character> str, int wordNum) {
        Character first = str.get(0);
        if (!startWithVowel(first)) {
            str.remove(0);
            str.add(first);
        }
        str.add('m');
        for (int j = 0; j <= wordNum; j++) {
            str.add('a');
        }
        return  str.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private boolean startWithVowel(char c) {
        for (char vowel : vowels) {
            if (c == vowel) {
                return true;
            }
        }
        return false;
    }
}
