package com.leetcode.www;

public class CanPlaceFlowers { // 605
    // greedy
    public boolean canPlaceFlowers(int[] flowerbed, int n) { // beats 12.95%
        int count = 0;
        for (int i = 0; i < flowerbed.length && count < n; i++) {
            if (flowerbed[i] == 0) {
                //get next and prev flower bed slot values. If i lies at the ends the next and prev are considered as 0.
                int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                int prev = (i == 0) ? 0 : flowerbed[i - 1];
                if (next == 0 && prev == 0) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }

        return count == n;
    }

    public boolean canPlaceFlowersV0(int[] flowerbed, int n) { // beats 20.42%
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
