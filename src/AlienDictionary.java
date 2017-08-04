import java.util.*;

public class AlienDictionary {
    public String alienOrder(String[] words) {
        int len = words.length;
        if (len == 0) {
            return "";
        } else if (len == 1) {
            return words[0];
        } else {
            Map<Character, List<Character>> map = new HashMap<>();
            for (int i = 0; i < len - 1; i++) {
                String s1 = words[i], s2 = words[i + 1];
                int p = 0;
                while (p < s1.length() && p < s2.length() && s1.charAt(p) == s2.charAt(p)) {
                    p++;
                }
                if (p < s1.length() && p < s2.length()) {
                    char c1 = s1.charAt(p), c2 = s2.charAt(p);
                    if (!map.containsKey(c1)) {
                        map.put(c1, new ArrayList<Character>());
                    }
                    map.get(c1).add(c2);
                }
            }

            int[] graph = new int[26];
            for (Map.Entry<Character, List<Character>> entry : map.entrySet()) {
                List<Character> list = entry.getValue();
                for (Character c : list) {
                    graph[c - 'a']++;
                }
            }

            Deque<Character> q = new ArrayDeque<>();
            String res = "";
            for (int i = 0; i < 26; i++) {
                if (graph[i] == 0) {
                    q.offer((char)('a' + i));
                    res += ((char)('a' + i));
                }
            }
            while (!q.isEmpty()) {
                char cur = q.remove();
                List<Character> neighbors = map.get(cur);
                for (Character c : neighbors) {
                    graph[c - 'a']--;
                    if (graph[c - 'a'] == 0) {
                        q.offer(c);
                        res += c;
                    }
                }
            }

            return res;
        }
    }
}
