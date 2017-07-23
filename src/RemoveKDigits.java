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
                    while (curIdx < len - 1 && num.charAt(curIdx) <= num.charAt(curIdx + 1)) {
                        curIdx++;
                    }

                    counter++;
                    removedIndices.add(curIdx);
                }

                curIdx++;
            }

            if (counter < k) {
                String tmpRes = getUpdatedString(removedIndices, num);
                removedIndices.clear();

                for (int i = tmpRes.length() - 1; i > 0 && counter < k; i--) {
                    if (tmpRes.charAt(i) >= tmpRes.charAt(i - 1)) {
                        removedIndices.add(i);
                        counter++;
                    }
                }

                if (counter < k) {
                    tmpRes = getUpdatedString(removedIndices, tmpRes);
                    removedIndices.clear();
                    for (int i = tmpRes.length() - 1; i >= 0 && counter < k; i--) {
                        if (tmpRes.charAt(i) > '0') {
                            removedIndices.add(i);
                            counter++;
                        }
                    }

                    return getResult(removedIndices, tmpRes);
                } else {
                    return getResult(removedIndices, tmpRes);
                }
            } else {
                return getResult(removedIndices, num);
            }
        }
    }

    private String getResult(List<Integer> removedIndices, String num) {
        String tmpRes = getUpdatedString(removedIndices, num);

        return trimHeadZeros(tmpRes);
    }

    private String getUpdatedString(List<Integer> removedIndices, String num) {
        int len = num.length();
        String res = "";
        for (int i = 0; i < len; i++) {
            if (!removedIndices.contains(i)) {
                res += num.charAt(i);
            }
        }
        return res;
    }

    private String trimHeadZeros(String str) {
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
    // "112", 1 => "11"
    // "112", 2 => "1"
    // "1234567890", 9 => "0"
}
