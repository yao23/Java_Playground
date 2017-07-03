/**
 * Created by liyao on 7/3/17.
 */
import java.util.List;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return check(s, wordDict);
    }

    private boolean check(String s, List<String> wordDict) { // Time Limit Exceeded for test case 5
        if (s.equals("")) {
            return true;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (wordDict.contains(s.substring(0, i + 1))) {
                    if (check(s.substring(i + 1), wordDict)) {
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
