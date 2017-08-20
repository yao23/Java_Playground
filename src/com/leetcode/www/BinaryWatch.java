package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch { // LC 401
    private int totalHour = 24;
    private int totalMinute = 60;
    private int[] time = new int[] {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        dfs(totalHour, totalMinute, num, res);
        return res;
    }

    private void dfs(int remainHour, int remainMinute, int num, List<String> res) {
        if (num == 0) {
            int hour = totalHour - remainHour;
            int minute = totalMinute - remainMinute;
            res.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
            return;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < num; j++) {
                int remain = getRemainTime(i, j, remainHour, remainMinute);
                if (remain > 0) {
                    if (i < 4) {
                        dfs(remain, remainMinute, num - 1, res);
                    } else {
                        dfs(remainHour, remain, num - 1, res);
                    }
                } else {
                    return;
                }
            }
        }
    }

    private int getRemainTime(int i, int j, int remainHour, int remainMinute) {
        if (i < 4) { // hour
            return (remainHour - j * time[i]);
        } else { // minute
            return (remainMinute - j * time[i]);
        }
    }
}
