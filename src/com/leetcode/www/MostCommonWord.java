package com.leetcode.www;

import java.util.*;

public class MostCommonWord {
    /**
     * Runtime: 21 ms, faster than 38.63% of Java online submissions for Most Common Word.
     * Memory Usage: 36.6 MB, less than 95.93% of Java online submissions for Most Common Word.
     *
     * https://leetcode.com/problems/most-common-word/discuss/296700/Java-Solution-using-Map
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        // https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
        // \\W+ - several non-word characters [^\w+] like punctuation symbols !?',;.
        String[] strs = paragraph.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String str : strs) {
            if (!ban.contains(str)) {
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
        }
        int max = 0;
        String res = "";
        for (String str : map.keySet()) {
            if (map.get(str) > max) {
                max = map.get(str);
                res = str;
            }
        }
        return res;
    }
}
