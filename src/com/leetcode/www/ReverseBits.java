package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class ReverseBits { // LC 190
    // you need treat n as an unsigned value
    public int reverseBitsV0(int n) { // beats 27.74%
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1;   // CATCH: must do unsigned shift
            if (i < 31) { // CATCH: for last digit, don't shift!
                result <<= 1;
            }
        }
        return result;
    }

    // cache
    private final Map<Byte, Integer> cache = new HashMap<>();
    public int reverseBits(int n) { // beats 5.03%
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8*i) & 0xFF);
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3) {
                result <<= 8;
            }
        }
        return result;
    }

    private int reverseByte(byte b) {
        Integer value = cache.get(b); // first look up from cache
        if (value != null) {
            return value;
        }
        value = 0;
        // reverse by bit
        for (int i = 0; i < 8; i++) {
            value += ((b >>> i) & 1);
            if (i < 7) {
                value <<= 1;
            }
        }
        cache.put(b, value);
        return value;
    }
}
