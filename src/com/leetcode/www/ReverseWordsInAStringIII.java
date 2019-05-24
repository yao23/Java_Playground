package com.leetcode.www;

public class ReverseWordsInAStringIII { // LC 557
    /**
     * Runtime: 3 ms, faster than 97.50% of Java online submissions for Reverse Words in a String III.
     * Memory Usage: 36.2 MB, less than 99.96% of Java online submissions for Reverse Words in a String III.
     *
     * Two Pointers
     *
     * https://leetcode.com/problems/reverse-words-in-a-string-iii/discuss/297970/Java-Speed-99.60-and-Memory-99.96-2-Approach
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = 0;
        int length = arr.length - 1;
        while (right < length) {
            if (arr[right]==' ') {
                reverseWord(arr, left, right - 1);
                left = right + 1;
            }
            right++;
        }
        reverseWord(arr, left, right); // last word
        return new String(arr);

    }

    private char[] reverseWord(char[] arr, int left, int right){
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return arr;
    }

    /**
     * Runtime: 3 ms, faster than 97.50% of Java online submissions for Reverse Words in a String III.
     * Memory Usage: 36.3 MB, less than 99.91% of Java online submissions for Reverse Words in a String III.
     *
     * @param s
     * @return
     */
    public String reverseWordsV0(String s) {
        String[] strArr=s.split(" ");
        StringBuilder build=new StringBuilder();
        for (int i = 0; i < strArr.length-1; i++) {
            build.append(reverseWord(strArr[i]));
            build.append(" ");
        }
        build.append(reverseWord(strArr[strArr.length - 1])); // last word
        return new String(build);
    }

    private String reverseWord(String word){
        char[] arr = word.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
}
