package io.educative.www.Coding.P05_CyclicSort;

class P05_06_FindTheCorruptPair {
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
    public static int[] findNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
            else
                i++;
        }

        for (i = 0; i < nums.length; i++)
            if (nums[i] != i + 1)
                return new int[] { nums[i], i + 1 };

        return new int[] { -1, -1 };
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = P05_06_FindTheCorruptPair.findNumbers(new int[] { 3, 1, 2, 5, 2 });
        System.out.println(nums[0] + ", " + nums[1]);
        nums = P05_06_FindTheCorruptPair.findNumbers(new int[] { 3, 1, 2, 3, 6, 4 });
        System.out.println(nums[0] + ", " + nums[1]);
    }
}