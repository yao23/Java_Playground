package www.clouds.com;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {
    private Lock lock = new ReentrantLock();
    private Condition isFull = lock.newCondition();
    private Condition isEmpty = lock.newCondition();

    private Deque<String> que;
    private int size;

    public MyBlockingQueue(int size) {
        this.size = size;
        this.que = new ArrayDeque<>();
    }
}
