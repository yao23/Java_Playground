/**
 * Created by liyao on 7/3/17.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return check(s, wordDict, new HashMap<String, Boolean>());
    }

    private boolean check(String s, List<String> wordDict, Map<String, Boolean> map) { // Time Limit Exceeded for test case 5
        if (s.equals("")) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                String leftStr = s.substring(0, i + 1);
                if (wordDict.contains(leftStr)) {
                    map.put(leftStr, true);
                    String rightStr = s.substring(i + 1);
                    if (map.containsKey(rightStr)) { // hit the cache
                        if (map.get(rightStr)) {
                            return true;
                        } else {
                            continue;
                        }
                    } else { // miss the cache
                        if (check(rightStr, wordDict, map)) {
                            return true;
                        } else {
                            continue;
                        }
                    }
                } else {
                    continue;
                }
            }

            return false;
        }
    }

    private boolean checkV0(String s, List<String> wordDict) { // Time Limit Exceeded for test case 5
        if (s.equals("")) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (wordDict.contains(s.substring(0, i + 1))) {
                    if (checkV0(s.substring(i + 1), wordDict)) {
                        return true;
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }

            return false;
        }
    }

    // "", ["leet", "code"] => true
    // "leetcode", [] => false
    // "leetcode",["leet", "code"] => true
    // "aaaaaaaa", ["a", "aa"] => true
    // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"] => true
}
