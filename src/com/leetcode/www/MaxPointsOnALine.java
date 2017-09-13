package com.leetcode.www;

import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnALine {
    public int maxPoints(Point[] points) {
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        int res = 0;
        int len = points.length;
        for( int i = 0; i < len; i++ ) {
            int InvalidK = 0;
            int add = 1;
            for( int j = i + 1; j < len; j++ ) {
                if( points[j].x == points[i].x ) {
                    if( points[j].y == points[i].y )
                        add++;
                    else
                        InvalidK++;
                    continue;
                }
                double k = points[j].y == points[i].y ? 0.0 :
                        (1.0 * (points[j].y - points[i].y)) /
                                (points[j].x - points[i].x);
                if( map.containsKey(k) ) {
                    int count = map.get(k);
                    map.put(k, count + 1);
                }
                else
                    map.put(k, 1);
            }
            for( Integer it : map.values() ) {
                if( it + add > res )
                    res = it.intValue() + add;
            }
            res = Math.max(InvalidK + add, res);
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

