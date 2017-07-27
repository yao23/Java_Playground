import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) { // beats 11.38%
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            if (--cnt[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    // better explanation: https://discuss.leetcode.com/topic/31413/easy-to-understand-iterative-java-solution
    public String removeDuplicateLettersV1(String s) {
        if (s == null || s.length() <= 1) return s;

        Map<Character, Integer> lastPosMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastPosMap.put(s.charAt(i), i);
        }

        char[] result = new char[lastPosMap.size()];
        int begin = 0, end = findMinLastPos(lastPosMap);

        for (int i = 0; i < result.length; i++) {
            char minChar = 'z' + 1;
            for (int k = begin; k <= end; k++) {
                if (lastPosMap.containsKey(s.charAt(k)) && s.charAt(k) < minChar) {
                    minChar = s.charAt(k);
                    begin = k+1;
                }
            }

            result[i] = minChar;
            if (i == result.length-1) break;

            lastPosMap.remove(minChar);
            if (s.charAt(end) == minChar) end = findMinLastPos(lastPosMap);
        }

        return new String(result);
    }

    private int findMinLastPos(Map<Character, Integer> lastPosMap) {
        if (lastPosMap == null || lastPosMap.isEmpty()) return -1;
        int minLastPos = Integer.MAX_VALUE;
        for (int lastPos : lastPosMap.values()) {
            minLastPos = Math.min(minLastPos, lastPos);
        }
        return minLastPos;
    }



    private String res = "";

    public String removeDuplicateLettersV0(String s) { // not working (test case 2)
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
                    curIdx += processDuplicates(curIdx, oldIdx - 1, s);
                }
            }

            return res;
        }
    }

    private int processDuplicates(int curIdx, int oldIdx, String s) {
        int leftLen = res.length() - 1 - oldIdx;
        int rightLen = s.length() - curIdx;
        System.out.println("processing: " + res + ", " + s + ", " + leftLen + ", " + rightLen);
        if (leftLen <= rightLen) {
            int leftStart = oldIdx;
            int leftEnd = res.length() - 2; // last char as middle ("a" in "fbdabf")
            int rightStart = curIdx;
            int rightEnd = curIdx + leftLen - 1;
            System.out.println("left longer: " + leftStart + ", " + leftEnd + ", " + rightStart + ", " + rightEnd);
            return findLongestSimilarStr(leftStart, leftEnd, rightStart, rightEnd, res, s);
        } else {
            int leftStart = oldIdx;
            int leftEnd = oldIdx + rightLen - 1;
            int rightStart = curIdx;
            int rightEnd = s.length() - 1;
            System.out.println("right longer: " + leftStart + ", " + leftEnd + ", " + rightStart + ", " + rightEnd);
            return findLongestSimilarStr(leftStart, leftEnd, rightStart, rightEnd, res, s);
        }
    }

    private int findLongestSimilarStr(int leftStart, int leftEnd, int rightStart, int rightEnd, String left, String right) {
        char c = left.charAt(leftEnd + 1);
        String leftRes = "";
        String rightRes = "";
        int offset = findLongestSimilarStrOffset(left.substring(leftStart, leftEnd + 1), right.substring(rightStart, rightEnd + 1));
        System.out.println("offset: " + offset + ", " + leftStart + ", " + leftEnd + ", " + rightStart + ", " + rightEnd);
        int rightOffset = 0;
        for (int i = 0; i <= offset; i++) {
            char cur = left.charAt(leftStart + i);
            if (cur > c) {
                rightRes += cur; // remove from left side
            } else {
                leftRes += cur; // remove from right side
                rightOffset++;
            }
        }
        for (int i = offset + 1; i <= leftEnd; i++) {
            leftRes += left.charAt(leftStart + i);
            rightRes += right.charAt(rightStart + i);
            rightOffset++;
        }

        System.out.println("leftRes: " + leftRes + ", cur: " + c + ", rightRes: " + rightRes);

        res = (leftRes + c + rightRes);

        return rightOffset;
    }

    private int findLongestSimilarStrOffset(String left, String right) {
        for (int i = left.length() - 1; i >= 0; i--) {
            String sortedLeft = sortString(left.substring(0, i + 1));
            String sortedRight = sortString(right.substring(0, i + 1));
            System.out.println("sorted left - right: " + i + ", " + sortedLeft + ", " + sortedRight);
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

// "bcabc" => "abc"
// "cbacdcbc" => "acdb"