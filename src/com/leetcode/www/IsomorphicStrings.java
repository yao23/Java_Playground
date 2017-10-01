package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsomorphicStrings { // LC 205
    public boolean isIsomorphic(String s, String t) { // beats 87.07%
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
}
