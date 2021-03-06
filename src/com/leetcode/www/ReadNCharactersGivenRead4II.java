package com.leetcode.www;

public class ReadNCharactersGivenRead4II { // LC 158 (Facebook)
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     *
     * The read function may be called multiple times.
     */
    private int tmpBufIdx = 0;
    private int tmpBufCnt = 0;
    private char[] tmpBuf = new char[4];
    public int read(char[] buf, int n) { // beats 24.87%
        int idx = 0;
        while (idx < n) {
            if (tmpBufIdx == 0) {
                tmpBufCnt = read4(tmpBuf);
            }
            if (tmpBufCnt == 0) {
                break;
            }
            while (idx < n && tmpBufIdx < tmpBufCnt) {
                buf[idx++] = tmpBuf[tmpBufIdx++];
            }
            if (tmpBufIdx >= tmpBufCnt) {
                tmpBufIdx = 0;
            }
        }
        return idx;
    }

    private int tmpSize = 0;
    private int tmpIndex = 0;
    private char[] tmp = new char[4];
    private boolean eof = false;

    public int readV0(char[] buf, int n) { // beats 24.87%
        int len = 0;
        int total = 0;

        while (total < n && (!eof || tmpSize > 0)) {
            if (!eof && tmpSize == 0) {
                len = read4(tmp);
                tmpSize = len;
                tmpIndex = 0;
                eof = (len < 4);
            }
            len = Math.min(tmpSize, n - total);

            if (len == 0) {
                break;
            }
            len = tmpIndex + len;
            for (int i = tmpIndex; i < len; i++) {
                buf[total] = tmp[i];
                total++;
                tmpIndex++;
                tmpSize--;
            }
        }

        return total;
    }

    private static char[] chars = new char[]{'a','b'};
    private static int offset = 0;
    private static int read4(char[] buf) {
        int len = 4;
        if (chars.length - offset < 4) {
            len = chars.length - offset;
        }
        for (int i = 0; i < len; i++) {
            buf[i] = chars[offset + i];
        }
        offset += len;
        return len;
    }


    private static void print(char[] chs) {
        System.out.println();
        int len = chs.length;
        for (int i = 0; i < len; i++) {
            System.out.print((chs[i]+"") + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        char[] buf = new char[2];
//        read(buf, 1);
//        read(buf, 2);
        System.out.println("final");
        print(buf);
    }
}

// "a", [read(1)] => 1 (['a'])
// ['a','b'], read(buf, 1), read(buf, 2) => 2 (['a','b'])
// ['a'], read(buf, 0), read(buf, 1), read(buf, 2) => 1 (['','a',''])
