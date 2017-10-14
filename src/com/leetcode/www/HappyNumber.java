package com.leetcode.www;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber { // LC 202
    public boolean isHappy(int n) { // beats 96.12%
        int x = n;
        int y = n;
        while (x > 1) {
            x = cal(x);
            if (x == 1) {
                return true;
            }
            y = cal(cal(y));
            if (y == 1) {
                return true;
            }

            if (x == y) {
                return false;
            }
        }
        return true;
    }
    private int cal(int n){
        int x = n;
        int s = 0;
        while (x > 0) {
            s = s + (x % 10) * (x % 10);
            x = x/10;
        }
        return s ;
    }

    //this is the bitset representation of happy numbers in range[0,810)
    private static final long[]happyMark = new long[]{580548859274370L, 35812649762896L,
            5764889547766761510L, 158621866461187L, -9061136725337702208L,
            -8574391826910016959L, 4683743612499927428L, 286423854940160L,
            29290989780729856L, 7566260683533189120L, 1170945809492058640L,
            720593571220033568L, 292095590400L};

    public boolean isHappyV1(int n) {
        if( n>810 ){
            int sum = 0, remainder = 0;
            while( n>0 ){
                remainder = n%10;
                sum += remainder*remainder;
                n /= 10;
            }
            n = sum;
        }
        int idx = ( n>>6 );
        long bitRep = ( 1L<< (n&0x3f) );
        return ( happyMark[idx]&bitRep )!=0;
    }

    /**
     *  The idea is to use one hash set to record sum of every digit square of every number occurred. Once the current
     *  sum cannot be added to set, return false; once the current sum equals 1, return true;
     * @param n
     * @return
     */
    public boolean isHappyV0(int n) { // beats 18.79%
        Set<Integer> inLoop = new HashSet<>();
        int squareSum, remain;
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1) {
                return true;
            } else {
                n = squareSum;
            }

        }
        return false;
    }
}
