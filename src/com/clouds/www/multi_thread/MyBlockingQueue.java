package com.clouds.www.multi_thread;

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

    public void put(String val) {
        lock.lock();
        try {
            while (que.size() == size) {
                isFull.await();
            }
            Thread.sleep(100);
            que.addLast(val);
            System.out.println("PUT:   " + val);

            isEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while (que.isEmpty()) {
                isEmpty.await();
            }
            Thread.sleep(100);
            String val = que.removeFirst();
            System.out.println("TAKE:   " + val);

            isFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static class Producer implements Runnable {
        MyBlockingQueue que;
        Producer(MyBlockingQueue que) {
            this.que = que;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                this.que.put("Token " + i);
            }
        }
    }

    static class Consumer implements Runnable {
        MyBlockingQueue que;
        Consumer(MyBlockingQueue que) {
            this.que = que;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                this.que.take();
            }
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(5);

        Producer producer = new Producer(myBlockingQueue);
        Consumer consumer = new Consumer(myBlockingQueue);

        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }
}
