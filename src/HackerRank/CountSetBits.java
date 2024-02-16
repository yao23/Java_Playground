
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    // Assume x is unsigned 4 byte int
    // return number of 1 in the binary representation
  
    // i.e. if x == 5, return 2 => [1 0 1]
    // i.e. if x == 7, return 3 => [1 1 1]
    // i.e. if x == 8, return 1 => [1 0 0 0]
    // i.e. if x == 10, return 2 => [1 0 1 0]
    // i.e. if x == 16, return 1 => [1 0 0 0 0]
    // x == 62 [] (32, 16, 8, 4, 2, 1), if start from left to right, it's not time and space efficient
    
    // start from most right, mod by 2, then keep diving by 2 to get the digit
    // i.e. 5, (5 % 2 =1, 5/2 = 2 => 2 % 2 = 0, 2 / 2 = 1 => 1 % 2 = 1)
    // i.e. 7, 7 % 2 = 1, 7/2 = 3 = >3 % 2 = 1, 3 / 2 = 1 => 1 % 2 = 1)
    public static int countSetBits(int x) {
        int counter = 0;
        if (x % 2 == 1) {
            counter++;
        }
        int tmp = x;
        while (tmp >= 2) {
            tmp /= 2;
            if (tmp % 2 == 1) {
                counter++;
            }
        }
        return counter;
    }

    static int addNumbers(int a, int b) {
        return a+b; 
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        a = in.nextInt();
        int b;
        b = in.nextInt();
        int sum;

        sum = addNumbers(a, b);
        System.out.println(sum);
    }
}
