package com.leetcode.www;

public class StringCompression { // LC 443
    /**
     * Runtime: 1 ms, faster than 98.78% of Java online submissions for String Compression.
     * Memory Usage: 35.7 MB, less than 99.46% of Java online submissions for String Compression.
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1) {
                for (char c : Integer.toString(count).toCharArray()) {
                    chars[indexAns++] = c;
                }
            }
        }
        return indexAns;
    }

    /**
     * Runtime: 1 ms, faster than 98.78% of Java online submissions for String Compression.
     * Memory Usage: 35.8 MB, less than 99.40% of Java online submissions for String Compression.
     *
     * two-pointer approach (actually three pointers where write pointer always points to the position that is ready to be written)
     * Time: O(n), space: O(1)
     *
     * @param chars
     * @return
     */
    public int compressV0(char[] chars) {
        int slow = 0, fast = 0;
        int write = 0;
        while (fast < chars.length) {
            if (chars[slow] == chars[fast])
                fast++;
            else {
                int len = fast - slow;
                write = helper(chars, write, len, chars[slow]);
                slow = fast;
            }
        }

        write = helper(chars, write, fast - slow, chars[slow]);
        return write;
    }

    public int helper(char[] chars, int write, int len, char c) {
        chars[write] = c;
        write++;

        if (len == 1) {
            return write;
        }
        String str = len + "";
        for (int i = 0; i < str.length(); i++) {
            chars[write] = str.charAt(i);
            write++; //remember to change update write
        }
        return write;
    }
}
