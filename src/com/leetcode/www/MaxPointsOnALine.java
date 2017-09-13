package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine { // LC 149
    public int maxPoints(Point[] points) { // beats 51.87%
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
        int result=0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int overlap = 0, max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int gcd = generateGCD(x, y);
                if (gcd != 0) {
                    x/=gcd;
                    y/=gcd;
                }

                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1);
                    }
                } else {
                    Map<Integer,Integer> m = new HashMap<>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max=Math.max(max, map.get(x).get(y));
            }
            result=Math.max(result, max+overlap + 1);
        }
        return result;
    }

    private int generateGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return generateGCD(b,a % b);
        }
    }

    public int maxPointsV0(Point[] points) { // not working for test case 2 (return 3)
        Map<Double, Integer> map = new HashMap<>();
        int res = 0;
        int len = points.length;
        for (int i = 0; i < len; i++) {
            int invalidK = 0;
            int add = 1;
            for (int j = i + 1; j < len; j++) {
                if (points[j].x == points[i].x) {
                    if (points[j].y == points[i].y) {
                        add++;
                    } else {
                        invalidK++;
                    }
                    continue;
                }
                double k = points[j].y == points[i].y ? 0.0 : (1.0 * (points[j].y - points[i].y)) / (points[j].x - points[i].x);
                if (map.containsKey(k)) {
                    int count = map.get(k);
                    map.put(k, count + 1);
                } else {
                    map.put(k, 1);
                }
            }
            for (Integer it : map.values()) {
                if (it + add > res) {
                    res = it.intValue() + add;
                }
            }
            res = Math.max(invalidK + add, res);
            map.clear();
        }
        return res;
    }
}

/**
 * Definition for a point.
 */
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

// [] => 0
// [[0,0],[94911151,94911150],[94911152,94911151]] => 2

