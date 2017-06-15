/**
 * Created by liyao on 6/13/17.
 */

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            return "";
        } else if (numerator == 0) {
            return "0";
        }

        long nume = Math.abs((long)numerator);
        long deno = Math.abs((long)denominator);
        String sign = (numerator < 0) ^ (denominator < 0) ? "-" : "";
        if (nume % deno == 0) {
            return sign + nume/deno + "";
        }

        Map<Long,Integer> map = new HashMap<>();
        StringBuilder res = new StringBuilder(sign + nume/deno + "."); // StringBuffer is thread safe
        long end = nume % deno * 10;//The decimal portion of the value, after decimal point
        int i = 0;

        while (end != 0){
            if (map.containsKey(end)) {
                res.insert(res.indexOf(".") + map.get(end) + 1, "(");
                res.append(")");
                return res.toString();
            } else {
                res.append(end / deno);
                map.put(end, i++);
                end = (end % deno) * 10;
            }
        }

        return res.toString();
    }

    // 0,0 => ""
    // 0,1 => "0"
    // 1,5 => "0.2"
    // 2,1 => "2"
    // 2,3 => "0.(6)"
    // 667/1000 => "0.667"
}

// all rational numbers are either terminating decimal or repeating decimal numerals
// https://math.stackexchange.com/questions/61937/how-can-i-prove-that-all-rational-numbers-are-either-terminating-decimal-or-repe
