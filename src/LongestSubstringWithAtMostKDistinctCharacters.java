import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
    private Map<Character, Integer> map = new HashMap<>();
    private int counter = 0;

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int len = s.length();
        if (len == 0 || k == 0) {
            return 0;
        } else {
            int left = 0, right = 0, maxLen = 0;
            while (right < len) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    maxLen = updateMaxLen(maxLen);
                } else { // map doesn't contain c
                    if (counter < k) {
                        map.put(c, 1);
                        counter++;
                    } else {
                        while (left <= right && counter == k) {
                            char leftChar = s.charAt(left);
                            int leftCharCounter = map.get(leftChar);
                            map.put(leftChar, leftCharCounter - 1);
                            if (leftCharCounter == 1) { // pass all left char
                                counter--;
                            }
                            left++;
                        }

                        map.put(c, 1);
                        counter++;
                    }
                }

                right++;
            }

            maxLen = updateMaxLen(maxLen);

            return maxLen;
        }
    }

    private int updateMaxLen(int maxLen) {
        int tmpLen = getLen();
        if (tmpLen > maxLen) {
            maxLen = tmpLen;
        }
        return maxLen;
    }

    private int getLen() {
        int len = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                len += val;
            }
        }

        return len;
    }
}
