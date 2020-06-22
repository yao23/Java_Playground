package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters { // LC 340 (Facebook)
    private static Map<Character, Integer> map = new HashMap<>();
    private static int counter = 0;

    public static int lengthOfLongestSubstringKDistinct(String s, int k) { // beats 55.80%
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)] == 0) {
                num++;
            }
            count[s.charAt(j)] += 1;
            if (num > k) {
                count[s.charAt(i)] -= 1;
                while (count[s.charAt(i)] > 0) {
                    i++;
                    count[s.charAt(i)] -= 1;
                }
                i++;
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static int lengthOfLongestSubstringKDistinctV0(String s, int k) {
        int len = s.length();
        if (len == 0 || k == 0) {
            return 0;
        } else {
            int left = 0, right = 0, maxLen = 0;
            while (right < len) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    if (counter < k) {
                        map.put(c, map.get(c) + 1);
                    } else {
                        if (map.get(c) == 0) {
                            left = moveLeftPointer(left, right, k, s);

                            map.put(c, 1);
                            counter++;
                        } else {
                            map.put(c, map.get(c) + 1);
                        }
                    }
                } else { // map doesn't contain c
                    if (counter == k) {
                        left = moveLeftPointer(left, right, k, s);
                    }

                    map.put(c, 1);
                    counter++;
                }

                maxLen = updateMaxLen(maxLen);
                right++;
            }

            maxLen = updateMaxLen(maxLen);

            return maxLen;
        }
    }

    private static int moveLeftPointer(int left, int right, int k, String s) {
        while (left <= right && counter == k) {
            char leftChar = s.charAt(left);
            int leftCharCounter = map.get(leftChar);
            map.put(leftChar, leftCharCounter - 1);
            if (leftCharCounter == 1) { // pass all left char
                counter--;
            }
            left++;
        }

        return left;
    }

    private static int updateMaxLen(int maxLen) {
        int tmpLen = getLen();
        if (tmpLen > maxLen) {
            maxLen = tmpLen;
        }
        return maxLen;
    }

    private static int getLen() {
        int len = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val > 0) {
                len += val;
            }
        }

        return len;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstringKDistinctV0("aba", 1));
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
    }
    // "aba",1 => 1 ("a")
    // "eceba",2 => 3 ("ece")
    // "eceeeecba",2 => 7 ("eceeeec")
    // "abacd",3 => 4 ("abac")

    // beats 1.73%
}
