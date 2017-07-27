import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {

    }

    public List<String> addOperatorsV0(String num, int target) {
        // collect all possible num combinations
        List<List<String>> numList = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        collectNums(num, 0, tmp, numList);
        // calculate after add operators
        List<String> res = new ArrayList<>();
        calculate(numList, res, target);
        return res;
    }

    private void calculate(List<List<String>> numList, List<String> res, int target) {
        for (List<String> nums : numList) {
            int numOprtrComb = (int)Math.pow(3, nums.size() - 1); // +, -, *
            for (int i = 0; i < numOprtrComb; i++) {
                if (getTarget(i, nums, target)) {
                    res.add(getExpression(i, nums));
                }
            }
        }
    }

    private String getExpression(int i, List<String> nums) {
        // concatenate nums and operators
        return "";
    }

    private boolean getTarget(int i, List<String> nums, int target) {
        // concatenate nums and operators, calculate, compare with target
        return true;
    }

    private void collectNums(String num, int depth, List<String> tmp, List<List<String>> numList) {
        if (depth == num.length()) {
            numList.add(new ArrayList<>(tmp));
        } else {
            for (int i = depth; i < num.length(); i++) {
                String cur = num.substring(depth, i + 1);
                if (isValidStr(cur)) {
                    tmp.add(cur);
                    collectNums(num, i + 1, tmp, numList);
                    tmp.remove(tmp.size() - 1);
                } else {
                    return;
                }
            }
        }
    }

    private boolean isValidStr(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == '0') {
                return false;
            }
        }

        return true;
    }
}
