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

            int result = 0;
            boolean flag = false; // flag indicates whether or not have a char only appears once
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                int counter = entry.getValue();
                if (counter % 2 == 0) {
                    result += counter;
                } else {
                    if (counter == 1) {
                        if (!flag) {
                            flag = true;
                            result += 1;
                        }
                    } else {
                        if (map.size() == 1) {
                            result += counter;
                        } else {
                            result += (counter - 1);
                        }
                    }
                }
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
}
