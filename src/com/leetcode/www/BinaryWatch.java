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
            for (int j = 1; j <= num; j++) {
                int remain = getRemainTime(i, j, remainHour, remainMinute);
                if (remain > 0) {
                    if (i < 4) {
                        dfs(remain, remainMinute, num - j, res);
                    } else {
                        dfs(remainHour, remain, num - j, res);
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

// n = 0
// ["0:00"]

// Input: n = 1
// Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

// n = 2
// ["0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48",
//  "1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00",
//  "4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"]