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

    public String addBinaryV0(String a, String b) { // beats 44.49%
        int aLength = a.length();
        int bLength = b.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (Math.max(aLength, bLength) > 0) {
            int aNum = aLength > 0 ? (a.charAt(aLength-- - 1) - '0') : 0;
            int bNum = bLength > 0 ? (b.charAt(bLength-- - 1) - '0') : 0;
            int cNum = aNum + bNum + carry;
            sb.append(cNum % 2);
            carry = cNum / 2;
        }
        return (carry == 1) ? sb.append(1).reverse().toString() : sb.reverse().toString();
    }
}