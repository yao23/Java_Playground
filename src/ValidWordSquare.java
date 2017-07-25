import java.util.List;

public class ValidWordSquare {
    public boolean validWordSquare(List<String> words) {
        int len = words.size();
        if (len == 0) {
            return true;
        } else {
            String firstWord = words.get(0);
            if (len == firstWord.length()) {
                for (int curIdx = 0; curIdx < len; curIdx++) {
                    String str = words.get(curIdx);
                    if (!isCharSame(words, curIdx, str, len)) {
                        return false;
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    private boolean isCharSame(List<String> words, int curIdx, String str, int len) {
        for (int i = curIdx; i < len; i++) {
            String tmp = words.get(i);
            int strLen = str.length(), tmpLen = tmp.length();
            if (i < strLen && curIdx < tmpLen) {
                char horizontalChar =  str.charAt(i);
                char verticalChar = tmp.charAt(curIdx);
                if (horizontalChar != verticalChar) {
                    System.out.println("[hv char diff] curIdx: " + curIdx + ", " + str + ", " + i + ", " + horizontalChar + ", " + verticalChar);
                    return false;
                }
            } else if (i >= strLen && curIdx >= tmpLen) {
                return true;
            } else {
                System.out.println("[hv len diff] curIdx: " + i + ", " + str.length() + ", "+ curIdx  + ", "  + tmpLen);
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