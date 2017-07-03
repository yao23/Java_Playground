/**
 * Created by liyao on 7/3/17.
 */
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return check(s, wordDict);
    }

    private boolean check(String s, List<String> wordDict) {
        if (s.equals("")) {
            return true;
        } else {
            boolean[] result = new boolean[s.length() + 1];
            Arrays.fill(result, false);
            result[0] = true;

            // result means segmentation from 0 and length is i can be segmented into dictionary words
            for (int len = 1; len <= s.length(); len++) {
                for (int i = 0; i < len; i++) {
                    String rightStr = s.substring(i, len);
                    if (result[i] && wordDict.contains(rightStr)) {
                        result[len] = true;
                        break;
                    }
                }
            }

            return result[s.length()];
        }
    }

    private boolean checkV1(String s, List<String> wordDict, Map<String, Boolean> map) { // Time Limit Exceeded for test case 5
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
                        if (checkV1(rightStr, wordDict, map)) {
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

    // beats 65.93%
}
