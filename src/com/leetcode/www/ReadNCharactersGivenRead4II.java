package com.leetcode.www;

public class ReadNCharactersGivenRead4II { // LC 158
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     *
     * The read function may be called multiple times.
     */
    public int read(char[] buf, int n) {
        boolean eof = false;
        int total = 0;
        char[] tmp = new char[4];
        int tmpSize = 0;
        int tmpIndex = 0;
        int len = 0;

        while (!eof && total < n) {
            if (tmpSize == 0) {
                len = read4(tmp);
                tmpSize = len;
                tmpIndex = 0;
                eof = (len < 4);
            }
            len = Math.min(tmpSize, n - total);

            for (int i = tmpIndex; i < tmpIndex + len; i++) {
                buf[total] = tmp[i];
                total++;
                tmpIndex++;
                tmpSize--;
            }
        }

        return total;
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
