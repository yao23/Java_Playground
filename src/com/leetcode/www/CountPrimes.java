package com.leetcode.www;

public class CountPrimes { // LC 204
    public int countPrimes(int n) { // beats 97.01%
        if (n < 3) {
            return 0;
        }

        boolean[] f = new boolean[n];

        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i]) {
                continue;
            }

            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }

    public int countPrimesV0(int n) { // beats 57.56%
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        return count;
    }
}
