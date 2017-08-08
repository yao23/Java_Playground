package www.clouds.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public Lock readLock = readWriteLock.readLock();
    public Lock writeLock = readWriteLock.writeLock();

    private List<Integer> list = new ArrayList<>();

    public void addElement(int element) {
        writeLock.lock();

        try {
            list.add(element);
            System.out.println(Thread.currentThread().getName() + " Adding... " + element
                    + " Current Running Thread Number: " + Thread.activeCount());
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void getElement(int index) {
        readLock.lock();

        try {
            int element = list.get(index);
            System.out.println(Thread.currentThread().getName() + " Getting... " + element
                    + " Current Running Thread Number: " + Thread.activeCount());
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    class Adder implements Runnable {
        ReadWriteLock readWriteLock;
        public Adder(ReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
        }
        @Override
        public void run() {
            readWriteLock.addElement(new Random().nextInt(10));
        }
    }

    class Setter implements Runnable {
        ReadWriteLock readWriteLock;
        int size;
        public Setter(ReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
            this.size = readWriteLock.list.size();
        }
        @Override
        public void run() {
            readWriteLock.getElement(0);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReadWriteLock();

        Adder adder = readWriteLock.new Adder(readWriteLock);
        Setter setter = readWriteLock.new Setter(readWriteLock);

        List<Thread> writeThread = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            writeThread.add(new Thread(adder));
        }

        List<Thread> readThread = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            readThread.add(new Thread(setter));
        }

        for (int i = 0; i < 3; i++) {
            writeThread.get(i).start();
        }

        Thread.sleep(100);

        for (int i = 0; i < 3; i++) {
            readThread.get(i).start();
        }
    }
}