import java.util.ArrayList;
import java.util.List;
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
}