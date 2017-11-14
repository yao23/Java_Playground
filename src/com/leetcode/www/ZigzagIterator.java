package com.leetcode.www; /**
 * Created by liyao on 7/17/17.
 */

import java.util.Iterator;
import java.util.List;

public class ZigzagIterator { // LC 281
    private Iterator<Integer> iterator1;
    private Iterator<Integer> iterator2;
    private int mode;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterator1 = v1.iterator();
        iterator2 = v2.iterator();
        if (v1 != null && v1.size() > 0) {
            mode = 1;
        } else {
            if (v2 != null && v2.size() > 0) {
                mode = 2;
            } else {
                mode = 0;
            }
        }
    }

    public int next() {
        if (mode == 0) {
            return Integer.MIN_VALUE;
        } else if (mode == 1) { // list 1
            if (iterator1 != null && iterator1.hasNext()) {
                int result = iterator1.next();
                mode = 2;
                return result;
            } else {
                if (iterator2 != null && iterator2.hasNext()) {
                    int result = iterator2.next();
                    mode = 2;
                    return result;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
        } else { // list 2
            if (iterator2 != null && iterator2.hasNext()) {
                int result = iterator2.next();
                mode = 1;
                return result;
            } else {
                if (iterator1 != null && iterator1.hasNext()) {
                    int result = iterator1.next();
                    mode = 1;
                    return result;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
        }
    }

    public boolean hasNext() {
        return iterator1.hasNext() || iterator2.hasNext();
    }

    // [],[] => []
    // [],[3,4,5,6] => [3,4,5,6]
    // [1,2],[3,4,5,6] => [1,3,2,4,5,6]

    // beats 70.43%
}
