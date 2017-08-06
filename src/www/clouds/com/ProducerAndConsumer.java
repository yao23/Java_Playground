package www.clouds.com;

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
}
