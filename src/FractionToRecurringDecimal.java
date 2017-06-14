/**
 * Created by liyao on 6/13/17.
 */
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        } else if (denominator == 0) {
            return "";
        } else {
            return "1";
        }
    }
}

// all rational numbers are either terminating decimal or repeating decimal numerals
// https://math.stackexchange.com/questions/61937/how-can-i-prove-that-all-rational-numbers-are-either-terminating-decimal-or-repe
