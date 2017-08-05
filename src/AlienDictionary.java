import java.util.*;

public class AlienDictionary { // LC 269
    public String alienOrder(String[] words) {
        int len = words.length;
        if (len == 0) {
            return "";
        } else if (len == 1) {
            return words[0];
        } else { // topological sort
            Map<Character, List<Character>> map = new HashMap<>();
            for (int i = 0; i < len - 1; i++) {
                String s1 = words[i], s2 = words[i + 1];
                int p = 0;
                while (p < s1.length() && p < s2.length() && s1.charAt(p) == s2.charAt(p)) {
                    p++;
                }
                char c1, c2;
                if (p < s1.length() && p < s2.length()) {
                    c1 = s1.charAt(p);
                    c2 = s2.charAt(p);
                } else {
                    c1 = s1.charAt(0);
                    c2 = s2.charAt(0);
                }
                if (!map.containsKey(c1)) {
                    map.put(c1, new ArrayList<>());
                }
                map.get(c1).add(c2);
            }

            if (map.size() == 1) { // test case 4
                Character firstKey = map.keySet().iterator().next();
                return (""+firstKey);
            }

            int[] graph = new int[26];
            Arrays.fill(graph, -1); // easy for graph not full (test case 1)
            for (Map.Entry<Character, List<Character>> entry : map.entrySet()) {
                char k = entry.getKey();
                if (graph[k - 'a'] == -1) { // update for topological sort
                    graph[k - 'a'] = 0;
                }
                List<Character> list = entry.getValue();
                for (Character c : list) {
                    if (graph[c - 'a'] == -1) {
                        graph[c - 'a'] = 0;
                    }
                    graph[c - 'a']++;
                }
            }

            Deque<Character> q = new ArrayDeque<>();
            String res = "";
            for (int i = 0; i < 26; i++) {
                if (graph[i] == 0) { // in-degree is zero
                    q.offer((char)('a' + i));
                    res += ((char)('a' + i));
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

            return res;
        }
    }
}

// ["wrt","wrf","er","ett","rftt"] => "wertf"
// ["z","x"] => "zx"
// ["z","x","z"] => ""
// ["z","z"] => "z"

