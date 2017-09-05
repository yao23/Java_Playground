package com.leetcode.www;

public class PermutationSequence { // LC 60
    public String getPermutation(int n, int k) { // beats 75.06%
        int[] num = new int[n];
        int count = 1, k_tmp = k;
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
            count *= (i + 1);
        }
        k_tmp--;
        StringBuilder tmpStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            count /= (n - i);
            int selected = k_tmp / count;
            k_tmp = k_tmp % count;
            tmpStr.append(num[selected]);
            for (int j = selected + 1; j < n; j++) {
                num[j - 1] = num[j];
            }
        }
        return tmpStr.toString();
    }
}

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.

 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):

 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 *
 * Given n and k, return the kth permutation sequence.
 */