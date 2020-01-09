package io.educative.www.Coding.P05_CyclicSort;

class P05_02_FindTheMissingNumber {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(n). In the while loop, although we are not incrementing the
     * index i when swapping the numbers, this will result in more than ‘n’ iterations of the loop, but in the
     * worst-case scenario, the while loop will swap a total of ‘n-1’ numbers and once a number is at its correct index,
     * we will move on to the next number by incrementing i. In the end, we iterate the input array again to find the
     * first number missing from its index, so overall, our algorithm will take O(n) + O(n-1) + O(n)
     * which is asymptotically equivalent to O(n).
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param nums
     * @return
     */
    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != nums[nums[i]])
                swap(nums, i, nums[i]);
            else
                i++;
        }

        // find the first number missing from its index, that will be our required number
        for (i = 0; i < nums.length; i++)
            if (nums[i] != i)
                return i;

        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(P05_02_FindTheMissingNumber.findMissingNumber(new int[] { 4, 0, 3, 1 }));
        System.out.println(P05_02_FindTheMissingNumber.findMissingNumber(new int[] { 8, 3, 5, 2, 4, 6, 0, 1 }));
    }
}