package com.leetcode.www;

public class CanPlaceFlowers { // 605
    public boolean canPlaceFlowers(int[] flowerbed, int n) { // beats 20.42%
        int len = flowerbed.length;
        for (int i = 0; i < len && n > 0; i++) {
            if (isValid(flowerbed, i)) {
                flowerbed[i] = 1;
                n--;
            }
        }
        return (n == 0);
    }

    private boolean isValid(int[] flowerbed, int idx) {
        if (flowerbed[idx] == 1) {
            return false;
        }
        int len = flowerbed.length;
        if (idx == 0) {
            if (len == 1) {
                return true;
            } else {
                return (flowerbed[idx + 1] == 0);
            }
        } else if (idx == len - 1) {
            return (flowerbed[idx - 1] == 0);
        } else {
            return (flowerbed[Math.max(0, idx - 1)] == 0 && flowerbed[Math.min(idx + 1, len -1)] == 0);
        }
    }
}
