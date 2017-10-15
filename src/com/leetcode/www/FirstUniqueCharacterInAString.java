package com.leetcode.www;

public class FirstUniqueCharacterInAString { // LC 387
    public int firstUniqChar(String s) { // beats 86.51%
        if (s == null || s.length() == 0) {
            return -1;
        }
        int len = s.length();
        if (len == 1) {
            return 0;
        }
        char[] cc = s.toCharArray();
        int slow = 0, fast = 1;
        int[] count = new int[256];
        count[cc[slow]]++;
        while (fast < len) {
            count[cc[fast]]++;
            // if slow pointer is not a unique character anymore, move to the next unique one
            while (slow < len && count[cc[slow]] > 1) {
                slow++;
            }
            if (slow >= len) {
                return -1; // no unique character exist
            }
            if (count[cc[slow]] == 0) { // not yet visited by the fast pointer
                count[cc[slow]]++;
                fast = slow; // reset the fast pointer
            }
            fast++;
        }
        return slow;
    }

    public int firstUniqCharV0(String s) { // beats 60.01%
        int freq [] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
