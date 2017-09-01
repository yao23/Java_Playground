package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords { // LC 30
    public List<Integer> findSubstring(String s, String[] words) { // beats 37.46%
        List<Integer> res = new ArrayList<>();
        if (s == null || words == null || words.length == 0) {
            return res;
        }
        int len = words[0].length(); // length of each word

        Map<String, Integer> map = new HashMap<>(); // map for words
        for (String w : words) {
            map.put(w, map.containsKey(w) ? map.get(w) + 1 : 1);
        }

        int wordLen = words.length;
        for (int i = 0; i <= s.length() - len * wordLen; i++) {
            Map<String, Integer> copy = new HashMap<>(map);
            for (int j = 0; j < wordLen; j++) { // checkc if match
                String str = s.substring(i + j * len, i + j * len + len); // next word
                if (copy.containsKey(str)) { // str is in remaining words
                    int count = copy.get(str);
                    if (count == 1) {
                        copy.remove(str);
                    } else {
                        copy.put(str, count - 1);
                    }
                    if (copy.isEmpty()) { // matches
                        res.add(i);
                        break;
                    }
                } else {
                    break; // not in words
                }
            }
        }
        return res;
    }
}
