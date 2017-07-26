import java.util.Arrays;

public class RemoveDuplicateLetters {
    private String res = "";

    public String removeDuplicateLetters(String s) {
        int len = s.length();
        if (len <= 1) {
            return s;
        } else {
            int[] map = new int[26];

            for (int curIdx = 0; curIdx < len; curIdx++) {
                char c = s.charAt(curIdx);
                int oldIdx = map[c - 'a'];
                if (oldIdx == 0) {
                    res += c;
                    map[c - 'a'] = res.length();
                } else { // duplicate letter
                    curIdx += processDuplicates(curIdx, oldIdx, s);
                }
            }

            return res;
        }
    }

    private int processDuplicates(int curIdx, int oldIdx, String s) {
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

    private int findLongestSimilarStr(int leftStart, int leftEnd, int rightStart, int rightEnd, String left, String right) {
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

        res = (leftRes + c + rightRes);

        return offset;
    }

    private int findLongestSimilarStrOffset(String left, String right) {
        for (int i = left.length() - 1; i >= 0; i++) {
            String sortedLeft = sortString(left);
            String sortedRight = sortString(right);
            if (sortedLeft.equals(sortedRight)) {
                return i;
            }
        }

        return 0;
    }

    private String sortString(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        String sorted = String.valueOf(arr);
        return sorted;
    }
}
