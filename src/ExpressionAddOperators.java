import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {

    }

    private List<String> helper(String num, int target) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                result.add("" + 0);
            } else {
                for (int j = i; j < num.length(); j++) {

                }
            }
        }
    }
}
