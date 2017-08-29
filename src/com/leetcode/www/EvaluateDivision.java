package com.leetcode.www; /**
 * Created by liyao on 6/29/17.
 */
import java.util.*;

public class EvaluateDivision { // LC 399
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, ArrayList<String>> pairs = new HashMap<>();
        HashMap<String, ArrayList<Double>> valuesPair = new HashMap<>();

        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<String>());
                valuesPair.put(equation[0], new ArrayList<Double>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<String>());
                valuesPair.put(equation[1], new ArrayList<Double>());
            }
            // add equation pair
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            // add value pair
            valuesPair.get(equation[0]).add(values[i]);
            valuesPair.get(equation[1]).add(1/values[i]);
        }

        double[] result = new double[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];

            result[i] = dfs(query[0], query[1], pairs, valuesPair, new HashSet<String>(), 1.0);

            if (result[i] == 0.0) {
                result[i] = -1.0;
            }
        }
        return result;
    }

    private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
        if (set.contains(start)) { // cycle in graph
            return 0.0;
        }

        if (!pairs.containsKey(start)) {
            return 0.0;
        }

        if (start.equals(end)) { // handle start == end, even like "d/d"
            return value;
        }

        set.add(start);

        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;

        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value * valueList.get(i));
            if (tmp != 0.0) {
                break;
            }
        }

        set.remove(start);

        return tmp;
    }

    // [ ["a","b"],["b","c"] ],[2.0,3.0],[ ["a","c"],["b","c"],["a","e"],["a","a"],["x","x"] ] => [6.00000,3.00000,-1.00000,1.00000,-1.00000]

    // beats 77.54%, 3ms
}
