package io.educative.www.Coding.P03_FastSlowPointers;

class P03_03_HappyNumber {
    /**
     * Time Complexity
     * The time complexity of the algorithm is difficult to determine. However we know the fact that all unhappy numbers
     * eventually get stuck in the cycle: 4 -> 16 -> 37 -> 58 -> 89 -> 145 -> 42 -> 20 -> 4
     *
     * This sequence behavior tells us two things:
     *
     * If the number NN is less than or equal to 1000, then we reach the cycle or ‘1’ in at most 1001 steps.
     * For N > 1000N>1000, suppose the number has ‘M’ digits and the next number is ‘N1’. From the above Wikipedia link,
     * we know that the sum of the squares of the digits of ‘N’ is at most 9^2M, or 81M
     * (this will happen when all digits of ‘N’ are ‘9’).
     * This means:
     *
     * N1 < 81M
     * As we know M = log(N+1)
     * Therefore: N1 < 81 * log(N+1) => N1 = O(logN)
     * This concludes that the above algorithm will have a time complexity of O(logN).
     *
     * Space Complexity
     * The algorithm runs in constant space O(1).
     *
     * @param num
     * @return
     */
    public static boolean find(int num) {
        int slow = num, fast = num;
        do {
            slow = findSquareSum(slow); // move one step
            fast = findSquareSum(findSquareSum(fast)); // move two steps
        } while (slow != fast); // found the cycle

        return slow == 1; // see if the cycle is stuck on the number '1'
    }

    private static int findSquareSum(int num) {
        int sum = 0, digit;
        while (num > 0) {
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(P03_03_HappyNumber.find(23));
        System.out.println(P03_03_HappyNumber.find(12));
    }
}