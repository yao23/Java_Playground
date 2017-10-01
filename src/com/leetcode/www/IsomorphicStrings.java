package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsomorphicStrings { // LC 205
    public boolean isIsomorphic(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        if (lenS != lenT) {
            return false;
        }

        Map<Integer, List<Integer>> mapS = new HashMap<>();
        Map<Integer, List<Integer>> mapT = new HashMap<>();
        for (int i = 0; i < lenS; i++) {
            addChar(s.charAt(i), i, mapS);
            addChar(t.charAt(i), i, mapT);
        }

        return areIsomorphic(mapS, mapT);
    }

    private void addChar(char c, int charIdx, Map<Integer, List<Integer>> map) {
        int ch = c - 'a';
        if (map.containsKey(ch)) {
            map.get(ch).add(charIdx);
        } else {
            List<Integer> idxList = new ArrayList<>();
            idxList.add(charIdx);
            map.put(ch, idxList);
        }
    }

    private boolean areIsomorphic(Map<Integer, List<Integer>> mapS, Map<Integer, List<Integer>> mapT) {
        return mapS.equals(mapT);
    }
}
