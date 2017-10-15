package com.leetcode.www;

public class PowerOfThree { // LC 326
    /**
     * https://leetcode.com/articles/power-of-three/
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) { // beats 62.18%
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    // If log10(n) / log10(3) returns an int (more precisely, a double but has 0 after decimal point),
    // then n is a power of 3.
    public boolean isPowerOfThreeV3(int n) { // beats 27.68%
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    // Base Conversion
    public boolean isPowerOfThreeV2(int n) { // beats 3.95%
        return Integer.toString(n, 3).matches("^10*$");
    }

    // Find the maximum integer that is a power of 3 and check if it is a multiple of the given input.
    public boolean isPowerOfThreeV1(int n) { // beats 13.45%
        int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(0x7fffffff) / Math.log(3)));
        return n > 0 && maxPowerOfThree % n == 0;
    }

    // Integer Limitations
    public boolean isPowerOfThreeV0(int n) { // beats 20.52%
        // 1162261467 is 3^19,  3^20 is bigger than int
        return (n > 0 &&  1162261467 % n == 0);
    }
}
