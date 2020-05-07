import com.sun.tools.javac.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ConfusingNumberII { // 1088 [Google]

    /**
     * DFS O(5^D) Beats 75 %
     *
     * Runtime: O(5^D) where D is the number of digits in N.
     *
     * @param N
     * @return
     */
    public int confusingNumberII(int N) {
        int digits = Integer.toString(N).length();
        AtomicInteger confusingNums = new AtomicInteger(0);
        getConfusingNumbers(N, 0, 0, 1, digits, confusingNums);

        return confusingNums.get();
    }

    Map<Integer, Integer> reversedDigits = Map.of(0, 0, 1, 1, 6, 9, 8, 8, 9, 6);
    List<Integer> digits = List.of(0, 1, 6, 8, 9); //we cannot use map's keySet because order is not guaranteed.

    private boolean getConfusingNumbers(int n, int num, int reversed, int reversedMult, int digitsLeft,
                                        AtomicInteger confusingNums) {
        if (digitsLeft == 0) {
            if (num != 0 && num != reversed) {
                confusingNums.incrementAndGet();
            }
            return true;
        }

        num *= 10;

        for (int digit : digits) {
            int num2 = digit + num;
            if (num2 > n) {
                return false;
            }
            int reversed2 = reversedMult * reversedDigits.get(digit) + reversed;

            if (!getConfusingNumbers(n, num2, reversed2,
                    digit > 0 || reversedMult > 1 ? reversedMult * 10 : 1, digitsLeft - 1,
                    confusingNums)) {
                return false; // we have reached the number no further processing is needed.
            }
        }

        return true;
    }

    class Solution {
        public int confusingNumberII(int N) {
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0,0);
            map.put(1,1);
            map.put(8,8);
            map.put(6,9);
            map.put(9,6);
            return helper(map, 0, 0, N, 1);
        }

        int helper(Map<Integer, Integer> map, int num, int rotate, int N, int base) {
            if( num > N || num<0 ) {
                return 0;
            }
            int result =0;
            if(num != rotate && num!=0) {
                result++;
            }
            for(int key : map.keySet()) {
                if((num ==0 && key==0) || num>100000000) {
                    continue;
                }
                result+=helper(map, num*10+key, map.get(key)*base+rotate, N, base*10);
            }
            return result;
        }
    }
}

/**
 We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees,
 they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid.

 A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 (Note that the rotated number can be greater than the original number.)

 Given a positive integer N, return the number of confusing numbers between 1 and N inclusive.



 Example 1:

 Input: 20
 Output: 6
 Explanation:
 The confusing numbers are [6,9,10,16,18,19].
 6 converts to 9.
 9 converts to 6.
 10 converts to 01 which is just 1.
 16 converts to 91.
 18 converts to 81.
 19 converts to 61.
 Example 2:

 Input: 100
 Output: 19
 Explanation:
 The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].


 Note:

 1 <= N <= 10^9