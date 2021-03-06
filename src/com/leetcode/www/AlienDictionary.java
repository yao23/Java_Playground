package com.leetcode.www;

import java.util.*;

public class AlienDictionary { // LC 269 (Facebook)
    /**
     * Topological Sort
     *
     * Runtime: 13 ms, faster than 12.55% of Java online submissions for Alien Dictionary.
     * Memory Usage: 39.2 MB, less than 5.41% of Java online submissions for Alien Dictionary.
     *
     * beats 54.90%
     *
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        String result = "";

        if (words == null || words.length == 0) {
            return result;
        }
        for (String s: words) {
            for (char c: s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) { // construct graph
            String cur = words[i];
            String next = words[i + 1];
            int length = Math.min(cur.length(), next.length());
            boolean allSame = true;
            for (int j = 0; j < length; j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) {
                    Set<Character> set = new HashSet<>();
                    if (map.containsKey(c1)) {
                        set = map.get(c1);
                    }
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    allSame = false;
                    break;
                }
            }
            if (allSame && cur.length() > next.length()) { // handle invalid test case ["abc", "ab"], return ""
                return "";
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (char c: degree.keySet()) { // start from zero degree
            if (degree.get(c) == 0) {
                q.add(c);
            }
        }
        while (!q.isEmpty()) { // BFS
            char c = q.remove();
            result += c;
            if (map.containsKey(c)) {
                for (char c2: map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) {
                        q.add(c2);
                    }
                }
            }
        }
        if (result.length() != degree.size()) { // maybe less (island)
            return "";
        } else {
            return result;
        }
    }

    /**
     * beats 54.90%
     *
     * @param words
     * @return
     */
    public String alienOrderV0(String[] words) {
        int len = words.length;
        if (len == 0) {
            return "";
        } else if (len == 1) {
            return words[0];
        } else { // topological sort
            Map<Character, List<Character>> map = new HashMap<>();
            int[] graph = new int[26];
            Arrays.fill(graph, -1); // easy for graph not full (test case 1)
            for (int i = 0; i < len - 1; i++) {
                String s1 = words[i], s2 = words[i + 1];
                initGraph(graph, s1); // update for topological sort
                initGraph(graph, s2);

                int p = 0;
                while (p < s1.length() && p < s2.length() && s1.charAt(p) == s2.charAt(p)) {
                    p++;
                }

                if (p < s1.length() && p < s2.length()) {
                    char c1 = s1.charAt(p);
                    char c2 = s2.charAt(p);
                    if (!map.containsKey(c1)) {
                        map.put(c1, new ArrayList<>());
                    }
                    map.get(c1).add(c2);
                }
            }

            for (Map.Entry<Character, List<Character>> entry : map.entrySet()) {
                List<Character> list = entry.getValue();
                for (Character c : list) {
                    graph[c - 'a']++;
                }
            }

            Deque<Character> q = new ArrayDeque<>();
            String res = "";
            int numNode = 0;
            for (int i = 0; i < 26; i++) {
                if (graph[i] == 0) { // in-degree is zero
                    q.offer((char)('a' + i));
                    res += ((char)('a' + i));
                }
                if (graph[i] > -1) {
                    numNode++;
                }
            }
            while (!q.isEmpty()) {
                char cur = q.remove();
                List<Character> neighbors = map.get(cur);
                if (neighbors != null) {
                    for (Character c : neighbors) {
                        graph[c - 'a']--;
                        if (graph[c - 'a'] == 0) {
                            q.offer(c);
                            res += c;
                        }
                    }
                }
            }

            return res.length() == numNode ? res : "";
        }
    }

    private void initGraph(int[] graph, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (graph[c - 'a'] == -1) {
                graph[c - 'a'] = 0;
            }
        }
    }
}

// ["wrt","wrf","er","ett","rftt"] => "wertf"
// ["z","x"] => "zx"
// ["z","x","z"] => ""
// ["z","z"] => "z"
// ["zy","zx"] => "yxz" ("yzx" accepted)
// ["wrt","wrtkj"] => "jkrtw"

