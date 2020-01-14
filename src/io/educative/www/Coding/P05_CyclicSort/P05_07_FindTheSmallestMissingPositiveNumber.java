package io.educative.www.Coding.P05_CyclicSort;

class P05_07_FindTheSmallestMissingPositiveNumber {
    /**
     * Time complexity
     * The time complexity of the above algorithm is O(n).
     *
     * Space complexity
     * The algorithm runs in constant space O(1).
     *
     * @param nums
     * @return
     */
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return i + 1;

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(P05_07_FindTheSmallestMissingPositiveNumber.findNumber(new int[] { -3, 1, 5, 4, 2 }));
        System.out.println(P05_07_FindTheSmallestMissingPositiveNumber.findNumber(new int[] { 3, -2, 0, 1, 2 }));
        System.out.println(P05_07_FindTheSmallestMissingPositiveNumber.findNumber(new int[] { 3, 2, 5, 1 }));
    }
}