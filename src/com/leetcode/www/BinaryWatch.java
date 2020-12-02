package com.leetcode.www;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryWatch { // LC 401
    private int totalHour = 11;
    private int totalMinute = 59;
    private int[] time = new int[] {1, 2, 4, 8, 16, 32, 1, 2, 4, 8};

    /**
     * 54.57%
     *
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num == 0) {
            res.add("0:00");
            return res;
        } else {
            dfs(totalHour, totalMinute, num, res, new HashSet<>());
            return res;
        }
    }

    private void dfs(int remainHour, int remainMinute, int num, List<String> res, Set<Integer> timeIdx) {
        if (num == 0) {
            int hour = totalHour - remainHour;
            int minute = totalMinute - remainMinute;
            res.add(hour + ":" + (minute < 10 ? "0" : "") + minute);
            return;
        }
        for (int i = 0; i < 10 && !timeIdx.contains(i); i++) {
            timeIdx.add(i);
            int remain = getRemainTime(i, remainHour, remainMinute);

            if (remain >= 0) { // either remainHour or remainMinute is 0 could be continue
                if (i > 5) { // hour
                    dfs(remain, remainMinute, num - 1, res, timeIdx);
                } else { // minute
                    dfs(remainHour, remain, num - 1, res, timeIdx);
                }
                timeIdx.remove(i);
            } else {
                timeIdx.remove(i); // don't forget to release for use in time
                return;
            }
        }
    }

    private int getRemainTime(int i, int remainHour, int remainMinute) {
        if (i > 5) { // hour
            return (remainHour - time[i]);
        } else { // minute
            return (remainMinute - time[i]);
        }
    }

    /**
     * beats 25.93%
     *
     * n = 0
     * ["0:00"]
     *
     * Input: n = 1
     * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     *
     *  n = 2
     *  ["0:03","0:05","0:06","0:09","0:10","0:12","0:17","0:18","0:20","0:24","0:33","0:34","0:36","0:40","0:48",
     *  "1:01","1:02","1:04","1:08","1:16","1:32","2:01","2:02","2:04","2:08","2:16","2:32","3:00",
     * "4:01","4:02","4:04","4:08","4:16","4:32","5:00","6:00","8:01","8:02","8:04","8:08","8:16","8:32","9:00","10:00"]
     * @param num
     * @return
     */
    public List<String> readBinaryWatchV1(int num) {
        List<String> times = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num) {
                    times.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return times;
    }
}
