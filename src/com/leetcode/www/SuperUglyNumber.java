package com.leetcode.www; /**
 * Created by liyao on 6/28/17.
 */
import java.util.Arrays;
import java.util.PriorityQueue;

public class SuperUglyNumber { // LC 313
    public static int nthSuperUglyNumberI(int n, int[] primes) { // 39ms, beats 32.59%
        int[] ugly = new int[n];
        int[] idx = new int[primes.length]; // next index for current prime number to get smallest product, finally [8,3,2,1] for test case 1

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            //find next smallest prime number
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);
            }

            //slip duplicate
            for (int j = 0; j < primes.length; j++) {
                while (primes[j] * ugly[idx[j]] <= ugly[i]) {
                    idx[j]++;
                }
            }
        }

        return ugly[n - 1];
    }

    public int nthSuperUglyNumberV1(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int next = 1;
        for (int i = 0; i < n; i++) {
            ugly[i] = next;

            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                //skip duplicate and avoid extra multiplication
                if (val[j] == ugly[i]) {
                    val[j] = ugly[idx[j]++] * primes[j];
                }
                //find next ugly number
                next = Math.min(next, val[j]);
            }
        }

        return ugly[n - 1];
    }

    public int nthSuperUglyNumberHeapV2(int n, int[] primes) {
        int[] ugly = new int[n];

        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) {
            pq.add(new Num(primes[i], 1, primes[i]));
        }
        ugly[0] = 1;

        for (int i = 1; i < n; i++) {
            ugly[i] = pq.peek().val;
            while (pq.peek().val == ugly[i]) {
                Num nxt = pq.poll();
                pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
            }
        }

        return ugly[n - 1];
    }

    private class Num implements Comparable<Num> {
        int val; // current smallest prime
        int idx;
        int p; // input prime num

        public Num(int val, int idx, int p) {
            this.val = val;
            this.idx = idx;
            this.p = p;
        }

        @Override
        public int compareTo(Num that) {
            return this.val - that.val;
        }
    }

    public static void main(String[] args) {
        int[] primes = new int[] {2, 7, 13, 19};
        int n = 12;

        System.out.println(nthSuperUglyNumberI(n, primes));
    }
}
