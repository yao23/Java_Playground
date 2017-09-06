package com.leetcode.www;

public class AddBinary { // LC 67
    public String addBinary(String a, String b) { // beats 27.70%
        if (a == null || a.equals("")) {
            return b;
        }
        if (b == null || b.equals("")) {
            return a;
        }
        StringBuilder a1 = new StringBuilder(a);
        a1.reverse();
        StringBuilder b1 = new StringBuilder(b);
        b1.reverse();
        StringBuilder sum = new StringBuilder();
        int sumLen = Math.max(a.length(), b.length());
        int carry = 0;
        for (int i = 0; i < sumLen; i++) {
            int t1 = (i >= a.length()? 0 : (a1.charAt(i) - '0'));
            int t2 = (i >= b.length()? 0 : (b1.charAt(i) - '0'));
            int t3 = t1 + t2 + carry;
            carry = t3 / 2;
            t3 = t3 % 2;
            sum.append(t3);
        }
        if (carry != 0) {
            sum.append(carry);
        }
        sum.reverse();
        return sum.toString();
    }
}
