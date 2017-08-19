package com.leetcode.www;

public class ReadNCharactersGivenRead4II { // LC 158
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     *
     * The read function may be called multiple times.
     */
    private int tmpSize = 0;
    private int tmpIndex = 0;
    private char[] tmp = new char[4];
    private boolean eof = false;

    public int read(char[] buf, int n) { // beats 24.87%
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
            // System.out.println("before: " + len + ", " + tmpSize + ", " + tmpIndex);
            // print(tmp);
            if (len == 0) {
                break;
            }
            len = tmpIndex + len;
            for (int i = tmpIndex; i < len; i++) { //System.out.println("index: " + i + ", " + len + ", " + tmpSize + ", " + tmpIndex);
                buf[total] = tmp[i];
                total++;
                tmpIndex++;
                tmpSize--;
            }

            // if (eof) {
            //     break;
            // }
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
        for (int i = 0; i < len; i++) { System.out.println("inside index: " + i + ", " + offset + ", " + len);
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

// ['a','b'], read(buf, 1), read(buf, 2) => 2 (['a','b'])
// ['a'], read(buf, 0), read(buf, 1), read(buf, 2) => 1 (['','a',''])
