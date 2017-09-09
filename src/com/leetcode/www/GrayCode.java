package com.leetcode.www;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int num_count = 1 << n;
        for( int i = 0; i < num_count; i++ )
            result.add(i^(i>>1));
        return result;
    }

    public List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if( n == 0 ) {
            result.add(0);
            return result;
        }
        if( n == 1 ) {
            result.add(0);
            result.add(1);
            return result;
        }

        List<Integer> prev = grayCode2(n-1);
        result.addAll(prev);
        int top = 1 << (n-1);
        for( int i = prev.size()-1; i >= 0; i-- )
            result.add(top+prev.get(i));
        return result;
    }
}
