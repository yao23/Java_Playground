package com.leetcode.www;

import java.util.LinkedList;
import java.util.Queue;

public class DesignHitCounter { // LC 362 [Google]
    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     * <p>
     * O(s) s is total seconds in given time interval, in this case 300.
     * basic ideal is using buckets. 1 bucket for every second because we only need to keep the recent hits info for 300
     * seconds. hit[] array is wrapped around by mod operation. Each hit bucket is associated with times[] bucket which
     * record current time. If it is not current time, it means it is 300s or 600s... ago and need to reset to 1.
     *
     *
     * / ["HitCounter","hit","hit","hit","getHits","hit","getHits","getHits"]
     // [[],[1],[2],[3],[4],[300],[300],[301]]
     // [null,null,null,null,3,null,4,3]
     */
    class HitCounter { // beats 80.30%
        private int[] times;
        private int[] hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) { // the timestamp comes first time
                times[index] = timestamp;
                hits[index] = 1;
            } else { // the timestamp comes more than 1 time
                hits[index]++;
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
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

        /**
         * Initialize your data structure here.
         */
        public HitCounterV1() {
            q = new LinkedList<>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            q.offer(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            while (!q.isEmpty() && timestamp - q.peek() >= 300) {
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

        /**
         * Initialize your data structure here.
         */
        public HitCounterV2() {
            sum = 0;
            time = new LinkedList<>();
            hits = new LinkedList<>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            if (time.isEmpty() || time.getLast() != timestamp) {
                time.addLast(timestamp);
                hits.addLast(1);
            } else {
                hits.addLast(hits.removeLast() + 1);
            }
            sum++;
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            int head = timestamp - 300;
            while (!time.isEmpty() && time.getFirst() <= head) {
                time.removeFirst();
                sum -= hits.removeFirst();
            }
            return sum;
        }
    }

    /**
     * Queue, not efficient as above 2 linked lists solution
     */
    class HitCounterV3 { // beats 52.69%
        class Tuple {
            int time;
            int count;

            public Tuple(int time, int count) {
                this.time = time;
                this.count = count;
            }
        }

        Queue<Tuple> q;
        int currCount;

        public HitCounterV3() {
            q = new LinkedList<>();
            currCount = 0;
        }

        public void hit(int timestamp) {
            advance(timestamp);
            if (!q.isEmpty() && q.peek().time == timestamp) {
                q.peek().count += 1;
            } else {
                q.offer(new Tuple(timestamp, 1));
            }
            currCount += 1;
        }

        private void advance(int timestamp) {
            while (!q.isEmpty() && q.peek().time <= timestamp - 300) {
                currCount -= q.poll().count;
            }
        }

        public int getHits(int timestamp) {
            advance(timestamp);
            return currCount;
        }
    }

    /**
     * Circular Array, most efficient solution
     *
     * There are two solutions, the first one we choose 1s as granularity, the other is full accuracy(see the post).
     * We call move() before hit() and getHits(). move() will take time at most O(N), where N is the length of the array.
     *
     * https://nuttynanaus.wordpress.com/2014/03/09/software-engineer-interview-questions/
     *
     */
    public class HitCounterV4 { // beats 85.11%
        int N;
        int[] count;
        int lastPosition;
        int lastTime;
        int sum;

        /** Initialize your data structure here. */
        public HitCounterV4() {
            N = 300;
            count = new int[N];
            lastPosition = 0;
            lastTime = 0;
            sum = 0;
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            move(timestamp);
            count[lastPosition]++;
            sum++;
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            move(timestamp);
            return sum;
        }

        void move(int timestamp){
            int gap = Math.min(timestamp - lastTime, N);
            for (int i = 0; i < gap;i++) {
                lastPosition = (lastPosition + 1) % N;
                sum -= count[lastPosition];
                count[lastPosition] = 0;
            }
            lastTime = timestamp;
        }
    }
}