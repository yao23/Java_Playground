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
                System.out.println("curIdx before: " + curIdx);
                char curChar = num.charAt(curIdx);
                if (curChar > '0') {
                    // find a digit larger than the latter one, remove then create a smaller number
                    while (curIdx < len - 1 && num.charAt(curIdx) < num.charAt(curIdx + 1)) {
                        curIdx++;
                    }

                    counter++;
                    removedIndices.add(curIdx);
                    System.out.println("curIdx after: " + curIdx + ", counter: " + counter);
                    System.out.println(removedIndices);
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

                return res;
            }
        }
    }
}
