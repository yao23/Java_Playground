package www.clouds.com;

public class ThreadPractice {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());
        thread1.start();
        thread2.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {

    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {

    }
}