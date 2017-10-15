package com.leetcode.www;

public class PowerOfTwo { // LC 231
    // iteration
    public boolean isPowerOfTwo(int n) { // beats 11.81, O(logn)
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return (n == 1);
    }

    /**
     * https://discuss.leetcode.com/topic/47195/4-different-ways-to-solve-iterative-recursive-bit-operation-math/2
     * @param n
     * @return
     */
    // the range of an integer = -2147483648 (-2^31) ~ 2147483647 (2^31-1),
    // the max possible power of two = 2^30 = 1073741824
    public boolean isPowerOfTwoV2(int n) { // beats 11.81%, O(1)
        return n > 0 && (1073741824 % n == 0);
    }

    // power of 2 has only one bit 1
    public boolean isPowerOfTwoV1(int n) { // beats 11.81%, O(1)
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     *  a power of two in binary form has and only has one "1".
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwoV0(int n) { // beats 11.81%, O(1)
        return n > 0 && Integer.bitCount(n) == 1;
    }
}
