package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

public class DesignHitCounter { // LC 362
    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     *
     * O(s) s is total seconds in given time interval, in this case 300.
     * basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300
     * seconds. hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which
     * record current time. If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
     */
    class HitCounter { // beats 80.30%
        private int[] times;
        private int[] hits;

        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }
    }

    /**
     * use a queue to record the information of all the hits. Each time we call the function getHits( ), we have to
     * delete the elements which hits beyond 5 mins (300). The result would be the length of the queue
     */
    class HitCounterV1 { // beats 16.49%
        Queue<Integer> q = null;

        /** Initialize your data structure here. */
        public HitCounterV1() {
            q = new LinkedList<>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            q.offer(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while(!q.isEmpty() && timestamp - q.peek() >= 300) {
                q.poll();
            }
            return q.size();
        }
    }

    /**
     * 2 Linked List
     */
    class HitCounterV2 { // beats 73.77
        private int sum;
        private LinkedList<Integer> time;
        private LinkedList<Integer> hits;

        /** Initialize your data structure here. */
        public HitCounterV2() {
            sum = 0;
            time = new LinkedList<>();
            hits = new LinkedList<>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            if (time.isEmpty() || time.getLast() != timestamp) {
                time.addLast(timestamp);
                hits.addLast(1);
            } else {
                hits.addLast(hits.removeLast() + 1);
            }
            sum++;
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int head = timestamp - 300;
            while (!time.isEmpty() && time.getFirst() <= head) {
                time.removeFirst();
                sum -= hits.removeFirst();
            }
            return sum;
        }
    }
}

// ["HitCounter","hit","hit","hit","getHits","hit","getHits","getHits"]
// [[],[1],[2],[3],[4],[300],[300],[301]]
// [null,null,null,null,3,null,4,3]