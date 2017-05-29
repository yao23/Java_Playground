public class Main {
    private static void test() {
        System.out.println("test");
    }

    private static int getNumSquares(int start, int end) {
        int sqrtStart = (int) (Math.sqrt(start)); // round down
        int sqrtEnd = (int) (Math.sqrt(end)); // round down

        return (sqrtEnd - sqrtStart + 1);
    }

    private static int solution(int A, int B) {
        // write your code in Java SE 8

        // check start number
        int start = (A < 0) ? 0 : A; // whole square starts from zero to positive numbers
        int end = B;

        return getNumSquares(start, end);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        test();

        // get number of squares
        int left = 4;
        int right = 17;

        System.out.println("num of squares: " + solution(left, right));

        left = -10000;
        right = 10000;

        System.out.println("num of squares: " + solution(left, right));

        left = -10;
        right = 27;

        System.out.println("num of squares: " + solution(left, right));


    }
}
