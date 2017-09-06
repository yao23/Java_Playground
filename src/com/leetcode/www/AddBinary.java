package com.leetcode.www;

public class AddBinary {
    public String addBinary(String a, String b) {
        if( a == null || a.equals("") )
            return b;
        if( b == null || b.equals("") )
            return a;
        StringBuilder a1 = new StringBuilder(a);
        a1.reverse();
        StringBuilder b1 = new StringBuilder(b);
        b1.reverse();
        StringBuilder sum = new StringBuilder();
        int SumLen = Math.max(a.length(), b.length());
        int carry = 0;
        for( int i = 0; i < SumLen; i++ ) {
            int t1 = (i >= a.length()? 0 : (a1.charAt(i) - '0'));
            int t2 = (i >= b.length()? 0 : (b1.charAt(i) - '0'));
            int t3 = t1 + t2 + carry;
            carry = t3 / 2;
            t3 = t3 % 2;
            sum.append(t3);
        }
        if( carry != 0 )
            sum.append(carry);
        sum.reverse();
        return sum.toString();
    }
}
