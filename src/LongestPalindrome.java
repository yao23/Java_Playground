/**
 * Created by liyao on 7/10/17.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    public int longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return len;
        } else {
            Map<Character,Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                Character cur = s.charAt(i);
                if (map.containsKey(cur)) {
                    map.put(cur, map.get(cur) + 1);
                } else {
                    map.put(cur, 1);
                }
            }

            int result = 0, numOdd = 0;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                int counter = entry.getValue();
                if (counter % 2 == 1) {
                    numOdd++;
                }
                result += counter;
            }
            if (numOdd > 0) {
                result -= (numOdd - 1);
            }

            return result;
        }
    }

    // "" => 0
    // "a" => 1
    // "aa" => 2
    // "ab" => 1
    // "ccc" => 3
    // "abbc" => 3
    // "aaccccdd" => 8
    // "abccccdd" => 7
    // "ababababa" => 9
    // "zeusnilemacaronimaisanitratetartinasiaminoracamelinsuez" => 55

    // beats 10.88%
}
