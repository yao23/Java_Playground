/**
 * Created by liyao on 7/3/17.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) { // beats 63.33%
        if (map.containsKey(s)) { // hit the cache
            return map.get(s);
        } else { // miss the cache
            LinkedList<String>res = new LinkedList<>();
            if (s.length() == 0) {
                res.add("");
                return res;
            } else {
                for (String word : wordDict) {
                    if (s.startsWith(word)) {
                        List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                        for (String sub : sublist) {
                            res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                        }
                    }
                }
                map.put(s, res);
                return res;
            }
        }
    }

    public List<String> wordBreakV0(String s, List<String> wordDict) { // beats 3.36%
        List<String> res = new ArrayList<>();
        if (s == null || wordDict.size() <= 0) {
            return res;
        } else {
            int length = s.length();
            // seg(i, j) means substring t start from i and length is j can be segmented into dictionary words
            boolean[][] seg = new boolean[length][length + 1];
            for (int len = 1; len <= length; len++) {
                for (int i = 0; i < length - len + 1; i++) {
                    String t = s.substring(i, i + len);
                    if (wordDict.contains(t)) {
                        seg[i][len] = true;
                        continue;
                    }
                    for (int k = 1; k < len; k++) {
                        if (seg[i][k]  && seg[i + k][len - k]) {
                            seg[i][len] = true;
                            break;
                        }
                    }
                }
            }
            if (!seg[0][length]) {
                return res;
            } else {
                int depth = 0;
                dfs(s, seg, 0, length, depth, res, new StringBuilder(), wordDict);
                return res;    
            }
        }
    }

    private void dfs(String s, boolean[][] seg, int start, int length, int depth, List<String> res, StringBuilder sb, List<String> dict) {
        if (depth == length) {
            String t = sb.toString();
            res.add(t.substring(0, t.length() - 1)); // remove last whitespace
            return;
        } else {
            for (int len = 1; len <= length; len++) {
                if (seg[start][len]) {
                    String t = s.substring(start, start + len);
                    if (!dict.contains(t)) {
                        continue;   
                    } else {
                        int BeforeAddLen = sb.length();
                        sb.append(t).append(" ");
                        dfs(s, seg, start + len, length, start + len, res, sb, dict);
                        sb.delete(BeforeAddLen, sb.length());   
                    }
                }
            }   
        }
    }

    // "catsanddog",["cat","cats","and","sand","dog"] => ["cat sand dog","cats and dog"]

    // beats 3.36%
}
