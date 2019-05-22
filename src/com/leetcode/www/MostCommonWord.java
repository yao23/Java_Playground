package com.leetcode.www;

import java.util.*;

public class MostCommonWord { // LC 819
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
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> map = new HashMap<>();
        // https://www.vogella.com/tutorials/JavaRegularExpressions/article.html
        // \\W+ - several non-word characters [^\w+] like punctuation symbols !?',;.
        String[] strs = paragraph.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String str : strs) {
            if (!bannedSet.contains(str)) {
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

    /**
     * Runtime: 7 ms, faster than 82.36% of Java online submissions for Most Common Word.
     * Memory Usage: 35.2 MB, less than 98.46% of Java online submissions for Most Common Word.
     *
     * https://leetcode.com/problems/most-common-word/discuss/295701/Java-Simple-HashMap-Solution
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWordV1(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        int maxVal = 0;
        Map<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paragraph.length(); i++) {
            char c = paragraph.charAt(i);
            if (Character.isLetter(c)) {
                sb.append(c);
            }

            if (!Character.isLetter(c) || i == paragraph.length() - 1) {
                String temp = sb.toString().toLowerCase();
                sb.setLength(0);
                if (temp.length() > 0 && !bannedSet.contains(temp)) {
                    map.put(temp, map.getOrDefault(temp, 0) + 1);
                    maxVal = Math.max(maxVal, map.get(temp));
                }
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxVal) {
                return entry.getKey();
            }
        }

        return "";
    }

    /**
     * Runtime: 13 ms, faster than 71.81% of Java online submissions for Most Common Word.
     * Memory Usage: 35.8 MB, less than 97.81% of Java online submissions for Most Common Word.
     *
     * @param paragraph
     * @param banned
     * @return
     */
    public String mostCommonWordV0(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        // use "#" to replace all non alphabet characters
        String[] candidates = paragraph.replaceAll("[^a-zA-Z0-9]", "#").split("#");
        Map<String, Integer> map = new HashMap<>();
        String res = null;
        int maxFreq = 0;

        for (String can: candidates) {
            can = can.toLowerCase();
            if (bannedSet.contains(can) || can.isEmpty()) {
                continue;
            }
            map.put(can, map.getOrDefault(can, 0) + 1);
            if (map.get(can) > maxFreq) {
                maxFreq = map.get(can);
                res = can;
            }
        }

        return res;
    }
}
