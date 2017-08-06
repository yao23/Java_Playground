package www.clouds.com;

public class LockPractice {
    public static synchronized void getClassValue() {
        // Class Lock
    }

    public synchronized void setNewValue() {
        // Object Lock
    }

    public synchronized void getNewValue() {
        // Object Lock
    }

    public void getNormalValue() {
        // None Lock
    }

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static class ThreadDemo1 extends Thread {
        public void run() {
            // Synchronized Block
            synchronized(lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lock2) {
                    System.out.println("ThreadDemo1 !");
                }
            }
        }
    }

    static class ThreadDemo2 extends Thread {
        public void run() {
            // Synchronized Block
            synchronized(lock2) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lock1) {
                    System.out.println("ThreadDemo2 !");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo1 t1 = new ThreadDemo1();
        ThreadDemo2 t2 = new ThreadDemo2();
        t1.start();
        t2.start(); // dead lock happens
    }
}
