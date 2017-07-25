public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        } else {
            int[] map = new int[26];
            String res = "";
            for (int curIdx = 0; curIdx < len; curIdx++) {
                char c = s.charAt(curIdx);
                int oldIdx = map[c - 'a'];
                if (oldIdx == 0) {
                    res += c;
                    map[c - 'a'] = res.length();
                } else { // duplicate letter
                    res = removeDuplicate(curIdx, oldIdx, map, c, res);
                }
            }

            return res;
        }
    }

    private String removeDuplicate(int curIdx, int oldIdx, int[] map, char c, String res) {
        int len = res.length();
        String tmp = "";
        if (oldIdx < len - 1 && res.charAt(oldIdx) > res.charAt(oldIdx + 1)) { // duplicate larger than its latter element
            if (oldIdx == 0) { // duplicate at first index
                if (len == 1) {
                    return res;
                } else {
                    map[c - 'a'] = len + 1; // update map to use cur char
                    return (res.substring(1) + c);
                }
            } else { // dupicate at later index
                map[c - 'a'] = len + 1;
                return (res.substring(0, oldIdx) + res.substring(oldIdx + 1, len) + c);
            }
        } else {
            return res;
        }
    }

    private String processDuplicates(int curIdx, int oldIdx, String res, String s) {
        int leftLen = res.length() - 1 - oldIdx;
        int rightLen = s.length() - curIdx;
        if (leftLen <= rightLen) {
            int leftStart = oldIdx;
            int leftEnd = res.length() - 2; // last char as middle ("a" in "fbdabf")
            int rightStart = curIdx;
            int rightEnd = curIdx + leftLen - 1;
            return findLongestSimilarStr(leftStart, leftEnd, rightStart, rightEnd, res, s);
        } else {
            int leftStart = oldIdx;
            int leftEnd = oldIdx + rightLen - 1;
            int rightStart = curIdx;
            int rightEnd = s.length() - 1;
            return findLongestSimilarStr(leftStart, leftEnd, rightStart, rightEnd, res, s);
        }
    }

    private String findLongestSimilarStr(int leftStart, int leftEnd, int rightStart, int rightEnd, String left, String right) {
        char c = left.charAt(leftEnd + 1);
        String leftRes = "";
        String rightRes = "";
        int offset = findLongestSimilarStrOffset(left.substring(leftStart, leftEnd + 1), right.substring(rightStart, rightEnd + 1));
        for (int i = 0; i < offset; i++) {
            char cur = left.charAt(leftStart + i);
            if (cur > c) {
                rightRes += cur; // remove from left side
            } else {
                leftRes += cur; // remove from right side
            }
        }

        return (leftRes + c + rightRes);
    }

    private int findLongestSimilarStrOffset(String left, String right) {
        for (int i = left.length() - 1; i >= 0; i++) {
            String sortedLeft = "";
            String sortedRight = "";
            if (sortedLeft.equals(sortedRight)) {
                return i;
            }
        }

        return 0;
    }
}
