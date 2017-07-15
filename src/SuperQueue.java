/**
 * Created by liyao on 7/14/17.
 */

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SuperQueue {
    private PriorityQueue<Integer> superQueue;
    private Set<PriorityQueue<Integer>> myQueues;

    public SuperQueue(Set<PriorityQueue<Integer>> queues) {
        superQueue = new PriorityQueue<>(queues.size());
        myQueues = new HashSet<>(queues);
        for (PriorityQueue<Integer> q : queues) { // O (queues.size)
            superQueue.offer(q.poll());
        }
    }

    public Integer poll() {
        int result = superQueue.poll();
        int min = Integer.MAX_VALUE;
        PriorityQueue<Integer> tmp = new PriorityQueue<>();
        // find next smallest number and add into superQueue
        for (PriorityQueue<Integer> q : myQueues) {
            if (q.peek() < min) {
                min = q.peek();
                tmp = q;
            }
        }

        // update the queue with the smallest number
        superQueue.offer(tmp.poll());

        return result;
    }

    public Integer peek() {
        return superQueue.peek();
    }

    // [0,2,4,5], [1,3], [6,7,8] => [0,1,2,3,4,5,6,7,8]
}

/**
 *  Set of priority queues, design a data structure to implement peek(), poll() for SuperQueue.
 **/