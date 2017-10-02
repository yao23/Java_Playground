package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings { // LC 205
    public boolean isIsomorphic(String s, String t) { // beats 98.86%
        char[] sCharArr = s.toCharArray();
        char[] tCharArr = t.toCharArray();

        int length = sCharArr.length;
        if (length != tCharArr.length) {
            return false;
        }

        char[] sm = new char[256];
        char[] tm = new char[256];

        for (int i = 0; i < length; i++) {
            char sc = sCharArr[i];
            char tc = tCharArr[i];
            if (sm[sc] == 0 && tm[tc] == 0) {
                sm[sc] = tc;
                tm[tc] = sc;
            } else {
                if (sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isIsomorphicV1(String s, String t) { // beats 87.07%
        int lenS = s.length(), lenT = t.length();
        if (lenS != lenT) {
            return false;
        }

        int[] m1 = new int[256];
        int[] m2 = new int[256];
        for (int i = 0; i < lenS; ++i) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) {
                return false;
            }
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }

    public boolean isIsomorphicV0(String s, String t) {
        Map m = new HashMap(); // Character as key in map for s, String as key in map for t
        for (Integer i = 0; i < s.length(); ++i) {
            if (m.put(s.charAt(i), i) != m.put(t.charAt(i) + "", i)) {
                return false;
            }
        }
        return true;
    }
}
