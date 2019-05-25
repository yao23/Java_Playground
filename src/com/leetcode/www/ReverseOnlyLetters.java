package com.leetcode.www;

public class ReverseOnlyLetters { // LC 917
    /**
     * Runtime: 1 ms, faster than 76.38% of Java online submissions for Reverse Only Letters.
     * Memory Usage: 33.6 MB, less than 100.00% of Java online submissions for Reverse Only Letters.
     *
     * https://leetcode.com/problems/reverse-only-letters/discuss/296549/Probably-the-easiest-or-Beats-100
     *
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        if (S.isEmpty()) {
            return S;
        }
        return reverse(S.toCharArray(), 0, S.length() - 1);
    }

    private void swap(char[] a, int x, int y) {
        char t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    private String reverse(char[] a, int start, int end){
        while (start <= end) {
            if (Character.isLetter(a[start]) && Character.isLetter(a[end])) { // only swap when start and end are letters
                swap(a, start++, end--);
            } else if (Character.isLetter(a[start])) {
                end -= 1;
            } else if (Character.isLetter(a[end])) {
                start += 1;
            } else {
                start += 1;
                end -= 1;
            }
        }
        return new String(a);
    }

    // Example 1:
    //
    // Input: "ab-cd"
    // Output: "dc-ba"
    // Example 2:
    //
    // Input: "a-bC-dEf-ghIj"
    // Output: "j-Ih-gfE-dCba"
    // Example 3:
    //
    // Input: "Test1ng-Leet=code-Q!"
    // Output: "Qedo1ct-eeLg=ntse-T!"
    //
    //
    // Note:
    //
    // S.length <= 100
    // 33 <= S[i].ASCIIcode <= 122
    // S doesn't contain \ or "
}
