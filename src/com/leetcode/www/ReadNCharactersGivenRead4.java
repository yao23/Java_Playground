package com.leetcode.www;

public class ReadNCharactersGivenRead4 { // LC 157
    /* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) { // beats 21.61%
        boolean eof = false;      // end of file flag
        int total = 0;            // total bytes have read
        char[] tmp = new char[4]; // temp buffer

        while (!eof && total < n) {
            int count = read4(tmp);

            // check if it's the end of the file
            eof = count < 4;

            // get the actual count (test case 1)
            count = Math.min(count, n - total);

            // copy from temp buffer to buf
            for (int i = 0; i < count; i++) {
                buf[total] = tmp[i];
                total++;
            }
        }

        return total;
    }

    public int readV0(char[] buf, int n) { // beats 21.61%
        int res = 0;
        char[] tmp = new char[4];

        while (res < n) {
            int count = read4(tmp);
            int len = Math.min(count, n - res);
            for (int i = 0; i < len; i++) {
                buf[res + i] = tmp[i];
            }
            res += len; // len, not count
            if (count < 4) { // no more to read
                break;
            }
        }

        return res;
    }

    private char[] chars = new char[100];
    private int offset = 0;
    private int read4(char[] buf) {
        int len = 4;
        if (offset + 4 < chars.length) {
            len = chars.length - offset;
        }
        for (int i = 0; i < len; i++) {
            buf[i] = chars[offset + i];
        }
        return len;
    }
}

// "ab", 1 => "a"