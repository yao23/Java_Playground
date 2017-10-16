package com.leetcode.www;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionByHeight { // LC 406
    /**
     * 1. Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people
     * taller than them, therefore each guy's index will be just as same as his k value.
     * 2. For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
     *
     * E.g.
     * input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     * subarray after step 1: [[7,0], [7,1]]
     * subarray after step 2: [[7,0], [6,1], [7,1]]
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) { // beats 63.14%
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return (o1[0] != o2[0]) ? (-o1[0] + o2[0]) : (o1[1] - o2[1]);
            }
        });
        List<int[]> res = new LinkedList<>();
        for (int[] cur : people) {
            res.add(cur[1], cur);
        }
        return res.toArray(new int[people.length][]);
    }

    public int[][] reconstructQueueV0(int[][] people) { // beats 22.69%
        Arrays.sort(people, (a, b) -> (a[0] == b[0]) ? (a[1] - b[1]) : (b[0] - a[0]));
        List<int[]> list = new LinkedList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }
        return list.toArray(new int[list.size()][]);
    }
}

// Input:
// [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
// Output:
// [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]