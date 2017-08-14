package www.clouds.com.multi_thread;

public class Singleton {
    private static Singleton singletonInstance;
    private static Object object = new Object();

    private Singleton() {
        // inline code here
    }

    public static Singleton getInstance() {
        if (singletonInstance == null) { // all threads check
            synchronized (object) {
                if (singletonInstance == null) { // threads hold lock check
                    singletonInstance = new Singleton();
                }
            }
        }
        return singletonInstance;
    }
}
