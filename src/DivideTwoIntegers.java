public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MIN_VALUE;
        }
        boolean isNegative = false;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            isNegative = true;
        }
        long res = (long)dividend;
        int counter = 0;

        while (res > 0) {
            long tmp = (long)divisor; // avoid overflow when left shift
            while (tmp < res) {
                tmp <<= 1;
                counter++;
            }
            if (tmp == res) {
                return counter;
            } else { // tmp > res
                tmp >>= 1;
                res -= tmp;
                counter--;
            }
        }

        if (counter == Integer.MAX_VALUE && isNegative) {
            counter = Integer.MIN_VALUE;
        }

        return counter;
    }
}
