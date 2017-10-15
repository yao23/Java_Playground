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

    // Sieve of Eratosthenes algorithm
    /**
     * In the boolean array m, m[n] means the number n. Thus for each time, if m[n] is a prime, we need to delete all
     * the multiple of m[n]. And finally, the remaining numbers are primes.
     *
     * @param n
     * @return
     */
    public int countPrimesV1(int n) { // beats 67.55%
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (m[i]) {
                continue;
            }

            count++;
            for (int j = i; j < n; j = j + i) {
                m[j] = true;
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
