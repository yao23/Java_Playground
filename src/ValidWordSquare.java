import java.util.List;

public class ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        int len = words.size();
        for (int curIdx = 0; curIdx < len; curIdx++) {
            String str = words.get(curIdx);
            int strLen = str.length();
            if (!isLengthSame(words, curIdx, strLen, len) || !isCharSame(words, curIdx, str, len)) {
                return false;
            }
        }

        return true;
    }

    private boolean isLengthSame(List<String> words, int curIdx, int strLen, int len) {
        if (curIdx < strLen) {
            if (len - curIdx == strLen - curIdx) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isCharSame(List<String> words, int curIdx, String str, int len) {
        for (int i = curIdx; i < len; i++) {
            char horizontalChar =  str.charAt(i);
            String tmp = words.get(i);
            char verticalChar = tmp.charAt(curIdx);
            if (horizontalChar != verticalChar) {
                return false;
            }
        }

        return true;
    }
}

// [
//  "abcd",
//  "bnrt",
//  "crmy",
//  "dtye"
// ]
// true

// [
//  "abcd",
//  "bnrt",
//  "crm",
//  "dt"
// ]
// true

// [
//  "ball",
//  "area",
//  "read",
//  "lady"
// ]
// false