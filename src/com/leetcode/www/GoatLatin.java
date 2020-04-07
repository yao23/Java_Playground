import java.util.*;
import java.util.stream.Collectors;

public class GoatLatin {
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
