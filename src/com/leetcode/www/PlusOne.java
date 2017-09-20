package com.leetcode.www; /**
 * Created by liyao on 6/1/17.
 */
import java.io.*;
import java.util.*;

public class PlusOne { // LC 66
    private static int[] plusOne(int[] arr) {
        int[] tmpRes = new int[arr.length];
        int carry = 1;

        for (int i = arr.length-1; i >= 0; i--) {
            int tmp = arr[i] + carry;
            tmpRes[i] = tmp % 10;
            carry = tmp / 10;

            if (i > 0) {
                continue;
            } else { // i == 0
                if (carry == 1) { // one more carry
                    int[] res = new int[arr.length+1];
                    res[0] = carry;

                    for (int j = 1; j < res.length; j++) {
                        res[j] = tmpRes[j-1];
                    }

                    return res;
                } else {
                    return tmpRes;
                }
            }
        }

        return tmpRes;
    }


    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java 8.");

        for (String string : strings) {
            System.out.println(string);
        }

        int[] arr = new int[] {2, 3, 4};
        int[] arr1 = new int[] {9, 9, 9};
        int[] output = plusOne(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(output[i] + " ");
        }

        System.out.println();

        int[] output1 = plusOne(arr1);
        for (int i = 0; i < output1.length; i++) {
            System.out.print(output1[i] + " ");
        }
    }
}