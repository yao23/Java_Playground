import java.util.ArrayList;
import java.util.List;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == 0) {
            return "";
        } else if (k == 0) {
            return num;
        } else if (len == k) {
            return "0";
        } else {
            int curIdx = 0, counter = 0;
            List<Integer> removedIndices = new ArrayList<>();

            while (curIdx < len && counter < k) {
                char curChar = num.charAt(curIdx);
                if (curChar > '0') {
                    // find a digit larger than the latter one, remove then create a smaller number
                    while (curIdx < len - 1 && num.charAt(curIdx) < num.charAt(curIdx + 1)) {
                        curIdx++;
                    }

                    counter++;
                    removedIndices.add(curIdx);
                    curIdx++;
                }
            }

            if (counter < k) {
                return "0";
            } else {
                String res = "";
                for (int i = 0; i < len; i++) {
                    if (!removedIndices.contains(i)) {
                        res += num.charAt(i);
                    }
                }

                return trimHeadZeros(res);
            }
        }
    }

    private String trimHeadZeros(String str) { // test cases 3, 4
        int idx = 0, len = str.length();

        while (idx < len && str.charAt(idx) == '0') {
            idx++;
        }

        String res = str.substring(idx);

        return (res.equals("")) ? "0" : res;
    }

    // "1432219", 3 => "1219"
    // "10200", 1 => "200"
    // "10", 2 => "0"
    // "10", 1 => "0"
}
