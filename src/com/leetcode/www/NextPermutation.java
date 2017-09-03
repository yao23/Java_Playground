package com.leetcode.www;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int index = -1;
        for( int i = nums.length - 1; i > 0; i-- ) {
            if( nums[i - 1] < nums[i] ) {
                index = i;
                break;
            }
        }
        if( index == -1 )
            ReverseArray(0, nums.length - 1, nums);
        else {
            int BiggerIndex = FindBig(nums[index - 1], index, nums);
            swap(BiggerIndex, index - 1, nums);
            ReverseArray(index, nums.length - 1, nums);
        }
    }
    private int FindBig(int sentinal, int index, int[] num) {
        int BigIndex = index;
        int BigValue = num[index];
        for( int i = index + 1; i < num.length; i++ ) {
            if( sentinal < num[i] && num[i] <= BigValue ) {
                BigValue = num[i];
                BigIndex = i;
            }
        }
        return BigIndex;
    }
    private void ReverseArray(int start, int end, int[] num) {
        while( start < end ) {
            swap(start, end, num);
            start++;
            end--;
        }
    }
    private void swap(int m, int n, int[] num) {
        int tmp = num[m];
        num[m] = num[n];
        num[n] = tmp;
    }
}
