package io.educative.www.Coding.P10_Subsets;

import java.util.*;

public class P10_07_EvaluateExpression {
    /**
     * Time complexity
     * The time complexity of this algorithm will be exponential and will be similar to Balanced Parentheses.
     * Estimated time complexity will be O(N*2^N) but the actual time complexity ( O(4^n/sqrt{n}) ) is bounded by
     * the Catalan number and is beyond the scope of a coding interview. See more details here.
     *
     * Space complexity
     * The space complexity of this algorithm will also be exponential, estimated at O(2^N) though the actual will be
     * ( O(4^n/sqrt{n}) ).
     *
     * @param input
     * @return
     */
    public static List<Integer> diffWaysToEvaluateExpression(String input) {
        List<Integer> result = new ArrayList<>();
        // base case: if the input string is a number, parse and add it to output.
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.parseInt(input));
        } else {
            for (int i = 0; i < input.length(); i++) {
                char chr = input.charAt(i);
                if (!Character.isDigit(chr)) {
                    // break the equation here into two parts and make recursively calls
                    List<Integer> leftParts = diffWaysToEvaluateExpression(input.substring(0, i));
                    List<Integer> rightParts = diffWaysToEvaluateExpression(input.substring(i + 1));
                    for (int part1 : leftParts) {
                        for (int part2 : rightParts) {
                            if (chr == '+')
                                result.add(part1 + part2);
                            else if (chr == '-')
                                result.add(part1 - part2);
                            else if (chr == '*')
                                result.add(part1 * part2);
                        }
                    }
                }
            }
        }
        return result;
    }

    // memoization map
    Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

    /**
     * Memorized version
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToEvaluateExpressionV1(String input) {
        if (map.containsKey(input))
            return map.get(input);
        List<Integer> result = new ArrayList<>();
        // base case: if the input string is a number, parse and return it.
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            result.add(Integer.parseInt(input));
        } else {
            for (int i = 0; i < input.length(); i++) {
                char chr = input.charAt(i);
                if (!Character.isDigit(chr)) {
                    List<Integer> leftParts = diffWaysToEvaluateExpressionV1(input.substring(0, i));
                    List<Integer> rightParts = diffWaysToEvaluateExpressionV1(input.substring(i + 1));
                    for (int part1 : leftParts) {
                        for (int part2 : rightParts) {
                            if (chr == '+')
                                result.add(part1 + part2);
                            else if (chr == '-')
                                result.add(part1 - part2);
                            else if (chr == '*')
                                result.add(part1 * part2);
                        }
                    }
                }
            }
        }
        map.put(input, result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = P10_07_EvaluateExpression.diffWaysToEvaluateExpression("1+2*3");
        System.out.println("Expression evaluations: " + result);

        result = P10_07_EvaluateExpression.diffWaysToEvaluateExpression("2*3-4-5");
        System.out.println("Expression evaluations: " + result);
    }
}
