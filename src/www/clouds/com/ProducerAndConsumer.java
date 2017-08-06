package www.clouds.com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumer {
    class Token {
        private int val;
        private String name;
        public Token(int val, String name) {
            this.val = val;
            this.name = name;
        }
        @Override
        public String toString() {
            return this.name + " " + this.val;
        }
    }

    class Bucket {
        private BlockingQueue<Token> que;
        private int rate; // rate for producer to put token
        public Bucket(int size, int rate) {
            this.que = new ArrayBlockingQueue<>(size);
            this.rate = rate;
        }
        public void putToken(Token token) {
            try {
                Thread.sleep(this.rate);
                que.put(token); // thread safe put() method
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public Token getToken() {
            try {
                Thread.sleep(100);
                return que.take(); // thread safe take() method
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
